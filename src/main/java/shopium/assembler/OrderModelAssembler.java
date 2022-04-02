
package shopium.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.entity.Status;
import shopium.controller.OrderController;
import shopium.entity.Order_;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order_, EntityModel<Order_>> {

	@Override
	public EntityModel<Order_> toModel(Order_ order_)
	{
		 EntityModel<Order_> orderModel = EntityModel.of(order_, 
				 linkTo(methodOn(OrderController.class).one(order_.getOrderID())).withSelfRel(), linkTo(methodOn(OrderController.class).all()).withRel("order"));
	
	
		 if (order_.getStatus() == Status.IN_PROGRESS) {
		      orderModel.add(linkTo(methodOn(OrderController.class).cancelOrder(order_.getOrderID())).withRel("cancel"));
		      orderModel.add(linkTo(methodOn(OrderController.class).completeOrder(order_.getOrderID())).withRel("complete"));
		    }
		 
		 return orderModel;
	}
	
}
