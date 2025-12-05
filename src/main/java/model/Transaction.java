package model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

// entity/Transaction.java
    @Entity
    public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        private String cardHash;

        private Double amount;

        private String type; // withdraw/topup

        private Boolean success;

        private String message;

        private LocalDateTime createdAt;

        private String initiatedBy; // username

        // getters/setters
    }


