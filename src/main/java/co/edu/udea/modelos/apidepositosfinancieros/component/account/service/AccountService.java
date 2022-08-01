package co.edu.udea.modelos.apidepositosfinancieros.component.account.service;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;

public interface AccountService {

    Account findById(Long id);
}
