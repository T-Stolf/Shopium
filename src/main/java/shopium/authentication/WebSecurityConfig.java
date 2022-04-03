package shopium.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService uds;
	
	@Bean
	AuthenticationProvider Auth() {
		
		DaoAuthenticationProvider authProvider 
					= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(uds);
		
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return authProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/")
		.permitAll()
		.antMatchers("/orders")
		.hasAuthority("User")
		.antMatchers("/orders")
		.hasAuthority("Admin")
		.antMatchers("/items")
		.hasAuthority("Users")
		.antMatchers("/items")
		.hasAuthority("Admin")
		.antMatchers("/admins")
		.hasAuthority("Admin")
		.antMatchers("/users")
		.hasAuthority("Admin")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	
	
}
