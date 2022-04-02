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
public class UserController {

	private final UserRepository repo;
	private UserModelAssembler assembler;
	
	public UserController(UserRepository repository, UserModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> all()
	{
		List<EntityModel<User>> users = repo.findAll()
	            .stream()
	            .map(assembler::toModel)
	            .collect(Collectors.toList());
		
		   return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> newUser(@RequestBody User newUser) {
	
	    EntityModel<User> entityModel = assembler.toModel(repo.save(newUser));
	
	    return ResponseEntity 
	            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	            .body(entityModel);
	}
	
	// Single item
	@GetMapping("/users/{id}")
	public EntityModel<User> one(@PathVariable Long id) {
	
	    User user = repo.findById(id) //
	            .orElseThrow(() -> new UserNotFoundException(id));
	
	    return assembler.toModel(user);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
	
	    User updatedUser = repo.findById(id)
	            .map(user -> {
	            	user.setAddress(newUser.getAddress());
	            	user.setDateTimeRegister(newUser.getDateTimeRegister());
	            	user.setFullName(newUser.getFullName());
	            	user.setUserName(newUser.getUserName());
	                return repo.save(user);
	            })
	            .orElseGet(() -> {
	                newUser.setUserID(id);
	                return repo.save(newUser);
	            });
	    
	    EntityModel<User> entityModel = assembler.toModel(updatedUser);
	    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	    
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
	    repo.deleteById(id);
	    return ResponseEntity.noContent().build();
	}

}

