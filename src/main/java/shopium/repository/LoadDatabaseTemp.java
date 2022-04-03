//package shopium.repository;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.time.LocalDateTime;
//
//import shopium.entity.Admin;
//
//@Configuration
//public class LoadDatabaseTemp {
//	
//	private static final Logger log = LoggerFactory.getLogger(LoadDatabaseTemp.class);
//	
//	  @Bean
//	  public CommandLineRunner initDatabase(AdminRepository repo) {
//	
//	      return args -> {
//	          log.info("Preloading " + repo.save(new Admin("$2a$12$nWJP8g6CGATY8QkDJQ7YOO2X4qzZXgTTkQhFHAs18QMJ9rRX4D682", "Admin", "Admin", LocalDateTime.now(), "toronto")));
//	          
//	          repo.findAll().forEach(user -> log.info("Preloaded " + user));
//	
//	          };
//	  }
//	
//}

