package shopium.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**")
		.allowedMethods("*")
		.allowedOrigins("http://localhost/*", "http://shopium-client.s3-website-us-east-1.amazonaws.com/*")
		.allowedHeaders("")
		.allowCredentials(false)
		.maxAge(-1);
	}

}
