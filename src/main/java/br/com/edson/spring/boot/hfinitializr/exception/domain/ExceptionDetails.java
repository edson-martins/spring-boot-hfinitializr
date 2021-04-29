package br.com.edson.spring.boot.hfinitializr.exception.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;
/**
* Class      : ExceptionDetails
* Description: Super class defining a ExceptionDetails behavior
*
* @author Edson Martins
*/
@Data
@SuperBuilder
public class ExceptionDetails {
	
	protected String title;
	protected int status;
	protected String details;
	protected String developerMessage;
	protected LocalDateTime timestamp;
}
