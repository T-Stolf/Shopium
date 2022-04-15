package shopium.adminListener.event;

import org.springframework.context.ApplicationEvent;



public class ShopiumEvent extends ApplicationEvent {

	  /**
	 * 
	 */
	
	public String Type;
	private static final long serialVersionUID = 1L;

	  public ShopiumEvent(Object source, String type) {
	    super(source);
	    this.Type = type;
	  }
	  
	  public String getType()
	  {
		  return Type;
	  }

}
