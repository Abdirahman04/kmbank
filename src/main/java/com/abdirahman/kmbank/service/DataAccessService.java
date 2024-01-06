package com.abdirahman.kmbank.service;

import com.abdirahman.kmbank.model.response.StatisticsResponseBody;
import com.abdirahman.kmbank.repository.BasicTransactionRepository;
import com.abdirahman.kmbank.repository.TransferTransactionRepository;
import com.abdirahman.kmbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;

@Service
@Transactional
public class DataAccessService {
    UserRepository userRepository;
    BasicTransactionRepository basicTransactionRepository;
    TransferTransactionRepository transferTransactionRepository;

    public DataAccessService() {
    }

    @Autowired
    public DataAccessService(UserRepository userRepository, BasicTransactionRepository basicTransactionRepository, TransferTransactionRepository transferTransactionRepository) {
        this.userRepository = userRepository;
        this.basicTransactionRepository = basicTransactionRepository;
        this.transferTransactionRepository = transferTransactionRepository;
    }

    public long countUsers() {
        return userRepository.countAllUsers();
    }

    public long countTypeBasicTransactions(String transaction) {
        return basicTransactionRepository.countTransactions(transaction);
    }

    public long countBasicTransactions() {
        return basicTransactionRepository.countAllBasicTransactions();
    }

    public long countTransferTransactions() {
        return transferTransactionRepository.countAllTransferTransactions();
    }

    public String getTotalBalance() {
        double amount = userRepository.getTotalOfBalance();
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    public StatisticsResponseBody getStatisticsData() {
        long countUsers = countUsers();
        long countBasicTransactions = countBasicTransactions();
        long countDeposits = countTypeBasicTransactions("deposit");
        long countWithdrawals = countTypeBasicTransactions("withdrawal");
        long countTransferTransactions = countTransferTransactions();
        long countAllTransactions = countBasicTransactions + countTransferTransactions;
        String totalAmount = getTotalBalance();

        return new StatisticsResponseBody(countUsers, totalAmount, countAllTransactions, countBasicTransactions, countDeposits, countWithdrawals, countTransferTransactions);
    }
}
