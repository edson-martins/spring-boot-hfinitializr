package br.com.edson.spring.boot.hfinitializr.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* Class      : BundleDTO
* Description: DTO model of Bundle payload.
*
* @author Edson Martins
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class BundleDTO extends HotfixDTO {
	@NotNull
	private Set<BugDTO> childBugs;
}
