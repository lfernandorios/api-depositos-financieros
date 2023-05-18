package co.edu.udea.modelos.apidepositosfinancieros;

import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.web.TransactionController;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.web.model.TransactionSaveRequest;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.TypeTransaction;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.TransactionService;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model.TransactionSaveCmd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class TransactionControllerTest {
    @Mock
    private TransactionService mockTransactionService;

    private TransactionController transactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionController = new TransactionController(mockTransactionService);
    }

    @Test
    public void save_shouldCreateTransactionAndReturnAcceptedResponse() {

        TransactionSaveRequest request =  TransactionSaveRequest.builder()
                .accountId(1L)
                .typeTransaction(TypeTransaction.DEPOSITO)
                .amount(100L)
                .build();

        TransactionSaveCmd transactionSaveCmd = TransactionSaveRequest.toModel(request);

        ResponseEntity<Void> response = transactionController.save(request);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(mockTransactionService).create(transactionSaveCmd);
    }

}
