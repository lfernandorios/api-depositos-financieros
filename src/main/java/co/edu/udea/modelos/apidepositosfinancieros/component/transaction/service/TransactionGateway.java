package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service;

import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import lombok.NonNull;

public interface TransactionGateway {

    Transaction save(@NonNull Transaction transactionToCreate);

}
