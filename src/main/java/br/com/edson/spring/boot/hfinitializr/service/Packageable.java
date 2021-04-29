package br.com.edson.spring.boot.hfinitializr.service;

import java.io.IOException;

import br.com.edson.spring.boot.hfinitializr.wrapper.BinaryMessageWrapper;
/**
* Interface  : Packageable
* Description: Interface exposing method(s) generate an archive. 
*              In the current case, is implementing Bundle or Hotfix service
*              generation behaviors, but can be used to implement different other
*              behaviors.
*
* @author Edson Martins
*/
public interface Packageable<T> {
	BinaryMessageWrapper generate(T t) throws IOException;
}
