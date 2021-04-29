package br.com.edson.spring.boot.hfinitializr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.edson.spring.boot.hfinitializr.domain.Bundle;
import br.com.edson.spring.boot.hfinitializr.dto.BundleDTO;
/**
* Interface  : BundleMapper
* Description: MapStruct mapper from BundleDTO to Bundle model.
*
* @author Edson Martins
*/
@Mapper(componentModel = "spring")
public interface BundleMapper {
	
	public static final BundleMapper INSTANCE = Mappers.getMapper(BundleMapper.class);
	Bundle toBundle(BundleDTO bundle);

}
