package com.eccpi.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.eccpi.entity.Account;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResource {
    private String name;

    private String password;
    
//    private String role;
//    
//    private String department;

    
    private String publicKey;
    
    private String remoteKey;
    
//    private boolean encryption;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
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

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		if(role==null){
//			role="Worker";
//		}
//		this.role = role;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//
//	public boolean getEncryption() {
//		return encryption;
//	}
//
//	public void setEncryption(boolean encryption) {
//		this.encryption = encryption;
//	}

	public Account toAccount() {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
//        account.setRole(role);
//        account.setDepartment(department);
//        account.setEncryption(encryption);
        return account;
    }
	
	public static AccountResource fromAccount(Account account) {
		AccountResource accountRes = new AccountResource();
		accountRes.setName(account.getUsername());
		accountRes.setPassword(account.getPassword());
//		accountRes.setRole(account.getRole());
//		accountRes.setDepartment(account.getDepartment());
//		accountRes.setEncryption(account.isEncryption());
		if(account.getKeyStore()!=null){
		accountRes.setPublicKey(account.getKeyStore().getPublicKey());
		}
        return accountRes;
    }
}
