package DTOs;

public class Dto {
    // dto/TransactionRequest.java
    public class TransactionRequest {
        private String cardNumber;
        private String pin;
        private Double amount;
        private String type; // "withdraw" or "topup"
        // getters/setters
    }

    // dto/TransactionResponse.java
    public class TransactionResponse {
        private boolean success;
        private String message;
        private Double balance;      // optional
        private String transactionId; // optional
        // getters/setters
    }


}
