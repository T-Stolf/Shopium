package shopium.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.controller.AdminController;
import shopium.entity.Admin;

@Component
public class AdminModelAssembler  implements RepresentationModelAssembler<Admin, EntityModel<Admin>> {
	
	@Override
	public EntityModel<Admin> toModel(Admin admin)
	{
		 return EntityModel.of(admin, 
				 linkTo(methodOn(AdminController.class).one(admin.getAID())).withSelfRel(), 
				 linkTo(methodOn(AdminController.class).all()).withRel("admin"));
	}


}
