package shopium.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.controller.UserAccountController;
import shopium.entity.UserAccount;

@Component
public class UserAccountModelAssembler implements RepresentationModelAssembler<UserAccount, EntityModel<UserAccount>> {

	@Override
	public EntityModel<UserAccount> toModel(UserAccount userAccount)
	{
		 return EntityModel.of(userAccount, 
				 linkTo(methodOn(UserAccountController.class).one(userAccount.getUserID())).withSelfRel(), linkTo(methodOn(UserAccountController.class).all()).withRel("user"));
	}
	
}
