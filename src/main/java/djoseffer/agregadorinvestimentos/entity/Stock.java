package djoseffer.agregadorinvestimentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_stocks")
public class Stock {

    @Id
    @Column(name = "stock_id")
    private String stockId;

    @Column(name = "description")
    private String description;
}
