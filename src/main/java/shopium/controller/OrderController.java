package shopium.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
import shopium.authentication.UserAuthentication;
import shopium.repository.*;
import shopium.exception.*;

@RestController
public class OrderController {

	private final OrderRepository repo;
	private OrderModelAssembler assembler;
	
	
	private UserAuthentication UAuth; 

	
	public OrderController(OrderRepository repository, OrderModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
		
	}
	
	//User Functions
	@GetMapping("/myOrders")
	public CollectionModel<EntityModel<Order_>> allMyOrders()
	{
		this.UAuth = UserAuthentication.getInstance();
		
		Long userID = UAuth.getID();
		
		List<EntityModel<Order_>> order_ = repo.findByUserID(userID).stream()
				.map(assembler::toModel) //
		        .collect(Collectors.toList());


		    return CollectionModel.of(order_, //
		        linkTo(methodOn(OrderController.class).all()).withSelfRel());
		    }
	
	
	// Single item
    @GetMapping("/myOrders/{oid}")
    public EntityModel<Order_> myOrder( @PathVariable Long oid) {
    	
    	this.UAuth = UserAuthentication.getInstance();
    	
		Long userID = UAuth.getID();
		
        Order_ order_ = repo.findById(oid) //
                .orElseThrow(() -> new OrderNotFoundException(oid));
        
        if(order_.getUserID() != userID)
        {
        	throw new OrderNotFoundException(oid);
        }

        return assembler.toModel(order_);
    }
	@PostMapping("/myOrders")
    public ResponseEntity<?> myNewOrder(@RequestBody Order_ order) {
		
		this.UAuth = UserAuthentication.getInstance();
		
		Long userID = UAuth.getID();
		
		if(order.getUserID() != userID)
		{
			throw new OrderNotFoundException(order.getOrderID());
		}
		
		order.setStatus(Status.IN_PROGRESS);
		Order_ newOrder = repo.save(order);

		return ResponseEntity 
            .created(linkTo(methodOn(OrderController.class).one(newOrder.getOrderID())).toUri()) //
            .body(assembler.toModel(newOrder));
    }
	
	
    @PutMapping("/myOrders/{id}/complete")
    public ResponseEntity<?> completeMyOrder(@PathVariable Long id) {

    	this.UAuth = UserAuthentication.getInstance();
    	
		Long userID = UAuth.getID();
    	
    	Order_ order_ = repo.findById(id) //
    	        .orElseThrow(() -> new OrderNotFoundException(id));
    	
    	if(order_.getUserID() != userID)
		{
    		throw new OrderNotFoundException(id);
		}

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
    
    

    @PutMapping("/myOrders/{id}/cancel")
    public ResponseEntity<?> cancelMyOrder(@PathVariable Long id) {
    	
    	this.UAuth = UserAuthentication.getInstance();
    	
		Long userID = UAuth.getID();
    	
    	 Order_ order_ = repo.findById(id) //
    		        .orElseThrow(() -> new OrderNotFoundException(id));

    	 if(order_.getUserID() != userID)
 		{
     		throw new OrderNotFoundException(id);
 		}

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
	
	
	
    
    //Admin Functions
	@GetMapping("/admin/orders")
	public CollectionModel<EntityModel<Order_>> all()
	{
		List<EntityModel<Order_>> order_ = repo.findAll().stream() //
		        .map(assembler::toModel) //
		        .collect(Collectors.toList());

		    return CollectionModel.of(order_, //
		        linkTo(methodOn(OrderController.class).all()).withSelfRel());
		    }
	
    // Single item
    @GetMapping("/admin/orders/{id}")
    public EntityModel<Order_> one(@PathVariable Long id) {

        Order_ order_ = repo.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order_);
    }
	
	@PostMapping("/admin/orders")
    public ResponseEntity<?> newOrder(@RequestBody Order_ order_) {

		order_.setStatus(Status.IN_PROGRESS);
		Order_ newOrder = repo.save(order_);

        return ResponseEntity 
                .created(linkTo(methodOn(OrderController.class).one(newOrder.getOrderID())).toUri()) //
                .body(assembler.toModel(newOrder));
    }

    @PutMapping("/admin/orders/{id}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable Long id) {

    	Order_ order_ = repo.findById(id) //
    	        .orElseThrow(() -> new OrderNotFoundException(id));

    	System.out.println("woo joo");
    	    if (order_.getStatus() == Status.IN_PROGRESS) {
    	      order_.setStatus(Status.COMPLETED);
    	      System.out.println("wee woo");
    	      return ResponseEntity.ok(assembler.toModel(repo.save(order_)));
    	    }

    	    return ResponseEntity //
    	        .status(HttpStatus.METHOD_NOT_ALLOWED) //
    	        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
    	        .body(Problem.create() //
    	            .withTitle("Method not allowed") //
    	            .withDetail("You can't complete an order that is in the " + order_.getStatus() + " status"));
    }
    
    

    @PutMapping("/admin/orders/{id}/cancel")
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
    
    @DeleteMapping("/admin/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id)
    {
    	   repo.deleteById(id);
           return ResponseEntity.noContent().build();
    }
    
    
	
}

