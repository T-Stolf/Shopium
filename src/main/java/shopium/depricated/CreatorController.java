package shopium.depricated;

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
public class CreatorController {

	private final CreatorRepository repo;
	private CreatorModelAssembler assembler;
	
	public CreatorController(CreatorRepository repository, CreatorModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/creators")
	public CollectionModel<EntityModel<Creator>> all()
	{
		List<EntityModel<Creator>> creators = repo.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
		
		   return CollectionModel.of(creators, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
	}
	
	@PostMapping("/creators")
    public ResponseEntity<?> newCreator(@RequestBody Creator newCreator) {

        EntityModel<Creator> entityModel = assembler.toModel(repo.save(newCreator));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/creators/{cid}")
    public EntityModel<Creator> one(@PathVariable Long cid) {

        Creator creator = repo.findById(cid) //
                .orElseThrow(() -> new CustomerNotFoundException(cid));

        return assembler.toModel(creator);
    }

    @PutMapping("/creators/{cid}")
    public ResponseEntity<?> replaceCreator(@RequestBody Creator newCreator, @PathVariable Long cid) {

        Creator updatedCreator = repo.findById(cid)
                .map(creator -> {
                	creator.setAddress(newCreator.getAddress());
                	creator.setDateTimeRegister(newCreator.getDateTimeRegister());
                	creator.setItems(newCreator.getItems());
                	creator.setPassword(newCreator.getPassword());
                	creator.setUserName(newCreator.getUserName());
                    return repo.save(creator);
                })
                .orElseGet(() -> {
                    newCreator.setCID(cid);
                    return repo.save(newCreator);
                });
        
        EntityModel<Creator> entityModel = assembler.toModel(updatedCreator);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/creators/{cid}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long cid) {
        repo.deleteById(cid);
        return ResponseEntity.noContent().build();
    }
	
}

