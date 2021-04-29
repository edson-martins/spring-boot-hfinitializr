package br.com.edson.spring.boot.hfinitializr.exception.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.edson.spring.boot.hfinitializr.exception.domain.IOExceptionDetails;
/**
 * Controller Advice : PackageFixContentExceptionHandler
 * 
 * Description       : This is the controller advice monitoring the IOException that can
 *                     be raised when mounting the zip archive for bundle or hotfix.
 *                     If an IOException is captured, the exception structure will follow
 *                     the IOExceptionDetails model.
 *
 * @author Edson Martins
 */
@RestControllerAdvice
public class PackageFixContentExceptionHandler {

	@ExceptionHandler(value = IOException.class)
	public ResponseEntity<IOExceptionDetails> handlePackageFixContentFileGeneration(IOException ex) {
		return new ResponseEntity<>(
				IOExceptionDetails.builder()
								  .timestamp(LocalDateTime.now())
								  .status(HttpStatus.BAD_REQUEST.value())
								  .title("Bad Request Exception. Check the documentation")
								  .details(ex.getMessage())
								  .developerMessage(ex.getClass().getName())
								  .build(), HttpStatus.BAD_REQUEST);
	}
}
