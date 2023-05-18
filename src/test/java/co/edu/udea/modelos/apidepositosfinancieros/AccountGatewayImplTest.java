package co.edu.udea.modelos.apidepositosfinancieros;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.io.gateway.AccountGatewayImpl;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.io.repository.AccountRepository;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class AccountGatewayImplTest {
    @Mock
    private AccountRepository mockAccountRepository;

    private AccountGatewayImpl accountGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        accountGateway = new AccountGatewayImpl(mockAccountRepository);
    }

    @Test
    public void findById_withExistingAccountId_shouldReturnAccount() {

        Long accountId = 1L;
        Account expectedAccount = Account.builder()
                .id(accountId)
                .saldo(500L)
                .build();

        when(mockAccountRepository.findById(anyLong())).thenReturn(Optional.of(expectedAccount));

        Account foundAccount = accountGateway.findById(accountId);

        assertEquals(expectedAccount, foundAccount);
    }

    @Test
    public void findById_withNonExistingAccountId_shouldThrowException() {

        Long nonExistingAccountId = 2L;

        when(mockAccountRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> accountGateway.findById(nonExistingAccountId));
    }


}
