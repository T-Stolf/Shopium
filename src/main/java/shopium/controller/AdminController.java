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
	@GetMapping("/admins/{id}")
	public EntityModel<Admin> one(@PathVariable Long id) {
	
	    Admin admin = repo.findById(id) //
	            .orElseThrow(() -> new AdminNotFoundException(id));
	
	    return assembler.toModel(admin);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> replaceAdmin(@RequestBody Admin newAdmin, @PathVariable Long id) {
	
	    Admin updatedAdmin = repo.findById(id)
	            .map(admin -> {
	            	admin.setAddress(newAdmin.getAddress());
	            	admin.setDateTimeRegister(newAdmin.getDateTimeRegister());
	            	admin.setFullname(newAdmin.getFullname());
	                return repo.save(admin);
	            })
	            .orElseGet(() -> {
	                newAdmin.setAdminID(id);
	                return repo.save(newAdmin);
	            });
	    
	    EntityModel<Admin> entityModel = assembler.toModel(updatedAdmin);
	    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	    
	}
	
	@DeleteMapping("/admins/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
	    repo.deleteById(id);
	    return ResponseEntity.noContent().build();
	}

}
