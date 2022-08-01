package co.edu.udea.modelos.apidepositosfinancieros.component.account.io.repository;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {
}
