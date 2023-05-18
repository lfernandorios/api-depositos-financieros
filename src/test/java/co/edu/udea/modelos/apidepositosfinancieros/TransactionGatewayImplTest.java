package co.edu.udea.modelos.apidepositosfinancieros;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.gateway.TransactionGatewayImpl;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.repository.TransactionRepository;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TransactionGatewayImplTest {
    @Mock
    private TransactionRepository mockTransactionRepository;

    private TransactionGatewayImpl transactionGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionGateway = new TransactionGatewayImpl(mockTransactionRepository);
    }

    @Test
    public void save_shouldSaveTransaction() {

        Transaction transactionToCreate = Transaction.builder()
                .id(1L)
                .amount(100L)
                .build();

        when(mockTransactionRepository.save(transactionToCreate)).thenReturn(transactionToCreate);

        Transaction savedTransaction = transactionGateway.save(transactionToCreate);

        assertEquals(transactionToCreate, savedTransaction);
        verify(mockTransactionRepository).save(transactionToCreate);
    }
}
