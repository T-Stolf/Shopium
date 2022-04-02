package shopium.depricated;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CreatorModelAssembler implements RepresentationModelAssembler<Creator, EntityModel<Creator>> {
	
	@Override
	public EntityModel<Creator> toModel(Creator creator)
	{
		 return EntityModel.of(creator, 
				 linkTo(methodOn(CreatorController.class).one(creator.getCID())).withSelfRel(), linkTo(methodOn(CreatorController.class).all()).withRel("creators"));
	}

}
