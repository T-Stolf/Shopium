
package shopium.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.entity.Status;
import shopium.controller.OrderController;
import shopium.entity.Order;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

	@Override
	public EntityModel<Order> toModel(Order order)
	{
		 EntityModel<Order> orderModel = EntityModel.of(order, 
				 linkTo(methodOn(OrderController.class).one(order.getOrderID())).withSelfRel(), linkTo(methodOn(OrderController.class).all()).withRel("order"));
	
	
		 if (order.getStatus() == Status.IN_PROGRESS) {
		      orderModel.add(linkTo(methodOn(OrderController.class).cancelOrder(order.getOrderID())).withRel("cancel"));
		      orderModel.add(linkTo(methodOn(OrderController.class).completeOrder(order.getOrderID())).withRel("complete"));
		    }
		 
		 return orderModel;
	}
	
}
