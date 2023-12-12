package com.abdirahman.kmbank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "basicTransactions")
public class BasicTransaction {
    private Long id;
    private Long userId;
    private String transactionType;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "transaction_type", nullable = false)
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name = "balance", nullable = false)
    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
