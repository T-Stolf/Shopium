package shopium.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import shopium.adminListener.listener.InputListener;
import shopium.adminListener.publisher.Publisher;

@RestController
public class AdditionalAdminFunctionsController {

	@Autowired
	private InputListener inp;
	
	 @GetMapping("/admin/loginNumber")
	public String numLogins()
	{
		return "Number of Logins is: " + inp.getLoginNum();
	}
	
	 @GetMapping("/admin/Actions")
	public String totalActions()
	{
		return inp.getActions().toString();
	}
	
}
