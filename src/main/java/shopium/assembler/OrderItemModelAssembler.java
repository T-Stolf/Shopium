package shopium.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.controller.OrderController;
import shopium.entity.Item;
import shopium.entity.OrderItem;

@Component
public class OrderItemModelAssembler implements RepresentationModelAssembler<OrderItem, EntityModel<OrderItem>> {

	@Override
	public EntityModel<OrderItem> toModel(OrderItem orderitem)
	{
		 return EntityModel.of(orderitem, 
				 linkTo(methodOn(OrderController.class).one(orderitem.getOrderItemID())).withSelfRel(), linkTo(methodOn(OrderController.class).all()).withRel("orderitems"));
	}
	
}
