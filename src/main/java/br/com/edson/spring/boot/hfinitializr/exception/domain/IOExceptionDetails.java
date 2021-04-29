package br.com.edson.spring.boot.hfinitializr.exception.domain;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
/**
* Class      : IOExceptionDetails
* Description: Subclass IOExceptionDetails extending the ExceptionDetails 
*              superclass.
*
* @author Edson Martins
*/
@Getter
@SuperBuilder
public class IOExceptionDetails extends ExceptionDetails {}
