package shopium.exception;

public class CreatorNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public CreatorNotFoundException(Long cid) {
        super("Could not find creator " + cid);
    }

}
