package shopium.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shopium.entity.UserAccount;
import shopium.repository.UserAccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserAccountRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException {
		
		UserAccount user = repo.findByUserName(UserName);
		
		if(user == null){
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CustomUserDetails(user);
	}
}