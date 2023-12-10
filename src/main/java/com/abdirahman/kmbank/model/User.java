package com.abdirahman.kmbank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    private String accountNumber;
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    private String password;
    private Double balance;

    public User() {
    }

    public User(String accountNumber, String firstName, String lastName, Date dob, String email, String password, Double balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    @Column(name = "account_number", nullable = false)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "date_of_birth", nullable = false)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "balance", nullable = false)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
