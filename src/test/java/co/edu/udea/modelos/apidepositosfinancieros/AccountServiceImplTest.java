package co.edu.udea.modelos.apidepositosfinancieros;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.service.AccountGateway;
import co.edu.udea.modelos.apidepositosfinancieros.component.account.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {
    @Mock
    private AccountGateway mockAccountGateway;

    private AccountServiceImpl accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountServiceImpl(mockAccountGateway);
    }

    @Test
    public void findById_withExistingAccountId_shouldReturnAccount() {

        Long accountId = 1L;
        Account expectedAccount = Account.builder()
                .id(accountId)
                .saldo(500L)
                .build();

        when(mockAccountGateway.findById(anyLong())).thenReturn(expectedAccount);

        Account foundAccount = accountService.findById(accountId);
        assertEquals(expectedAccount, foundAccount);
    }

    @Test
    public void findById_withNonExistingAccountId_shouldReturnNull() {

        Long nonExistingAccountId = 2L;

        when(mockAccountGateway.findById(anyLong())).thenReturn(null);

        Account foundAccount = accountService.findById(nonExistingAccountId);

        assertEquals(null,foundAccount);
    }

}
