package shopium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.Order_;

@Repository
public interface OrderRepository extends JpaRepository<Order_, Long> {

	List<Order_> findByUserID(Long id);
}
