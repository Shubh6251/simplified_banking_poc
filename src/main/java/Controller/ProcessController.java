package Controller;

import DTOs.TransactionRequest;
import DTOs.TransactionResponse;
import Service.BankingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    public class ProcessController {

        private final BankingService bankingService;

        public ProcessController(BankingService bankingService) {
            this.bankingService = bankingService;
        }

        @PostMapping("/process")
        public TransactionResponse process(@RequestBody TransactionRequest request) {
            // initiatedBy null: called from System 1
            return bankingService.processTransaction(request, null);
        }
    }


