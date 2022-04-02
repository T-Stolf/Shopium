package shopium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
