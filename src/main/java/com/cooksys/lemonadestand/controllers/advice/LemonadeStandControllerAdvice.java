package com.cooksys.lemonadestand.controllers.advice;
																				import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
// heehoo it give advice to the controllers about how to handle exceptions heehoo
import org.springframework.web.bind.annotation.ControllerAdvice;				// ConAdv is a specialization of Component; allows handling of exception for the whole app
import org.springframework.web.bind.annotation.ExceptionHandler;				// in one global handling component.    arg = path to controllers package
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.model.ErrorDto;

																				//@ResponseBody = annotation that tells Spring to convert our ErrorDto java type to JSON
@ResponseBody				
@ControllerAdvice(basePackages = { "com.cooksys.lemonadestand.controllers" })   // handles any exceptions that occur within the specified base package, if a method is defined for that Excep.
public class LemonadeStandControllerAdvice {
	
	// @ResponseStatus(desired http status code)
	// @ExceptionHandler(customExceptionClass)
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException){  // first arg represents the actual request being handled
		
		return new ErrorDto(badRequestException.getMessage());
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException){
		
		return new ErrorDto(notFoundException.getMessage());
	}
}

// @ExceptionHandler(customExceptionClass)
// we can also handle pre-existing conditions in this way, any kind of exception we want

// because of @ResponseBody, our return type is bound to the response body, and because it's bound to the response body
// and we're using Spring Web, it defaults to JSON conversion :)