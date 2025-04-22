package djoseffer.agregadorinvestimentos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_accountstocks")
public class AccountStock {

    @EmbeddedId
    private AccountStockId accountStockId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @MapsId("stockId")
    private Stock stock;

    @Column(name = "quantity")
    private int quantity;
}
