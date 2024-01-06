package com.abdirahman.kmbank.model.response;

public record StatisticsResponseBody(long numberOfUsers, String totalAmount, long numberOfTransactions, long numberOfBasicTransactions, long numberOfDeposits, long numberOfWithdrawals, long numberOfTransferTransactions) {
}
