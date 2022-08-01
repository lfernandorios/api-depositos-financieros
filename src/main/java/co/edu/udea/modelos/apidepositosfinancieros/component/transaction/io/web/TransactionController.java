package co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.web;

import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.io.web.model.TransactionSaveRequest;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.TransactionService;
import co.edu.udea.modelos.apidepositosfinancieros.component.transaction.service.model.TransactionSaveCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody TransactionSaveRequest request){

        TransactionSaveCmd transactionSaveCmd = TransactionSaveRequest.toModel(request);

        transactionService.create(transactionSaveCmd);

        return ResponseEntity.accepted().build();
    }

}
