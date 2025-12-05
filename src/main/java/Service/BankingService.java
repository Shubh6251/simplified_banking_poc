package Service;


import DTOs.TransactionRequest;
import DTOs.TransactionResponse;
import Repository.CardRepository;
import Repository.TransactionRepository;
import model.Card;
import model.Transaction;
import security.HashUtil;

import java.util.List;
import java.util.Optional;

@Service
    public class BankingService {

        private final CardRepository cardRepo;
        private final TransactionRepository txRepo;

        public BankingService(CardRepository cardRepo, TransactionRepository txRepo) {
            this.cardRepo = cardRepo;
            this.txRepo = txRepo;
        }

        public TransactionResponse processTransaction(TransactionRequest request, String initiatedBy) {
            String cardHash = HashUtil.sha256(request.getCardNumber());
            String pinHashInput = HashUtil.sha256(request.getPin());

            Optional<Card> cardOpt = cardRepo.findByCardHash(cardHash);

            Transaction tx = new Transaction();
            tx.setCardHash(cardHash);
            tx.setAmount(request.getAmount());
            tx.setType(request.getType().toLowerCase());
            tx.setCreatedAt(LocalDateTime.now());
            tx.setInitiatedBy(initiatedBy == null ? "SYSTEM1" : initiatedBy);

            TransactionResponse resp = new TransactionResponse();

            if (cardOpt.isEmpty()) {
                tx.setSuccess(false);
                tx.setMessage("Invalid card number");
                txRepo.save(tx);

                resp.setSuccess(false);
                resp.setMessage("Invalid card number");
                return resp;
            }

            Card card = cardOpt.get();

            if (!card.getPinHash().equals(pinHashInput)) {
                tx.setSuccess(false);
                tx.setMessage("Invalid PIN");
                txRepo.save(tx);

                resp.setSuccess(false);
                resp.setMessage("Invalid PIN");
                return resp;
            }

            // PIN OK, process
            if (tx.getType().equals("withdraw")) {
                if (card.getBalance() < request.getAmount()) {
                    tx.setSuccess(false);
                    tx.setMessage("Insufficient funds");
                    txRepo.save(tx);

                    resp.setSuccess(false);
                    resp.setMessage("Insufficient funds");
                    resp.setBalance(card.getBalance());
                    return resp;
                }
                card.setBalance(card.getBalance() - request.getAmount());
            } else if (tx.getType().equals("topup")) {
                card.setBalance(card.getBalance() + request.getAmount());
            } else {
                tx.setSuccess(false);
                tx.setMessage("Invalid transaction type");
                txRepo.save(tx);

                resp.setSuccess(false);
                resp.setMessage("Invalid transaction type");
                return resp;
            }

            cardRepo.save(card);
            tx.setSuccess(true);
            tx.setMessage("OK");
            txRepo.save(tx);

            resp.setSuccess(true);
            resp.setMessage("Transaction successful");
            resp.setBalance(card.getBalance());
            resp.setTransactionId(tx.getId());
            return resp;
        }

        // For UI – super admin
        public List<Transaction> getAllTransactions() {
            return txRepo.findAll();
        }

        // For UI – customer
        public Optional<Card> getCardByOwner(String username) {
            return cardRepo.findAll().stream()
                    .filter(c -> c.getOwnerUsername().equals(username))
                    .findFirst();
        }

        public List<Transaction> getTransactionsByOwner(String username) {
            return txRepo.findByInitiatedBy(username);
        }
    }


