package br.com.edson.spring.boot.hfinitializr.domain;

import lombok.Data;
/**
* Class      : Bug
* Description: Super class defining a Bug behavior
*
* @author Edson Martins
*/
@Data
public class Bug {
	private String bugId;
	private String title;
}
