package shopium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
