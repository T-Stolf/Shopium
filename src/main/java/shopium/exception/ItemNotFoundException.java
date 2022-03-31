package shopium.exception;

public class ItemNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

    public ItemNotFoundException(Long uid) {
        super("Could not find item " + uid);
    }

}
