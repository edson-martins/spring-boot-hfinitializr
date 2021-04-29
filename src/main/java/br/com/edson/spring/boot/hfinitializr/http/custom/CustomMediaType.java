package br.com.edson.spring.boot.hfinitializr.http.custom;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;
/**
 * Class       : CustomMediaType
 * 
 * Description : This is a override from MediaType to define
 *               a zip media type, since the super class MediaType
 *               doesn't have one. 
 *
 * @author Edson Martins
 */
public class CustomMediaType extends MediaType {
	private static final long serialVersionUID =
			8735184975626072409L;
	
	public static final MediaType APPLICATION_ZIP;
	public static final String APPLICATION_ZIP_VALUE = "application/zip";
	
	static {
		// Not using "valueOf' to avoid static init cost
		APPLICATION_ZIP = new MediaType("application", "zip");
	}
	
	public CustomMediaType(MediaType other, Charset charset) {
		super(other, charset);
	}
}
