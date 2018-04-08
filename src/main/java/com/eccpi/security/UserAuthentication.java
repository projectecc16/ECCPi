package com.eccpi.security;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.eccpi.entity.Account;


public class UserAuthentication implements Authentication {

	private final Account account;
	private boolean authenticated = true;

	public UserAuthentication(Account account) {
		this.account = account;
	}

	@Override
	public String getName() {
		return account.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return account.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return account.getPassword();
	}

	@Override
	public Account getDetails() {
		return account;
	}

	@Override
	public Object getPrincipal() {
		return account.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

}
