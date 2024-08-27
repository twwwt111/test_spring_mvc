package fa.football.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.football.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	
	@Query(value = "SELECT COUNT(email) FROM account WHERE role <> 'ADMIN'", nativeQuery = true)
	int countAllUsers();
	
	Page<Account> findAllUsersByUserNameContaining(String name, Pageable pageable);
}
