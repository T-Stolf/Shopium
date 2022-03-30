package shopium.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "shopium")
@EnableJpaRepositories(basePackages = "shopium.repository")
@EntityScan("shopium.entity")
public class ShopiumApp {
	 public static void main(String... args) {
	        SpringApplication.run(ShopiumApp.class, args);
	    }
	
}
