package shopium.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Repository;

import shopium.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findByUserID(Long id);
	
}
