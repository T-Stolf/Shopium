package shopium.exception;

public class AdminNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public AdminNotFoundException(Long uid) {
        super("Could not find admin " + uid);
    }

}
