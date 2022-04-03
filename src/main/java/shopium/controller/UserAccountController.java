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
public class UserAccountController {

	private final UserAccountRepository repo;
	private UserAccountModelAssembler assembler;
	
	public UserAccountController(UserAccountRepository repository, UserAccountModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/userAccounts")
	public CollectionModel<EntityModel<UserAccount>> all()
	{
		List<EntityModel<UserAccount>> userAccounts = repo.findAll()
	            .stream()
	            .map(assembler::toModel)
	            .collect(Collectors.toList());
		
		   return CollectionModel.of(userAccounts, linkTo(methodOn(UserAccountController.class).all()).withSelfRel());
	}
	
	@PostMapping("/userAccounts")
	public ResponseEntity<?> newUser(@RequestBody UserAccount newUser) {
	
	    EntityModel<UserAccount> entityModel = assembler.toModel(repo.save(newUser));
	
	    return ResponseEntity 
	            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	            .body(entityModel);
	}
	
	// Single item
	@GetMapping("/userAccounts/{id}")
	public EntityModel<UserAccount> one(@PathVariable Long id) {
	
	    UserAccount userAccount = repo.findById(id) //
	            .orElseThrow(() -> new UserAcccountNotFoundException(id));
	
	    return assembler.toModel(userAccount);
	}
	
	@PutMapping("/userAccounts/{id}")
	public ResponseEntity<?> replaceUser(@RequestBody UserAccount newUser, @PathVariable Long id) {
	
	    UserAccount updatedUser = repo.findById(id)
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
	    
	    EntityModel<UserAccount> entityModel = assembler.toModel(updatedUser);
	    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	    
	}
	
	@DeleteMapping("/userAccounts/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
	    repo.deleteById(id);
	    return ResponseEntity.noContent().build();
	}

}

