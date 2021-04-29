package br.com.edson.spring.boot.hfinitializr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.edson.spring.boot.hfinitializr.domain.Hotfix;
import br.com.edson.spring.boot.hfinitializr.dto.HotfixDTO;
/**
* Interface  : HotfixMapper
* Description: MapStruct mapper from HotfixDTO to Hotfix model.
*
* @author Edson Martins
*/
@Mapper(componentModel = "spring")
public interface HotfixMapper {
	
	public static final HotfixMapper INSTANCE = Mappers.getMapper(HotfixMapper.class);
	Hotfix toHotfix(HotfixDTO hotfix);
}
