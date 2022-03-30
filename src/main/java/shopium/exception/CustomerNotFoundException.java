package shopium.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long uid) {
        super("Could not find user " + uid);
    }
}


