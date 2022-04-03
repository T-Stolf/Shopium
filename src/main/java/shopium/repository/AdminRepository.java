package shopium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	Admin findByUserName(String UserName);

}
