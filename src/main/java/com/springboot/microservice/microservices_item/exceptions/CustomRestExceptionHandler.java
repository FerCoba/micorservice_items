package com.springboot.microservice.microservices_item.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.netflix.hystrix.exception.HystrixRuntimeException;


@EnableWebMvc
@ControllerAdvice
@RestController
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info("Ingresa en el metodo handleNoHandlerFoundException de la clase ResponseEntityExceptionHandler.");
		
		return EnumExceptionsHandler.THROW_EXCEPTION.exceptionBuilder(ex, request, ex.getClass().getSimpleName());

	}

	@ExceptionHandler({ Exception.class,NullPointerException.class,
			NoSuchElementException.class, Exception500Status.class, HttpClientErrorException.class, HystrixRuntimeException.class,
			AccessDeniedException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected ResponseEntity<Object> handleAll(final Exception ex, WebRequest request) {
		logger.info("Ingresa en el metodo handleAll de la clase ResponseEntityExceptionHandler.");
		return EnumExceptionsHandler.THROW_EXCEPTION.exceptionBuilder(ex, request, ex.getClass().getSimpleName());
	}

}
