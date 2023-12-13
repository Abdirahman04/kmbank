package com.abdirahman.kmbank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "basicTransactions")
public class BasicTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Long userId;
    @Column(name = "transaction_type", nullable = false)
    private String transactionType;
    @Column(name = "balance", nullable = false)
    private Double balance;

    public BasicTransaction() {
    }

    public BasicTransaction(Long id, Long userId, String transactionType, double balance) {
        this.id = id;
        this.userId = userId;
        this.transactionType = transactionType;
        this.balance = balance;
    }

    public BasicTransaction(Long userId, String transactionType, double balance) {
        this.userId = userId;
        this.transactionType = transactionType;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
