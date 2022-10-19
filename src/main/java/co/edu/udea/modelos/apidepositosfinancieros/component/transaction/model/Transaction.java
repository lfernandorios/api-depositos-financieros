package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transaction")
    private TypeTransaction typeTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "amount")
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_transaction")
    private StateTransaction stateTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "cause_transaction")
    private CauseTransaction causeTransaction;
}
