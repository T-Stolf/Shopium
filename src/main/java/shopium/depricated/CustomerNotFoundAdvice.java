package shopium.depricated;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import shopium.exception.*;

@ControllerAdvice
public class CustomerNotFoundAdvice {
	
	  @ResponseBody
	    @ExceptionHandler(CustomerNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public String employeeNotFoundHandler(CustomerNotFoundException ex) {
	        return ex.getMessage();
	    }

}