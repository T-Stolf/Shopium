package shopium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.Order_;

@Repository
public interface OrderRecordRepository extends JpaRepository<Order_, Long> {

}
