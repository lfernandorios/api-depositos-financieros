package co.edu.udea.modelos.apidepositosfinancieros.component.account.service;


import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountGateway accountGateway;

    @Override
    public Account findById(Long id){
        Account transactionFounded = accountGateway.findById(id);
        return transactionFounded;
    }

}
