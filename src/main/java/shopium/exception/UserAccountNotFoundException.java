package shopium.exception;

public class UserAccountNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public UserAccountNotFoundException(String name) {
        super("Could not find user " + name);
    }
    
    public UserAccountNotFoundException(Long id) {
        super("Could not find user " + id);
    }


}
