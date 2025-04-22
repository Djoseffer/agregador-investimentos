package djoseffer.agregadorinvestimentos.repository;

import djoseffer.agregadorinvestimentos.entity.Account;
import djoseffer.agregadorinvestimentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
