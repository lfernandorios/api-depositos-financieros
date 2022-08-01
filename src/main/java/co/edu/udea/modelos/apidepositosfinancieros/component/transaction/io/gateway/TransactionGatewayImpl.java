package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.gateway;

import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.repository.TransactionRepository;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.TransactionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionGatewayImpl implements TransactionGateway {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transactionToCreate){

        return transactionRepository.save(transactionToCreate);

    }

}
