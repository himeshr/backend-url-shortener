package com.sd.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UrlShortenRequest implements Serializable {
	
//	public UrlShortenRequest() {
//		longUrl = null;
//	}
//	
//	public UrlShortenRequest(String longUrl) {
//		this.longUrl = longUrl;
//	}

	private static final long serialVersionUID = -4601368000024513212L;
	private String longUrl;
}
