package shopium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopium.entity.Creator;

@Repository
public interface CreatorRepository extends JpaRepository<Creator, Long>
{

}
