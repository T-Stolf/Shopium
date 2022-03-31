package shopium.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.controller.CustomerController;
import shopium.controller.OrderController;

import shopium.entity.Order;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
	
	
	@Override
	public EntityModel<Order> toModel(Order order)
	{
		 return EntityModel.of(order, 
				 linkTo(methodOn(OrderController.class).one(order.getOID())).withSelfRel(), linkTo(methodOn(OrderController.class).all()).withRel("orders"));
	}

}
