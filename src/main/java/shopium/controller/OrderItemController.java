package shopium.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shopium.entity.*;
import shopium.adminListener.publisher.Publisher;
import shopium.assembler.*;
import shopium.authentication.CustomUserDetails;
import shopium.authentication.UserAuthentication;
import shopium.repository.*;
import shopium.exception.*;

@RestController
public class OrderItemController {

	private OrderItemRepository repo;
	private OrderRepository Orepo;
	private OrderItemModelAssembler assembler;
	
	
	private UserAuthentication UAuth; 

	public OrderItemController(OrderItemRepository repository, OrderItemModelAssembler ass, OrderRepository orep)
	{
		this.repo = repository;
		this.assembler = ass;
		this.Orepo = orep;
	}
	
	@Autowired
	private Publisher Pub;
	
	//UserControls
	@GetMapping("/myOrderItems/{orderID}")
	public CollectionModel<EntityModel<OrderItem>> allForOrder(@PathVariable Long orderID)
	{
		
		Pub.Event("/myOrderItems");
		
		this.UAuth = UserAuthentication.getInstance();
		
		Long userID = UAuth.getID();
		
		List<OrderItem> unverifiedItemList = repo.findByOrderID(orderID);
		List<OrderItem> verifiedItemList = new ArrayList<OrderItem>();
		
		
		for(OrderItem OI: unverifiedItemList)
		{
			if(Orepo.getById(OI.getOrderID()).getUserID().equals(userID))
			{
				verifiedItemList.add(OI);
			}
		}
		
		List<EntityModel<OrderItem>> orderitems = 
				verifiedItemList
	            .stream()
	            .map(assembler::toModel)
	            .collect(Collectors.toList());
		
		   return CollectionModel.of(orderitems, linkTo(methodOn(OrderItemController.class).all()).withSelfRel());
	}
	
	@PostMapping("/myOrderItems")
	public ResponseEntity<?> newItemForOrder(@RequestBody OrderItem newOrderItem) {
	
		Pub.Event("/myOrderItems");
		
		this.UAuth = UserAuthentication.getInstance();
		
		Long userID = UAuth.getID();
		
		
		
		if(Orepo.getById(newOrderItem.getOrderID()).getUserID().equals(userID))
		{
	    EntityModel<OrderItem> entityModel = assembler.toModel(repo.save(newOrderItem));
	    	return ResponseEntity 
	            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	            .body(entityModel);
		}
		else
			return null;
	}
	
	
	@DeleteMapping("/myOrderItems/{orderItemID}")
	public ResponseEntity<?> deleteItemFromOrder(@PathVariable Long orderItemID) {
	    
		Pub.Event("/myOrderItems");
		
		this.UAuth = UserAuthentication.getInstance();
		
		Long userID = UAuth.getID();
		
		if(Orepo.getById(repo.getById(orderItemID).getOrderID()).getUserID().equals(userID))
		{
			repo.deleteById(orderItemID);
		    return ResponseEntity.noContent().build();
		}
		else
		{
			throw new OrderItemNotFoundException(orderItemID);
		}
		
	}

	
	
	
	//Admin Controls
	@GetMapping("/admin/orderItems")
	public CollectionModel<EntityModel<OrderItem>> all()
	{
		List<EntityModel<OrderItem>> orderitems = repo.findAll()
	            .stream()
	            .map(assembler::toModel)
	            .collect(Collectors.toList());
		
		   return CollectionModel.of(orderitems, linkTo(methodOn(OrderItemController.class).all()).withSelfRel());
	}
	
	// Single item
	@GetMapping("/admin/orderItems/{id}")
	public EntityModel<OrderItem> one(@PathVariable Long id) {
	
	    OrderItem orderitem = repo.findById(id) //
	            .orElseThrow(() -> new OrderItemNotFoundException(id));
	
	    return assembler.toModel(orderitem);
	}
	
	@PostMapping("/admin/orderItems")
	public ResponseEntity<?> newOrderItem(@RequestBody OrderItem newOrderItem) {
	
	    EntityModel<OrderItem> entityModel = assembler.toModel(repo.save(newOrderItem));
	
	    return ResponseEntity 
	            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	            .body(entityModel);
	}
	
	@PutMapping("/admin/orderItems/{id}")
	public ResponseEntity<?> replaceOrderItem(@RequestBody OrderItem newOrderItem, @PathVariable Long id) {
	
	    OrderItem updatedOrderItem = repo.findById(id)
	            .map(orderitem -> {
	            	orderitem.setOrderID(newOrderItem.getOrderID());
	            	orderitem.setItemID(newOrderItem.getItemID());
	                return repo.save(orderitem);
	            })
	            .orElseGet(() -> {
	                newOrderItem.setOrderItemID(id);
	                return repo.save(newOrderItem);
	            });
	    
	    EntityModel<OrderItem> entityModel = assembler.toModel(updatedOrderItem);
	    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	    
	}
	
	@DeleteMapping("/admin/orderItems/{id}")
	public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
	    repo.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
}
