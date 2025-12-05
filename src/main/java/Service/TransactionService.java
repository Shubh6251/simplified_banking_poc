package Service;


import DTOs.Dto;

@Service
    public class TransactionService {

        private final WebClient system2Client;

        public TransactionService(WebClient system2Client) {
            this.system2Client = system2Client;
        }

        public Dto.TransactionResponse handleTransaction(Dto.TransactionRequest request) {
            // Basic validation
            if (request.getCardNumber() == null || request.getCardNumber().isBlank()
                    || request.getPin() == null || request.getPin().isBlank()
                    || request.getAmount() == null
                    || request.getType() == null || request.getType().isBlank()) {
                return decline("Missing required fields");
            }

            if (request.getAmount() <= 0) {
                return decline("Amount must be positive");
            }

            String type = request.getType().toLowerCase();
            if (!type.equals("withdraw") && !type.equals("topup")) {
                return decline("Invalid transaction type (must be withdraw or topup)");
            }

            // Routing logic: only Visa-like (starting with 4) goes to System 2
            if (!request.getCardNumber().startsWith("4")) {
                return decline("Unsupported card range. Only cards starting with 4 are allowed.");
            }

            // Route to system 2
            return system2Client.post()
                    .uri("/process")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Dto.TransactionResponse.class)
                    .block();
        }

        private Dto.TransactionResponse decline(String message) {
            Dto.TransactionResponse resp = new Dto.TransactionResponse();
            resp.setSuccess(false);
            resp.setMessage(message);
            return resp;
        }
    }


