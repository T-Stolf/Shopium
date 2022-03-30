package shopium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{

}
