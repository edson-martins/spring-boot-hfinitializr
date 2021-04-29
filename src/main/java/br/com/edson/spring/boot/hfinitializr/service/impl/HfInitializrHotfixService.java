package br.com.edson.spring.boot.hfinitializr.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.edson.spring.boot.hfinitializr.component.PackageFixContentManagement;
import br.com.edson.spring.boot.hfinitializr.domain.Hotfix;
import br.com.edson.spring.boot.hfinitializr.dto.HotfixDTO;
import br.com.edson.spring.boot.hfinitializr.http.custom.CustomMediaType;
import br.com.edson.spring.boot.hfinitializr.mapper.HotfixMapper;
import br.com.edson.spring.boot.hfinitializr.service.Packageable;
import br.com.edson.spring.boot.hfinitializr.wrapper.BinaryMessageWrapper;
import lombok.RequiredArgsConstructor;
/**
* Class      : HfInitializrHotfixService
* Interface  : Packageable
* @param     : HotfixDTO
* Description: Implementation of HfInitializrHotfixService interface.
*
* @author Edson Martins
*/
@Service
@RequiredArgsConstructor
public class HfInitializrHotfixService implements Packageable<HotfixDTO> {

	private final HotfixMapper hotfixMapper;
	private final PackageFixContentManagement packageFixContentManagement;

	@Override
	public BinaryMessageWrapper generate(HotfixDTO hotfixDTO) throws IOException {
		
		Hotfix hotfix = hotfixMapper.toHotfix(hotfixDTO);
		String zipFilename = hotfix.getBugId() + ".zip";
		
		return new BinaryMessageWrapper(zipFilename, 
				CustomMediaType.APPLICATION_ZIP_VALUE,
				packageFixContentManagement
						.assembler(hotfix.getBugId(), hotfix.getTitle(), hotfix.getNotes(), null)
						.toByteArray());
	}

}
