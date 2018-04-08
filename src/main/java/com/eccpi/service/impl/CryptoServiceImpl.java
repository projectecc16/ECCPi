package com.eccpi.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eccpi.crypto.ECCUtil;
import com.eccpi.entity.KeyStore;
import com.eccpi.resource.CryptoResource;
import com.eccpi.service.CryptoService;
@Service(value="cryptoService")
@Transactional
public class CryptoServiceImpl implements CryptoService {

	@Override
	public CryptoResource encrypt(KeyStore keyStore,CryptoResource decData) {
		String key = ECCUtil.generateSharedSecret(keyStore.getPrivateKey(),
				keyStore.getRemoteKey());
		String value = ECCUtil.encryptString(key, decData.getCrypta());
		return new CryptoResource(value);
	}

	@Override
	public CryptoResource decrypt(KeyStore keyStore,CryptoResource encData) {
		String key = ECCUtil.generateSharedSecret(keyStore.getPrivateKey(),
				keyStore.getRemoteKey());
		String value = ECCUtil.decryptString(key, encData.getCrypta());
		return new CryptoResource(value);
	}
	

}
