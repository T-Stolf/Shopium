package shopium.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import shopium.controller.ItemController;
import shopium.entity.Item;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<Item, EntityModel<Item>> {
	
	@Override
	public EntityModel<Item> toModel(Item item)
	{
		 return EntityModel.of(item, 
				 linkTo(methodOn(ItemController.class).one(item.getIID())).withSelfRel(), linkTo(methodOn(ItemController.class).all()).withRel("items"));
	}

}

