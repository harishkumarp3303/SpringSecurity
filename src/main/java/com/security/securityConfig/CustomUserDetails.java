//package com.security.securityConfig;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.security.entity.Account;
//import com.security.repository.AccountRepository;
//
//@Service
//public class CustomUserDetails implements UserDetailsService{
//	
//	@Autowired
//	private AccountRepository accountRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		String Username;
//		String password;
//		List<GrantedAuthority> authorities;
//		
//		List<Account> account=accountRepository.findByUsername(username);
//		
//		if(account.size()==0) {
//			throw new UsernameNotFoundException("User Details not found for the user"+username);
//		}else {
//			Username=account.get(0).getUsername();
//			password=account.get(0).getPassword();
//			authorities=new ArrayList<>();
//			authorities.add(new SimpleGrantedAuthority(account.get(0).getRole())); 
//		}
//		return new User(Username, password, authorities);
//		
//	}
//
//}
