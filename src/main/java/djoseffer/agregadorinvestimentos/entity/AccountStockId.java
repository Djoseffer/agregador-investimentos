package djoseffer.agregadorinvestimentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AccountStockId {

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "stock_id")
    private String stockId;
}
