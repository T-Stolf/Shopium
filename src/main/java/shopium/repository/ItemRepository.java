package shopium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
