package swp391.SPS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.SPS.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

}
