package shopium.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import shopium.exception.OrderNotFoundException;

@ControllerAdvice
public class OrderItemNotFoundAdvice {

	  @ResponseBody
	    @ExceptionHandler(OrderNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public String OrderNotFoundHandler(OrderNotFoundException ex) {
	        return ex.getMessage();
	    }

}
