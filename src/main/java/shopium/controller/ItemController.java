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
public class ItemController {

	private final ItemRepository repo;
	private ItemModelAssembler assembler;
	
	public ItemController(ItemRepository repository, ItemModelAssembler ass)
	{
		this.repo = repository;
		this.assembler = ass;
	}
	
	@GetMapping("/items")
	public CollectionModel<EntityModel<Item>> all()
	{
		List<EntityModel<Item>> items = repo.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
		
		   return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
	}
	
	@PostMapping("/items")
    public ResponseEntity<?> newItem(@RequestBody Item newItem) {

        EntityModel<Item> entityModel = assembler.toModel(repo.save(newItem));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/items/{iid}")
    public EntityModel<Item> one(@PathVariable Long iid) {

        Item item = repo.findById(iid) //
                .orElseThrow(() -> new ItemNotFoundException(iid));

        return assembler.toModel(item);
    }

    @PutMapping("/items/{iid}")
    public ResponseEntity<?> replaceItem(@RequestBody Item newItem, @PathVariable Long iid) {

        Item updatedItem = repo.findById(iid)
                .map(item -> {
                	item.setCID(newItem.getCID());
                	item.setDescription(newItem.getDescription());
                	item.setIName(newItem.getIName());
                	item.setPhoto(newItem.getPhoto());
                	item.setPrice(newItem.getPrice());
                	item.setStock(newItem.getStock());
                	item.setType(newItem.getType());
                    return repo.save(item);
                })
                .orElseGet(() -> {
                    newItem.setIID(iid);
                    return repo.save(newItem);
                });
        
        EntityModel<Item> entityModel = assembler.toModel(updatedItem);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/items/{iid}")
    public ResponseEntity<?> deleteItem(@PathVariable Long iid) {
        repo.deleteById(iid);
        return ResponseEntity.noContent().build();
    }
	
}
