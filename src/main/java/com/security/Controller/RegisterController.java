package com.security.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.security.entity.Account;
import com.security.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class RegisterController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("register")
	public ResponseEntity<?> postMethodName(@RequestBody Account account) {
		ResponseEntity<String> response = null;
		System.out.println("Registering..........");
		try {
			String encodedpassword=passwordEncoder.encode(account.getPassword());
			account.setPassword(encodedpassword);
			Account account2=accountRepository.save(account);
			
			if(account.getId()>0) {
				response= ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
			}
			
		} catch (Exception e) {
			response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An Error Occured due to "+e.getMessage());
		}
		
		return response;
	}
	

}

