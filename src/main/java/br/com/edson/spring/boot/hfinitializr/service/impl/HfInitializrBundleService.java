package br.com.edson.spring.boot.hfinitializr.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.edson.spring.boot.hfinitializr.component.PackageFixContentManagement;
import br.com.edson.spring.boot.hfinitializr.domain.Bundle;
import br.com.edson.spring.boot.hfinitializr.dto.BundleDTO;
import br.com.edson.spring.boot.hfinitializr.http.custom.CustomMediaType;
import br.com.edson.spring.boot.hfinitializr.mapper.BundleMapper;
import br.com.edson.spring.boot.hfinitializr.service.Packageable;
import br.com.edson.spring.boot.hfinitializr.wrapper.BinaryMessageWrapper;
import lombok.RequiredArgsConstructor;
/**
* Class      : HfInitializrBundleService
* Interface  : Packageable
* @param     : BundleDTO
* Description: Implementation of HfInitializrBundleService interface.
*
* @author Edson Martins
*/
@Service
@RequiredArgsConstructor
public class HfInitializrBundleService implements Packageable<BundleDTO> {

	private final BundleMapper bundleMapper;
	private final PackageFixContentManagement packageFixContentManagement;

	@Override
	public BinaryMessageWrapper generate(BundleDTO bundleDTO) throws IOException {
		
		Bundle bundle = bundleMapper.toBundle(bundleDTO);
		String zipFilename = bundle.getBugId() + ".zip";
		
		return new BinaryMessageWrapper(zipFilename, 
				CustomMediaType.APPLICATION_ZIP_VALUE,
				packageFixContentManagement
						.assembler(bundle.getBugId(), bundle.getTitle(), bundle.getNotes(), bundle.getChildBugs())
						.toByteArray());
	}

}
