package co.edu.udea.modelos.apidepositosfinancieros.component.account.io.gateway;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.io.repository.AccountRepository;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.service.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountGatewayImpl implements AccountGateway {

    private final AccountRepository accountRepository;

    @Override
    public Account findById(Long id){

        final Account accountFounded = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrad con id "+id));

        return accountFounded;
    }


}
