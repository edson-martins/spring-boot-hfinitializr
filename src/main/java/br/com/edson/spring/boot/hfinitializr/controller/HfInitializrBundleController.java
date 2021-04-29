package br.com.edson.spring.boot.hfinitializr.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edson.spring.boot.hfinitializr.dto.BundleDTO;
import br.com.edson.spring.boot.hfinitializr.http.custom.CustomMediaType;
import br.com.edson.spring.boot.hfinitializr.service.impl.HfInitializrBundleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/**
 * Controller : HfInitializrBundleController
 * 
 * Description: This is the controller exposing the endpoint /bundle that will generate a
 * bundle hotfix archive to the client.
 *
 * @author Edson Martins
 */
@RestController
@RequestMapping("/bundle")
@RequiredArgsConstructor
@Log4j2
public class HfInitializrBundleController {
	
	private final HfInitializrBundleService bundleService;

	/**
	* Entry point to generate a zip archive representing the structure of
	* a bundle hotfix, i.e, cumulative package addressing software problems
	* solving.
	*
	* @param bundleBody as structure with a list of child bugs info as bugid, title, and childBugs set.
	* @return Byte[] as a zip archive
	* @throws IOException 
	*/
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(value = "Generate Bundle hotfix")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces = CustomMediaType.APPLICATION_ZIP_VALUE)
	public ResponseEntity<byte[]> bundle(@Valid @RequestBody BundleDTO bundleBody) throws IOException {
		
		log.info  ("Bundle process with id " + bundleBody.getBugId() + ".");
		return ResponseEntity.ok(bundleService.generate(bundleBody).getData());
	}
}
