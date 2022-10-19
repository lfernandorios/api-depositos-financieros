package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.web.model;

import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.CauseTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.StateTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.TypeTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model.TransactionSaveCmd;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class TransactionSaveRequest {

    private Long accountId;

    private TypeTransaction typeTransaction;

    private Long amount;


    public static TransactionSaveCmd toModel(@NonNull  TransactionSaveRequest transactionSaveRequest){
        return TransactionSaveCmd.builder()
                .accountId(transactionSaveRequest.getAccountId())
                .typeTransaction(transactionSaveRequest.getTypeTransaction())
                .amount(transactionSaveRequest.getAmount())
                .build();
    }

}
