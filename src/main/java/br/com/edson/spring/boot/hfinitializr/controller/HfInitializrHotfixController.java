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

import br.com.edson.spring.boot.hfinitializr.dto.HotfixDTO;
import br.com.edson.spring.boot.hfinitializr.http.custom.CustomMediaType;
import br.com.edson.spring.boot.hfinitializr.service.impl.HfInitializrHotfixService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/**
 * Controller : HfInitializrHotfixController
 * 
 * Description: This is the controller exposing the endpoint /hotfix that will generate
 * a simple hotfix archive to the client.
 *
 * @author Edson Martins
 */
@RestController
@RequestMapping("/hotfix")
@RequiredArgsConstructor
@Log4j2
public class HfInitializrHotfixController {
	
	private final HfInitializrHotfixService hotfixService;

	/**
	* Entry point to generate a file archiver representing the structure of
	* a simple hotfix, i.e, simple package addressing a particular
	* software problem solving.
	*
	* @param hotfixBody as structure with a simple bug info as bug id, title.
	* @return Byte[] as a zip archive
	* @throws IOException 
	*/
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(value = "Generate Hotfix")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,  produces = CustomMediaType.APPLICATION_ZIP_VALUE)
	public ResponseEntity<byte[]> hotfix(@Valid @RequestBody HotfixDTO hotfixBody) throws IOException {
		
		log.info  ("Hotfix process with id " + hotfixBody.getBugId() + ".");
		return ResponseEntity.ok(hotfixService.generate(hotfixBody).getData());
	}
}
