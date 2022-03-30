package shopium.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.controller.CustomerController;
import shopium.entity.Customer;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
	
	@Override
	public EntityModel<Customer> toModel(Customer customer)
	{
		 return EntityModel.of(customer, 
				 linkTo(methodOn(CustomerController.class).one(customer.getUID())).withSelfRel(), linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
	}

}
