//package shopium.repository;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.time.LocalDateTime;
//
//import shopium.entity.Item;
//import shopium.entity.Order_;
//import shopium.entity.Status;
//import shopium.entity.UserAccount;
//
//@Configuration
//public class LoadDatabaseTemp {
//	
//	private static final Logger log = LoggerFactory.getLogger(LoadDatabaseTemp.class);
//	
//	  @Bean
//	  public CommandLineRunner initDatabase(UserAccountRepository repo, OrderRepository orderRepo, ItemRepository itemRepo) {
//	
//	      return args -> {
//	          log.info("Preloading " + repo.save(new UserAccount("$2a$12$nWJP8g6CGATY8QkDJQ7YOO2X4qzZXgTTkQhFHAs18QMJ9rRX4D682", "Admin", "Admin", LocalDateTime.now(), "toronto", "Admin")));
//	          log.info("Preloading " + repo.save(new UserAccount("$2a$12$nWJP8g6CGATY8QkDJQ7YOO2X4qzZXgTTkQhFHAs18QMJ9rRX4D682", "User", "User", LocalDateTime.now(), "toronto", "User")));
//	          
//	          repo.findAll().forEach(user -> log.info("Preloaded " + user));
//	    	  
//	    	  log.info("Preloading " + orderRepo.save(new Order_( (long) 101, LocalDateTime.now(), 100 , 5, Status.IN_PROGRESS)));
//	
//	    	  orderRepo.findAll().forEach(Order_ -> log.info("Preloaded " + Order_));
//	    	  
//	    	  log.info("Preloading " + itemRepo.save(new Item( (long) 101, "hat" , "https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg" , "the tragedy of darth plaguess the wise", 100, 4)));
//	    	 
//	    	  itemRepo.findAll().forEach(item-> log.info("Preloaded " + item));
//	    	  
//	          };
//	  }
//	
//}

