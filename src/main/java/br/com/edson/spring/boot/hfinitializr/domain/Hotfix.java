package br.com.edson.spring.boot.hfinitializr.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* Class      : Hotfix
* Description: Subclass extending Bug super class defining a Hotfix behavior
*
* @author Edson Martins
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Hotfix extends Bug {
	private List<Note> notes;
}
