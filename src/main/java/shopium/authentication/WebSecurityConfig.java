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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//extends basic security
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsService uds;
	
	@Bean
	public DaoAuthenticationProvider Auth() {
		DaoAuthenticationProvider authProvider 
			= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(uds);
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(Auth());
	}
	
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
//		.csrf().disable()
		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests()
		.antMatchers("/admin/**").hasAuthority("Admin")
		.antMatchers("/my**").hasAnyAuthority("Admin", "User")
		.antMatchers("/Account*").permitAll()
		.antMatchers("/login*").anonymous()
		.antMatchers("/index*").anonymous()
		.antMatchers("/").anonymous()
//		.anyRequest().authenticated()
		
		
		
		.and()
		
		.formLogin()//.loginPage("/login.html")
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/myAccount", true)
		.failureUrl("/login.html?error=true")
		//.failureHandler(authenticationFailureHandler())
		
	    .and()
	    
	    .logout()
	    .logoutUrl("/logout")
	    .logoutSuccessUrl("/")
	    .invalidateHttpSession(true)    
	    .deleteCookies("JSESSIONID")
	    
	    .and().httpBasic();
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
