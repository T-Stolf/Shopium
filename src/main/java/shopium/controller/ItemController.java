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
    @GetMapping("/items/{id}")
    public EntityModel<Item> one(@PathVariable Long id) {

        Item item = repo.findById(id) //
                .orElseThrow(() -> new ItemNotFoundException(id));

        return assembler.toModel(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> replaceItem(@RequestBody Item newItem, @PathVariable Long id) {

        Item updatedItem = repo.findById(id)
                .map(item -> {
                	item.setUserID(newItem.getUserID());
                	item.setDescription(newItem.getDescription());
                	item.setItemName(newItem.getItemName());
                	item.setPhoto(newItem.getPhoto());
                	item.setPrice(newItem.getPrice());
                	item.setStock(newItem.getStock());
                	item.setType(newItem.getType());
                    return repo.save(item);
                })
                .orElseGet(() -> {
                    newItem.setItemID(id);
                    return repo.save(newItem);
                });
        
        EntityModel<Item> entityModel = assembler.toModel(updatedItem);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
	
}