package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.service.AccountService;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.CauseTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.StateTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.TypeTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model.TransactionSaveCmd;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionGateway transactionGateway;

    private final AccountService accountService;

    @Override
    public Transaction create(@NonNull TransactionSaveCmd command){


        Transaction transactionToCreate = TransactionSaveCmd.toModel(command);
        Account accountInDataBase = accountService.findById(command.getAccountId());
        transactionToCreate.setAccount(accountInDataBase);
        transactionToCreate.setDateTime(LocalDateTime.now());
        Map <String, String> resultadoUpdateSaldo = updateSaldo(accountInDataBase, command);
        transactionToCreate.setStateTransaction(StateTransaction.valueOf(resultadoUpdateSaldo.get("State")));
        transactionToCreate.setCauseTransaction(CauseTransaction.valueOf(resultadoUpdateSaldo.get("Cause")));

        return transactionGateway.save(transactionToCreate);
    }



    public Map <String, String> updateSaldo(Account account, TransactionSaveCmd command){

        Map<String, String> result = new HashMap <>();

        Long nuevoSaldo = null;

        result.put("State",StateTransaction.PENDIENTE.toString());
        result.put("Cause", CauseTransaction.STAND_BY.toString());

        Random rand = new Random();

        int max=4;
        int min=1;
        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum > 3){
            result.put("State",StateTransaction.FALLIDA.toString());
            result.put("Cause", CauseTransaction.TIMEOUT.toString());
            return result;
        }

        if (command.getAmount() == 0){
            result.put("State",StateTransaction.FALLIDA.toString());
            result.put("Cause", CauseTransaction.MONTO_CERO.toString());
            return result;
        }

        if (command.getTypeTransaction() == TypeTransaction.RETIRO) {
            if (account.getSaldo() - command.getAmount() < 0){
                result.put("State",StateTransaction.FALLIDA.toString());
                result.put("Cause", CauseTransaction.SIN_SALDO.toString());
                return result;
            } else nuevoSaldo = account.getSaldo() - command.getAmount();

        } else if (command.getTypeTransaction() == TypeTransaction.DEPOSITO) nuevoSaldo = account.getSaldo() + command.getAmount();

        result.put("State",StateTransaction.EXITOSA.toString());
        result.put("Cause", CauseTransaction.OK.toString());
        account.setSaldo(nuevoSaldo);
        return result;
    }

}
