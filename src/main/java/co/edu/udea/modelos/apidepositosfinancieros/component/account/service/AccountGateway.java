package co.edu.udea.modelos.apidepositosfinancieros.component.account.service;

import co.edu.udea.modelos.apidepositosfinancieros.component.account.model.Account;

public interface AccountGateway {

    Account findById(Long id);
}
