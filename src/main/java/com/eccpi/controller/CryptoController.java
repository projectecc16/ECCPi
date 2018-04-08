package com.eccpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eccpi.controller.exception.NotFoundException;
import com.eccpi.entity.Account;
import com.eccpi.resource.CryptoResource;
import com.eccpi.service.AccountService;
import com.eccpi.service.CryptoService;


@Controller
@RequestMapping("/rest/crypto")
public class CryptoController {
	
	@Autowired
    private AccountService accountService;

	@Autowired
    private CryptoService cryptoService;
	
    @RequestMapping(value="/enc",method = RequestMethod.POST)
    //@PreAuthorize("permitAll")
    public ResponseEntity<CryptoResource> encrypt(@RequestBody CryptoResource decData) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Account account = accountService.findByAccountName(auth.getName());
	    if(account != null)
	    {
	    	CryptoResource encData = cryptoService.encrypt(account.getKeyStore(), decData);
	        return new ResponseEntity<CryptoResource>(encData, HttpStatus.OK);
	    } else {
	        throw new NotFoundException();
	    }
	    
    }
    
    @RequestMapping(value="/dec",method = RequestMethod.POST)
    //@PreAuthorize("permitAll")
    public ResponseEntity<CryptoResource> decrypt(@RequestBody CryptoResource encData) {
    	
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Account account = accountService.findByAccountName(auth.getName());
	    if(account != null)
	    {
	    	CryptoResource decData = cryptoService.decrypt(account.getKeyStore(), encData);
	        return new ResponseEntity<CryptoResource>(decData, HttpStatus.OK);
	    } else {
	        throw new NotFoundException();
	    }
    }
    
      
}
