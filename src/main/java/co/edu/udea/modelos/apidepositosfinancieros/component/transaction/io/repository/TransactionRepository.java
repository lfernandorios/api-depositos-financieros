package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.repository;

import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
