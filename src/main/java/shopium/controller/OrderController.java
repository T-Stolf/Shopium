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
public class OrderController {

	private final OrderRepository repo;
	private OrderModelAssembler assembler;
	
	public OrderController(OrderRepository repository, OrderModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/orders")
	public CollectionModel<EntityModel<Order>> all()
	{
		List<EntityModel<Order>> orders = repo.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
		
		   return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
	}
	
	@PostMapping("/orders")
    public ResponseEntity<?> newOrder(@RequestBody Order newOrder) {

        EntityModel<Order> entityModel = assembler.toModel(repo.save(newOrder));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/orders/{oid}")
    public EntityModel<Order> one(@PathVariable Long oid) {

        Order order = repo.findById(oid) //
                .orElseThrow(() -> new OrderNotFoundException(oid));

        return assembler.toModel(order);
    }

    @PutMapping("/orders/{oid}")
    public ResponseEntity<?> replaceOrder(@RequestBody Order newOrder, @PathVariable Long oid) {

        Order updatedOrder = repo.findById(oid)
                .map(order-> {
                	order.setCost(newOrder.getCost());
                	order.setDateTime(newOrder.getDateTime());
                	order.setItemNum(newOrder.getItemNum());
                	order.setUID(newOrder.getUID());
                    return repo.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setOID(oid);
                    return repo.save(newOrder);
                });
        
        EntityModel<Order> entityModel = assembler.toModel(updatedOrder);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/orders/{oid}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long oid) {
        repo.deleteById(oid);
        return ResponseEntity.noContent().build();
    }
	
}

