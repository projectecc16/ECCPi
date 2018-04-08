package com.eccpi.service;

import com.eccpi.entity.KeyStore;
import com.eccpi.resource.CryptoResource;

public interface CryptoService {
	public CryptoResource encrypt(KeyStore keyStore, CryptoResource decData);
    public CryptoResource decrypt(KeyStore keyStore, CryptoResource encData);
}
