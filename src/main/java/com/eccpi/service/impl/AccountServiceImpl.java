package com.eccpi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eccpi.crypto.ECCUtil;
import com.eccpi.crypto.PairKey;
import com.eccpi.entity.Account;
import com.eccpi.repository.AccountRepository;
import com.eccpi.service.exception.DoesNotExistException;
import com.eccpi.service.exception.ExistsException;


@Service(value="accountService")
@Transactional
public class AccountServiceImpl implements com.eccpi.service.AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccount(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepository.findByUsername(data.getUsername());
        if(account != null)
        {
            throw new ExistsException();
        }
        account = accountRepository.save(data);
        //updateKey(account.getId());
        return account;
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepository.findByUsername(name);
    }

	@Override
	public void updateKey(Long id) {
		Account account = accountRepository.findOne(id);
        if(account == null)
        {
            throw new DoesNotExistException();
        }
        PairKey pairKey = ECCUtil.generateECKeys();
        accountRepository.updateKey(id, pairKey.getPrivateKey(),pairKey.getPublicKey());
        return;
	}

	@Override
	public void updateRemoteKey(Long id, String remoteKey, String privateKey) {
		Account account = accountRepository.findOne(id);
        if(account == null)
        {
            throw new DoesNotExistException();
        }
        String sharedKey = ECCUtil.generateSharedSecret(privateKey,remoteKey);
        
        accountRepository.updateRemoteKey(id, remoteKey,sharedKey);
        
        return;
	}
}
