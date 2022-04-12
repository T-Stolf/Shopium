package shopium.repository;

<<<<<<< HEAD
import java.util.Collection;
=======
>>>>>>> refs/remotes/origin/SyedBranch
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.hateoas.EntityModel;
=======
import org.springframework.data.repository.query.Param;
>>>>>>> refs/remotes/origin/SyedBranch
import org.springframework.stereotype.Repository;

import shopium.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
<<<<<<< HEAD

	List<Item> findByUserID(Long id);
	
=======
	
	List<Item> findByItemNameOrDescriptionContaining(@Param("n") String n, @Param("d") String d);
>>>>>>> refs/remotes/origin/SyedBranch
}
