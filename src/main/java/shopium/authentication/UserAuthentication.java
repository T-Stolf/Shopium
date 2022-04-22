package shopium.authentication;

import org.springframework.security.core.context.SecurityContextHolder;


import shopium.exception.UserAccountNotFoundException;

public class UserAuthentication {

	private Object principal;
	private String username;
	private Long ID;
	
	private static UserAuthentication UAuth;
	
	public UserAuthentication() throws Exception {
		try {
		this.principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof CustomUserDetails) {
			 username = ((CustomUserDetails)principal).getUsername();
			 ID = ((CustomUserDetails)principal).getUserID();
			} else {
			 username = principal.toString();
			 ID = null;
			}
		}
		catch(Exception e)
		{
			throw new UserAccountNotFoundException(username + " err");
		}

	}
	
//	public static UserAuthentication getInstance()
//	{
//		if(UAuth == null)
//		{
//			try {
//				UAuth = new UserAuthentication();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return UAuth;
//	}
	

	public Long getID()
	{
		try {
			this.principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof CustomUserDetails) {
				 username = ((CustomUserDetails)principal).getUsername();
				 ID = ((CustomUserDetails)principal).getUserID();
				} else {
				 username = principal.toString();
			
				}
			}
			catch(Exception e)
			{
				ID = null;
			}

		return ID;
	}
	
	public String getUsername()
	{
		try {
			this.principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof CustomUserDetails) {
				 username = ((CustomUserDetails)principal).getUsername();
				 ID = ((CustomUserDetails)principal).getUserID();
				} else {
				 username = principal.toString();
				 ID = null;
				}
			}
			catch(Exception e)
			{
				throw new UserAccountNotFoundException(username + " err");
			}

		return username;
	}
	
	
}
