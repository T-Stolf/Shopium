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
public class CustomerController {
	
	private final CustomerRepository repo;
	private CustomerModelAssembler assembler;
	
	public CustomerController(CustomerRepository repository, CustomerModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/customers")
	public CollectionModel<EntityModel<Customer>> all()
	{
		List<EntityModel<Customer>> customers = repo.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
		
		   return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
	}
	
	@PostMapping("/customers")
    public ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) {

        EntityModel<Customer> entityModel = assembler.toModel(repo.save(newCustomer));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/customers/{uid}")
    public EntityModel<Customer> one(@PathVariable Long uid) {

        Customer customer = repo.findById(uid) //
                .orElseThrow(() -> new CustomerNotFoundException(uid));

        return assembler.toModel(customer);
    }

    @PutMapping("/customers/{uid}")
    public ResponseEntity<?> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long uid) {

        Customer updatedCustomer = repo.findById(uid)
                .map(customer -> {
                    customer.setUserName(newCustomer.getUserName()) ;
                    customer.setAddress(newCustomer.getAddress());
                    customer.setDateTimeRegister(newCustomer.getDateTimeRegister());
                    customer.setFullName(newCustomer.getFullName());
                    customer.setPassword(newCustomer.getPassword());
                    return repo.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setUID(uid);
                    return repo.save(newCustomer);
                });
        
        EntityModel<Customer> entityModel = assembler.toModel(updatedCustomer);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/customers/{uid}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long uid) {
        repo.deleteById(uid);
        return ResponseEntity.noContent().build();
    }
	
}
