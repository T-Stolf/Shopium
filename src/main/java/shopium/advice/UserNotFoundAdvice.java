package shopium.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import shopium.exception.OrderNotFoundException;

@ControllerAdvice
public class UserNotFoundAdvice {

	  @ResponseBody
	    @ExceptionHandler(OrderNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public String UserNotFoundHandler(OrderNotFoundException ex) {
	        return ex.getMessage();
	    }

}
