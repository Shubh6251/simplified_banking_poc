package model;


import jakarta.persistence.*;

// entity/Card.java
    @Entity
    public class Card {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Store hash of card number for security
        @Column(unique = true, nullable = false)
        private String cardHash;

        @Column(nullable = false)
        private String pinHash;

        @Column(nullable = false)
        private Double balance;

        // link to "owner" username for role-based history
        @Column(nullable = false)
        private String ownerUsername;

        // getters/setters
    }


