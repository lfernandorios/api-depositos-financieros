package co.edu.udea.modelos.apidepositosfinancieros;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.service.AccountService;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.TransactionGateway;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.*;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.TransactionServiceImpl;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model.TransactionSaveCmd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class TransactionServiceImplTest {
    @Mock
    private TransactionGateway mockTransactionGateway;

    @Mock
    private AccountService mockAccountService;

    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionServiceImpl(mockTransactionGateway, mockAccountService);
    }


    @Test
    public void updateSaldo_withValidCommand_shouldReturnSuccessfulResult() {

        Account mockAccount = Account.builder()
                .id(1L)
                .saldo(500L)
                .build();

        TransactionSaveCmd command = TransactionSaveCmd.builder()
                .typeTransaction(TypeTransaction.DEPOSITO)
                .amount(100L)
                .build();

        Map<String, String> result = transactionService.updateSaldo(mockAccount, command);

        assertEquals(StateTransaction.EXITOSA.toString(), result.get("State"));
        assertEquals(CauseTransaction.OK.toString(), result.get("Cause"));
    }


}
