package djoseffer.agregadorinvestimentos.repository;

import djoseffer.agregadorinvestimentos.entity.AccountStock;
import djoseffer.agregadorinvestimentos.entity.AccountStockId;
import djoseffer.agregadorinvestimentos.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
