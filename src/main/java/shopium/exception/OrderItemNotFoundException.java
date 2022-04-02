package shopium.exception;

public class OrderItemNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public OrderItemNotFoundException(Long oid) {
        super("Could not find orderitem " + oid);
    }
}
