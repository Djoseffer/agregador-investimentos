package djoseffer.agregadorinvestimentos.repository;

import djoseffer.agregadorinvestimentos.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
