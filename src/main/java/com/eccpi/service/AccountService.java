package com.eccpi.service;

import com.eccpi.entity.Account;

public interface AccountService {
    public Account findAccount(Long id);
    public Account createAccount(Account data);
    public Account findByAccountName(String name);
    public void updateKey(Long id);
    public void updateRemoteKey(Long id, String remoteKey, String privateKey);
}
