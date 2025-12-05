package DTOs;


    public class TransactionRequest {
        private String cardNumber;
        private String pin;
        private Double amount;
        private String type;
        // getters/setters
    }

    public class TransactionResponse {
        private boolean success;
        private String message;
        private Double balance;
        private String transactionId;
        // getters/setters
    }


