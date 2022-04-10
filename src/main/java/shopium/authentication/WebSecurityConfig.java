package shopium.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//extends basic security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsService uds;
	
	@Bean
	AuthenticationProvider Auth() {
		DaoAuthenticationProvider adminAuthProvider 
			= new DaoAuthenticationProvider();
		adminAuthProvider.setUserDetailsService(uds);
		adminAuthProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return adminAuthProvider;
	}

////	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//			auth.userDetailsService(uds);
//	}
	
	//ignore request calls to any path containing /resources, used to place our internal resources
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resources/**");
	}
	
	//enables http security
	@Override
	protected void configure(HttpSecurity http) throws Exception{
	
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin/**").hasAuthority("Admin")
		.antMatchers("/home*").anonymous()
		.antMatchers("/login*").permitAll()
		.anyRequest().authenticated()
		
		.and()
		
		.formLogin()//.loginPage("/login.html")
		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/index.html", true)
		.failureUrl("/login.html?error=true")
		//.failureHandler(authenticationFailureHandler())
		
	    .and()
	    
	    .logout()
	    .logoutUrl("/perform_logout")
	    .logoutSuccessUrl("/home")
	    .invalidateHttpSession(true)    
	    .deleteCookies("JSESSIONID");
	    //.logoutSuccessHandler(logoutSuccessHandler());
//		http.authorizeRequests()
//		.antMatchers("/").permitAll()
//		.antMatchers("/Register").permitAll()
//		
//		.antMatchers("/myOrders").hasAnyAuthority("User", "Admin")
//		.antMatchers("/myItems").hasAnyAuthority("User", "Admin")
//		.antMatchers("/myAccount").hasAnyAuthority("User", "Admin")
//		.antMatchers("/myOrderItems/{orderID}").hasAnyAuthority("User", "Admin")
//		
//		.antMatchers("/orders").not().hasAuthority("User")
//		.antMatchers("/items").not().hasAuthority("User")
//		.antMatchers("/userAccounts").not().hasAuthority("User")
//		.antMatchers("/orderItems").not().hasAuthority("User")
//		
//		.antMatchers("/orders").hasAuthority("Admin")
//		.antMatchers("/items").hasAuthority("Admin")
//		.antMatchers("/userAccounts").hasAuthority("Admin")
//		.antMatchers("/orderItems").hasAuthority("Admin")
//		
//		.anyRequest().authenticated()
//		
//		.and()
//		.httpBasic()
//		.and().logout();
		
//		http.csrf().disable();
	}
	
	
}
