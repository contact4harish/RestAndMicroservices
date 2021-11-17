package com.Spring.RestAndMicroServices.Exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Spring.RestAndMicroServices.Entity.UserNotFoundException;




/*Specialization of @Component for classes that declare @ExceptionHandler, @InitBinder, or @ModelAttribute methods to be shared across multiple @Controller classes.

Classes annotated with @ControllerAdvice can be declared explicitly as Spring beans or auto-detected via classpath scanning. All such beans are sorted based on Ordered semantics or @Order / @Priority declarations, with Ordered semantics taking precedence over @Order / @Priority declarations. @ControllerAdvice beans are then applied in that order at runtime. Note, however, that @ControllerAdvice beans that implement PriorityOrdered are not given priority over @ControllerAdvice beans that implement Ordered. In addition, Ordered is not honored for scoped @ControllerAdvice beans — for example if such a bean has been configured as a request-scoped or session-scoped bean. For handling exceptions, an @ExceptionHandler will be picked on the first advice with a matching exception handler method. For model attributes and data binding initialization, @ModelAttribute and @InitBinder methods will follow @ControllerAdvice order.

Note: For @ExceptionHandler methods, a root exception match will be preferred to just matching a cause of the current exception, among the handler methods of a particular advice bean. However, a cause match on a higher-priority advice will still be preferred over any match (whether root or cause level) on a lower-priority advice bean. As a consequence, please declare your primary root exception mappings on a prioritized advice bean with a corresponding order.

By default, the methods in an @ControllerAdvice apply globally to all controllers. Use selectors such as annotations, basePackageClasses, and basePackages (or its alias value) to define a more narrow subset of targeted controllers. If multiple selectors are declared, boolean OR logic is applied, meaning selected controllers should match at least one selector. Note that selector checks are performed at runtime, so adding many selectors may negatively impact performance and add complexity.

 * 
 * */

@ControllerAdvice
@RestController
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {
		
		ResponseException respException=new ResponseException(new Date(), ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity(respException, HttpStatus.INTERNAL_SERVER_ERROR);

		
	}

	

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest req) {
		
		ResponseException respException=new ResponseException(new Date(), ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity(respException, HttpStatus.NOT_FOUND);

		
	}
	
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {

		ResponseException respException=new ResponseException(new Date(), "Validation Failure", ex.getBindingResult().getAllErrors().toString());
		
		return new ResponseEntity(respException, HttpStatus.BAD_REQUEST);
}



}
