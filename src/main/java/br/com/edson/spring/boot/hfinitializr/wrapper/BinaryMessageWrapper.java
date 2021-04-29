package br.com.edson.spring.boot.hfinitializr.wrapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
* Class      : BinaryMessageWrapper
* Description: Wrapper class used to store the binary data, i.e, zip content.
*
* @author Edson Martins
*/
public class BinaryMessageWrapper {
	
	private static final String CACHE_CONTROL = "must-revalidate, post-check=0, pre-check=0";
	
	private HttpHeaders headers;
	private byte[] data;
	
	public BinaryMessageWrapper(String mediaType) {
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.parseMediaType(mediaType));
		this.headers.setCacheControl(CACHE_CONTROL);
	}
	
	public BinaryMessageWrapper(String mediaType, byte[] data) {
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.parseMediaType(mediaType));
		this.headers.setCacheControl(CACHE_CONTROL);
		
		this.data = data;
	}
	
	public BinaryMessageWrapper(String fileName, String mediaType, byte[] data) {
		this.headers = new HttpHeaders();
		this.headers.setContentType(MediaType.parseMediaType(mediaType));
		this.headers.setContentDispositionFormData(fileName, fileName);
		this.headers.setCacheControl(CACHE_CONTROL);
		
		this.data = data;
	}
	
	public void setFilename(String filename) {
        headers.setContentDispositionFormData(filename, filename);
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
