package shopium.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shopium.entity.Admin;
import shopium.repository.AdminRepository;

@Service
public class CustomAdminDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException {
		
		Admin admin = repo.findByUserName(UserName);
		
		if(admin == null){
			throw new UsernameNotFoundException("Admin not found");
		}
		
		return new CustomAdminDetails(admin);
	}
}
