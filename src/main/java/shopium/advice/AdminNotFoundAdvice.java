package shopium.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import shopium.exception.*;

@ControllerAdvice
public class AdminNotFoundAdvice {
	
	  @ResponseBody
	    @ExceptionHandler(AdminNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public String AdminNotFoundHandler(AdminNotFoundException ex) {
	        return ex.getMessage();
	    }

}


