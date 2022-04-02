package shopium.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shopium.entity.*;
import shopium.assembler.*;
import shopium.repository.*;
import shopium.exception.*;

@RestController
public class OrderItemController {

	private OrderItemRepository repo;
	private OrderItemModelAssembler assembler;
	
	public OrderItemController(OrderItemRepository repository, OrderItemModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	@GetMapping("/orderitems")
	public CollectionModel<EntityModel<OrderItem>> all()
	{
		List<EntityModel<OrderItem>> orderitems = repo.findAll()
	            .stream()
	            .map(assembler::toModel)
	            .collect(Collectors.toList());
		
		   return CollectionModel.of(orderitems, linkTo(methodOn(OrderItemController.class).all()).withSelfRel());
	}
	
	// Single item
	@GetMapping("/orderitems/{id}")
	public EntityModel<OrderItem> one(@PathVariable Long id) {
	
	    OrderItem orderitem = repo.findById(id) //
	            .orElseThrow(() -> new OrderItemNotFoundException(id));
	
	    return assembler.toModel(orderitem);
	}
	
	@PostMapping("/orderitems")
	public ResponseEntity<?> newOrderItem(@RequestBody OrderItem newOrderItem) {
	
	    EntityModel<OrderItem> entityModel = assembler.toModel(repo.save(newOrderItem));
	
	    return ResponseEntity 
	            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	            .body(entityModel);
	}
	
	@PutMapping("/orderitem/{id}")
	public ResponseEntity<?> replaceOrderItem(@RequestBody OrderItem newOrderItem, @PathVariable Long id) {
	
	    OrderItem updatedOrderItem = repo.findById(id)
	            .map(orderitem -> {
	            	orderitem.setOrderID(newOrderItem.getOrderID());
	            	orderitem.setUserID(newOrderItem.getUserID());
	                return repo.save(orderitem);
	            })
	            .orElseGet(() -> {
	                newOrderItem.setOrderItemID(id);
	                return repo.save(newOrderItem);
	            });
	    
	    EntityModel<OrderItem> entityModel = assembler.toModel(updatedOrderItem);
	    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	    
	}
	
	@DeleteMapping("/orderitems/{id}")
	public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
	    repo.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
}
