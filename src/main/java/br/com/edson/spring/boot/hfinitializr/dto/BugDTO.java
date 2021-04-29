package br.com.edson.spring.boot.hfinitializr.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
/**
* Class      : BugDTO
* Description: DTO model of Bug payload.
*
* @author Edson Martins
*/
@Data
public class BugDTO {
	@NotNull
	private String bugId;
	private String title;
}
