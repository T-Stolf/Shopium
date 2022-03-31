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
public class AdminController {

	private final AdminRepository repo;
	private AdminModelAssembler assembler;
	
	public AdminController(AdminRepository repository, AdminModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/admins")
	public CollectionModel<EntityModel<Admin>> all()
	{
		List<EntityModel<Admin>> admins = repo.findAll()
	            .stream()
	            .map(assembler::toModel)
	            .collect(Collectors.toList());
		
		   return CollectionModel.of(admins, linkTo(methodOn(AdminController.class).all()).withSelfRel());
	}
	
	@PostMapping("/admins")
	public ResponseEntity<?> newAdmin(@RequestBody Admin newAdmin) {
	
	    EntityModel<Admin> entityModel = assembler.toModel(repo.save(newAdmin));
	
	    return ResponseEntity 
	            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	            .body(entityModel);
	}
	
	// Single item
	@GetMapping("/admins/{aid}")
	public EntityModel<Admin> one(@PathVariable Long aid) {
	
	    Admin admin = repo.findById(aid) //
	            .orElseThrow(() -> new AdminNotFoundException(aid));
	
	    return assembler.toModel(admin);
	}
	
	@PutMapping("/admin/{aid}")
	public ResponseEntity<?> replaceAdmin(@RequestBody Admin newAdmin, @PathVariable Long aid) {
	
	    Admin updatedAdmin = repo.findById(aid)
	            .map(admin -> {
	            	admin.setAddress(newAdmin.getAddress());
	            	admin.setDateTimeRegister(newAdmin.getDateTimeRegister());
	            	admin.setFullname(newAdmin.getFullname());
	            	admin.setPassword(newAdmin.getPassword());
	                return repo.save(admin);
	            })
	            .orElseGet(() -> {
	                newAdmin.setAID(aid);
	                return repo.save(newAdmin);
	            });
	    
	    EntityModel<Admin> entityModel = assembler.toModel(updatedAdmin);
	    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	    
	}
	
	@DeleteMapping("/admins/{aid}")
	public ResponseEntity<?> deleteAdmin(@PathVariable Long aid) {
	    repo.deleteById(aid);
	    return ResponseEntity.noContent().build();
	}

}
