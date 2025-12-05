package Controller;


import DTOs.Dto;
import Service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/transaction")
    public class TransactionController {

        private final TransactionService service;

        public TransactionController(TransactionService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<Dto.TransactionResponse> create(@RequestBody Dto.TransactionRequest request) {
            Dto.TransactionResponse response = service.handleTransaction(request);
            return ResponseEntity.ok(response);
        }
    }


