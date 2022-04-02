package shopium.exception;

public class UserAcccountNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public UserAcccountNotFoundException(Long id) {
        super("Could not find user " + id);
    }


}
