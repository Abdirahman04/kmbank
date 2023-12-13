package com.abdirahman.kmbank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transferTransactions")
public class TransferTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Long senderId;
    @Column(nullable = false)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private Long recipientId;
    @Column(name = "balance", nullable = false)
    private Double balance;

    public TransferTransaction() {
    }

    public TransferTransaction(Long id, Long senderId, Long recipientId, Double balance) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.balance = balance;
    }

    public TransferTransaction(Long senderId, Long recipientId, Double balance) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
