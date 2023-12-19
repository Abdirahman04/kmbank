package com.abdirahman.kmbank.model.entity;

import com.abdirahman.kmbank.model.entity.BasicTransaction;
import com.abdirahman.kmbank.model.entity.TransferTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence")
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "date_of_birth", nullable = false)
    private Date dob;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "balance", nullable = false)
    private Double balance = 0.0;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BasicTransaction> basicTransactions;

    @OneToMany(mappedBy = "senderId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransferTransaction> senderTransferTransactions;

    @OneToMany(mappedBy = "recipientId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransferTransaction> recipientTransferTransactions;

    public User() {
    }

    public User(String accountNumber, String firstName, String lastName, Date dob, String email, String password) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<BasicTransaction> getBasicTransactions() {
        return basicTransactions;
    }

    public void setBasicTransactions(List<BasicTransaction> basicTransactions) {
        this.basicTransactions = basicTransactions;
    }

    public List<TransferTransaction> getSenderTransferTransactions() {
        return senderTransferTransactions;
    }

    public void setSenderTransferTransactions(List<TransferTransaction> senderTransferTransactions) {
        this.senderTransferTransactions = senderTransferTransactions;
    }

    public List<TransferTransaction> getRecipientTransferTransactions() {
        return recipientTransferTransactions;
    }

    public void setRecipientTransferTransactions(List<TransferTransaction> recipientTransferTransactions) {
        this.recipientTransferTransactions = recipientTransferTransactions;
    }

    @Override
    public String toString() {
        return "User{" +
                "accountNumber='" + accountNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
