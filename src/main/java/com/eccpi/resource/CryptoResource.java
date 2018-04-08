package com.eccpi.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoResource {
	
	private String crypta;
	public CryptoResource(){
		
	}
	public CryptoResource(String crypta) {
		super();
		this.crypta = crypta;
	}
	public String getCrypta() {
		return crypta;
	}
	public void setCrypta(String crypta) {
		this.crypta = crypta;
	}
}
