package com.eccpi.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class KeyStore {
	@Column( length = 8000)
	private String privateKey;
	@Column( length = 8000)
    private String publicKey;
	@Column( length = 8000)
    private String remoteKey;
	@Column( length = 8000)
    private String sharedKey;
	
	public String getSharedKey() {
		return sharedKey;
	}
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getRemoteKey() {
		return remoteKey;
	}
	public void setRemoteKey(String remoteKey) {
		this.remoteKey = remoteKey;
	}
    
}
