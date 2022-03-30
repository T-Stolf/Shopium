//package shopium.repository;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.time.LocalDateTime;
//
//import shopium.entity.Customer;
//
//@Configuration
//public class LoadDatabaseTemp {
//	
//	private static final Logger log = LoggerFactory.getLogger(LoadDatabaseTemp.class);
//	
//	  @Bean
//	  public CommandLineRunner initDatabase(CustomerRepository repo) {
//	
//	      return args -> {
//	          log.info("Preloading " + repo.save(new Customer("Bilbo Baggins", "billy B", "password1",LocalDateTime.now(), "the shire")));
//	          log.info("Preloading " + repo.save(new Customer("Frodo Baggins", "Fodussy", "1drowssap",LocalDateTime.now(), "the shire")));
//	          log.info("Preloading " + repo.save(new Customer("Thomas Stolf ", "T$hmoney", "moneyman stan", LocalDateTime.now(), "toronto")));
//	          
//	          repo.findAll().forEach(user -> log.info("Preloaded " + user));
//	
//	          };
//	  }
//	
//}

