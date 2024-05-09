package com.security.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.security.entity.Account;
import com.security.repository.AccountRepository;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
		List<Account> list=accountRepository.findByUsername(username);
		
		System.out.println("Working");
		
		if(list.size()>0) {
			if(passwordEncoder.matches(password, list.get(0).getPassword())) {
				List<GrantedAuthority> authorities=new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(list.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username,password ,authorities);
						
			}else {
				throw new BadCredentialsException("Invalid password");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	
	
}
