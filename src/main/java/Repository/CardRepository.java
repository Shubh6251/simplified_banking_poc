package Repository;


import model.Card;
import model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
        Optional<Card> findByCardHash(String cardHash);
    }

    public interface TransactionRepository extends JpaRepository<Transaction, String> {
        List<Transaction> findByCardHash(String cardHash);
        List<Transaction> findByInitiatedBy(String username);
    }


