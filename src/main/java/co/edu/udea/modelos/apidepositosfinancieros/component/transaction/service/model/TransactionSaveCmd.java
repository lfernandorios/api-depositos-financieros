package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model;


import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.TypeTransaction;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class TransactionSaveCmd {

    private TypeTransaction typeTransaction;

    private Long accountId;

    private Long amount;

    public static Transaction toModel(@NonNull TransactionSaveCmd transactionToCreateCmd){
        return Transaction.builder()
                .typeTransaction(transactionToCreateCmd.getTypeTransaction())
                .amount(transactionToCreateCmd.getAmount())
                .build();
    }

}
