package com.eccpi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eccpi.controller.exception.ConflictException;
import com.eccpi.controller.exception.ForbiddenException;
import com.eccpi.controller.exception.NotFoundException;
import com.eccpi.entity.Account;
import com.eccpi.resource.AccountResource;
import com.eccpi.service.AccountService;
import com.eccpi.service.exception.DoesNotExistException;
import com.eccpi.service.exception.ExistsException;


@RestController
@RequestMapping("/rest")
public class AccountController {
	@Autowired
    private AccountService accountService;

//    @RequestMapping(value="/login",method = RequestMethod.POST)
//    //@PreAuthorize("permitAll")
//    public AccountResource login(@RequestBody AccountResource res){
//    		//@RequestParam(value="name", required = true) String name, 
//    		//@RequestParam(value="password", required = false) String password) {
//    	//AccountResource res = null;    
//    	Account account = accountService.findByAccountName(res.getName());
//        if(account != null) {
////            if(password != null) {
////                if(account.getPassword().equals(password)) {
//                	res = AccountResource.fromAccount(account);
////                }
////            }
//        }
//        if (res==null){
//        	throw new NotFoundException();
//        }
//        return res;
//    }
    
    @RequestMapping(value="/accounts",method = RequestMethod.POST)
    //@PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> createAccount(
            @RequestBody AccountResource sentAccount
    ) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = AccountResource.fromAccount(createdAccount);
            return new ResponseEntity<AccountResource>(res, HttpStatus.CREATED);
        } catch(ExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping( value="/accounts/{accountId}",
                method = RequestMethod.GET)
    //@PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable Long accountId
    ) {
        Account account = accountService.findAccount(accountId);
        if(account != null)
        {
            AccountResource res = AccountResource.fromAccount(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping( value="/currLogin",
            method = RequestMethod.GET)
	@PreAuthorize("permitAll")
    //@PreAuthorize("hasRole('CEO')")
	public ResponseEntity<AccountResource> getCurrentLoginAccount(
	Principal principal
	) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName();
    	//String name = principal.getName();
    	AccountResource res = null;    
    	Account account = accountService.findByAccountName(name);
        if(account != null) {
//            if(password != null) {
//                if(account.getPassword().equals(password)) {
        			res = AccountResource.fromAccount(account);
//                }
//            }
        }
        if (res==null){
        	throw new NotFoundException();
        }
        return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
	}
    
    @RequestMapping( value="/accounts/key", ///{accountId}
            method = RequestMethod.PATCH)
    //@PreAuthorize("permitAll")
	public ResponseEntity<AccountResource> updateKey(
	        //@PathVariable Long accountId
	) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Account account = accountService.findByAccountName(auth.getName());
	    if(account != null)
	    {
		    accountService.updateKey(account.getId());
	        AccountResource res = AccountResource.fromAccount(accountService.findAccount(account.getId()));
	        return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
	    }
	}

    @RequestMapping( value="/accounts/remoteKey", ///{accountId}
            method = RequestMethod.PATCH)
    //@PreAuthorize("permitAll")
	public void updateRemoteKey(
	        //@PathVariable Long accountId, 
	        @RequestBody AccountResource data
	) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Account account = accountService.findByAccountName(auth.getName());
	    if(account != null)
	    {
	    	accountService.updateRemoteKey(account.getId(),data.getPublicKey(),account.getKeyStore().getPrivateKey());
	    }else {
	    	throw new NotFoundException();
	    }
	}
  
}
