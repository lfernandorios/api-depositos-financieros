package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service;

import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model.TransactionSaveCmd;
import lombok.NonNull;

public interface TransactionService {

    Transaction create(@NonNull TransactionSaveCmd transactionToCreateCmd);

}
