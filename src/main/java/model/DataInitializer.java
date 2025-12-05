package model;


import Repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import security.HashUtil;

@Component
    public class DataInitializer implements CommandLineRunner {

        private final CardRepository cardRepository;

        public DataInitializer(CardRepository cardRepository) {
            this.cardRepository = cardRepository;
        }

        @Override
        public void run(String... args) {
            // Sample Visa-like card
            String cardNumber = "4123456789012345";
            String pin = "1234";

            Card card = new Card();
            card.setCardHash(HashUtil.sha256(cardNumber));
            card.setPinHash(HashUtil.sha256(pin));
            card.setBalance(1000.0);
            card.setOwnerUsername("customer1");
            cardRepository.save(card);
        }
    }


