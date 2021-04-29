package br.com.edson.spring.boot.hfinitializr.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* Class      : Bundle
* Description: Subclass extending Bug super class defining a Bundle behavior
*
* @author Edson Martins
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class Bundle extends Hotfix {
	private List<Bug> childBugs;
}
