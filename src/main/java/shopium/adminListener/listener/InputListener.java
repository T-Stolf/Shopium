package shopium.adminListener.listener;

import java.util.HashMap;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import shopium.adminListener.event.ShopiumEvent;
import shopium.authentication.CustomUserDetails;

@Component
 public class InputListener{// implements ApplicationListener<UserEvent>{
	
	private int numLogins = 0;
	private HashMap<String, Integer> actions = new HashMap<String, Integer>();
	
	 @Async
	 @EventListener()
	 public void onApplicationEvent(ShopiumEvent event)
	 {
		 if(actions.get(event.getType()) == null)
		 {
			 actions.put(event.getType(),0);
		 }
		 actions.put(event.getType(), actions.get(event.getType()) + 1);

		 System.out.println("EVENT: " + event.toString());
	 } 
	 
	 @Async
	 @EventListener()
	 public void onLoginEvent(InteractiveAuthenticationSuccessEvent event)
	 {
	 	numLogins = numLogins + 1;
	 	System.out.println("LOGIN Number: " + numLogins);   
	 } 

	 public int getLoginNum()
		{
			return numLogins;
		}

		public HashMap<String, Integer> getActions()
		{
			return actions;
		}
		
	
	 
 }