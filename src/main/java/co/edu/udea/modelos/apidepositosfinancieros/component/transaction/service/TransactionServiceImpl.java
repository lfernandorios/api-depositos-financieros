package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.service.AccountService;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.TypeTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model.TransactionSaveCmd;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionGateway transactionGateway;

    private final AccountService accountService;

    @Override
    public Transaction create(@NonNull TransactionSaveCmd command){

        Account accountInDataBase = accountService.findById(command.getAccountId());
        Transaction transactionToCreate = TransactionSaveCmd.toModel(command);
        transactionToCreate.setAccount(accountInDataBase);
        transactionToCreate.setDateTime(LocalDateTime.now());
        updateSaldo(accountInDataBase, command);
        return transactionGateway.save(transactionToCreate);
    }

    private void updateSaldo(Account account, TransactionSaveCmd command){

        if (command.getTypeTransaction() == TypeTransaction.RETIRO){
            Long nuevoSaldo = account.getSaldo() - command.getAmount();

            if (nuevoSaldo < 0){
                throw new RuntimeException("No tiene saldo suficiente para la transacción solicitada");
            }

            account.setSaldo(nuevoSaldo);
        }

    }

}
