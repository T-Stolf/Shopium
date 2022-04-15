package shopium.adminListener.publisher;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

import shopium.adminListener.event.ShopiumEvent;
import shopium.adminListener.listener.InputListener;
import shopium.entity.Order_;
import shopium.entity.UserAccount;

@Service
public class Publisher{
	
	@Autowired 
	private ApplicationEventPublisher APE;

	public void Event(String Type)
	{
		ShopiumEvent SE = new ShopiumEvent(this,Type);
		APE.publishEvent(SE);
	}

}
