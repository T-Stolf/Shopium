package shopium.exception;

public class OrderNotFoundException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public OrderNotFoundException(Long oid) {
        super("Could not find order " + oid);
    }

}
