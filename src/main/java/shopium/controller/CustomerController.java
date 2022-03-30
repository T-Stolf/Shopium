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
	
	@GetMapping("/users")
	public CollectionModel<EntityModel<Customer>> all()
	{
		List<EntityModel<Customer>> customers = repo.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
		
		   return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
	}
	
	@PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody Customer newUser) {

        EntityModel<Customer> entityModel = assembler.toModel(repo.save(newUser));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/users/{uid}")
    public EntityModel<Customer> one(@PathVariable Long uid) {

        Customer employee = repo.findById(uid) //
                .orElseThrow(() -> new CustomerNotFoundException(uid));

        return assembler.toModel(employee);
    }

    @PutMapping("/users/{uid}")
    public ResponseEntity<?> replaceEmployee(@RequestBody Customer newUser, @PathVariable Long uid) {

        Customer updatedUser = repo.findById(uid)
                .map(user -> {
                    user.setUserName(newUser.getUserName()) ;
                    user.setAddress(newUser.getAddress());
                    user.setDateTimeRegister(newUser.getDateTimeRegister());
                    user.setFullName(newUser.getFullName());
                    user.setPassword(newUser.getPassword());
                    return repo.save(user);
                })
                .orElseGet(() -> {
                    newUser.setUID(uid);
                    return repo.save(newUser);
                });
        
        EntityModel<Customer> entityModel = assembler.toModel(updatedUser);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/users/{uid}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long uid) {
        repo.deleteById(uid);
        return ResponseEntity.noContent().build();
    }
	
}
