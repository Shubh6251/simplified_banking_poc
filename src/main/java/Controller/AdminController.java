package Controller;


import DTOs.TransactionRequest;
import DTOs.TransactionResponse;
import Service.BankingService;
import model.Transaction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("/admin")
    public class AdminController {

        private final BankingService bankingService;

        public AdminController(BankingService bankingService) {
            this.bankingService = bankingService;
        }

        @GetMapping("/transactions")
        public List<Transaction> allTransactions() {
            return bankingService.getAllTransactions();
        }
    }

    @RestController
    @RequestMapping("/customer")
    public class CustomerController {

        private final BankingService bankingService;

        public CustomerController(BankingService bankingService) {
            this.bankingService = bankingService;
        }

        @GetMapping("/me/balance")
        public ResponseEntity<Double> myBalance(Authentication auth) {
            String username = auth.getName();
            return bankingService.getCardByOwner(username)
                    .map(c -> ResponseEntity.ok(c.getBalance()))
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/me/transactions")
        public List<Transaction> myTransactions(Authentication auth) {
            String username = auth.getName();
            return bankingService.getTransactionsByOwner(username);
        }

        @PostMapping("/me/topup")
        public TransactionResponse topup(@RequestBody Map<String, Object> body, Authentication auth) {
            String cardNumber = (String) body.get("cardNumber");
            String pin = (String) body.get("pin");
            Double amount = Double.valueOf(body.get("amount").toString());

            TransactionRequest req = new TransactionRequest();
            req.setCardNumber(cardNumber);
            req.setPin(pin);
            req.setAmount(amount);
            req.setType("topup");

            return bankingService.processTransaction(req, auth.getName());
        }
    }


