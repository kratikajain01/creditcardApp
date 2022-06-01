/**
 * 
 */
package com.task.exception;

/**
 * @author kratika.jain
 *
 */
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.task.entity.ApplicationError;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class,
			ResponseStatusException.class, ConstraintViolationException.class, InternalServerError.class,UsernameNotFoundException.class,
			SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class, Exception.class, })
	protected ResponseEntity<ApplicationError> handleConflict(RuntimeException ex, WebRequest request) {

		Throwable rootCause = getRealRootCause(ex);
		if (rootCause instanceof ResponseStatusException) {
			return new ResponseEntity<ApplicationError>(
					new ApplicationError(((ResponseStatusException) rootCause).getRawStatusCode(),
							((ResponseStatusException) rootCause).getReason(), HttpStatus.CONFLICT),
					HttpStatus.CONFLICT);
		}
		if (rootCause instanceof javax.validation.ConstraintViolationException) {
			javax.validation.ConstraintViolationException cause = (javax.validation.ConstraintViolationException) rootCause;
			Set<ConstraintViolation<?>> constraintViolations = cause.getConstraintViolations();
			StringBuilder exceptions = new StringBuilder();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				exceptions.append(constraintViolation.getMessage()+",");
			}
			
			return new ResponseEntity<ApplicationError>(
					new ApplicationError(1000, exceptions.toString().substring(0, exceptions.toString().length()-1), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
		}
		if (rootCause instanceof EntityNotFoundException || rootCause instanceof BadCredentialsException) {
			return new ResponseEntity<ApplicationError>(
					new ApplicationError(404, rootCause.getMessage().toString(), HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
		
		if (rootCause instanceof ApplicationException) {
			return new ResponseEntity<ApplicationError>(
					new ApplicationError(400, rootCause.getMessage().toString(), HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);
		}
		
		rootCause.printStackTrace();
		return new ResponseEntity<ApplicationError>(
				new ApplicationError(1001, "Some Error Occured", HttpStatus.INTERNAL_SERVER_ERROR),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private Throwable getRealRootCause(Throwable ex) {
		while (ex.getCause() != null) {
			ex = ex.getCause();
		}
		return ex;
	}

}