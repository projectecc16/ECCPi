package com.eccpi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.eccpi.entity.Account;
import com.eccpi.service.AccountService;


@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService service;

    @Override
    public Account loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = service.findByAccountName(name);
        if(account == null) {
            throw new UsernameNotFoundException("no user found with " + name);
        }
        return account;
    }
}
