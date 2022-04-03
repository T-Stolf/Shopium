package shopium.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

	private final OrderRecordRepository repo;
	private OrderModelAssembler assembler;
	
	public OrderController(OrderRecordRepository repository, OrderModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/orders")
	public CollectionModel<EntityModel<Order_>> all()
	{
		List<EntityModel<Order_>> order_ = repo.findAll().stream() //
		        .map(assembler::toModel) //
		        .collect(Collectors.toList());

		    return CollectionModel.of(order_, //
		        linkTo(methodOn(OrderController.class).all()).withSelfRel());
		    }
	
    // Single item
    @GetMapping("/orders/{id}")
    public EntityModel<Order_> one(@PathVariable Long id) {

        Order_ order_ = repo.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order_);
    }
	
	@PostMapping("/orders")
    public ResponseEntity<?> newOrder(@RequestBody Order_ order_) {

		order_.setStatus(Status.IN_PROGRESS);
		Order_ newOrder = repo.save(order_);

        return ResponseEntity 
                .created(linkTo(methodOn(OrderController.class).one(newOrder.getOrderID())).toUri()) //
                .body(assembler.toModel(newOrder));
    }

    @PutMapping("/orders/{id}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable Long id) {

    	Order_ order_ = repo.findById(id) //
    	        .orElseThrow(() -> new OrderNotFoundException(id));

    	    if (order_.getStatus() == Status.IN_PROGRESS) {
    	      order_.setStatus(Status.COMPLETED);
    	      return ResponseEntity.ok(assembler.toModel(repo.save(order_)));
    	    }

    	    return ResponseEntity //
    	        .status(HttpStatus.METHOD_NOT_ALLOWED) //
    	        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
    	        .body(Problem.create() //
    	            .withTitle("Method not allowed") //
    	            .withDetail("You can't complete an order that is in the " + order_.getStatus() + " status"));
    }
    
    

    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
    	
    	 Order_ order_ = repo.findById(id) //
    		        .orElseThrow(() -> new OrderNotFoundException(id));

    		    if (order_.getStatus() == Status.IN_PROGRESS) {
    		      order_.setStatus(Status.CANCELLED);
    		      return ResponseEntity.ok(assembler.toModel(repo.save(order_)));
    		    }

    		    return ResponseEntity //
    		        .status(HttpStatus.METHOD_NOT_ALLOWED) //
    		        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
    		        .body(Problem.create() //
    		            .withTitle("Method not allowed") //
    		            .withDetail("You can't cancel an order that is in the " + order_.getStatus() + " status"));
    }
    
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id)
    {
    	   repo.deleteById(id);
           return ResponseEntity.noContent().build();
    }
    
    
	
}

