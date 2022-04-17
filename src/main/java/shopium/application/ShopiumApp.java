package shopium.application;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = "shopium")
@EnableJpaRepositories(basePackages = "shopium.repository")
@EntityScan("shopium.entity")
public class ShopiumApp {
	 public static void main(String... args) {
	        SpringApplication.run(ShopiumApp.class, args);
	 }
	 
	 @Configuration
	 @EnableWebMvc
	 public class WebConfig implements WebMvcConfigurer {

	     @Override
	     public void addCorsMappings(CorsRegistry registry) {
	         registry.addMapping("/**");
	     }
	 }
}
