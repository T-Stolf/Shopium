package shopium.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Repository;

import shopium.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	
	List<Item> findByItemNameOrDescriptionContaining(@Param("n") String n, @Param("d") String d);
	List<Item> findByPriceBetween(@Param("lower") int lower, @Param("upper") int upper);
	List<Item> findByUserID(@Param("n") Long n);
}
