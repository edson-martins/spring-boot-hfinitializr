package br.com.edson.spring.boot.hfinitializr.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* Class      : HotfixDTO
* Description: DTO model of Hotfix payload.
*
* @author Edson Martins
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class HotfixDTO extends BugDTO {
	private List<NoteDTO> notes;
}
