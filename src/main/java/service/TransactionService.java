package service;

import java.util.Collection;

import dto.TransactionDto;
import dto.TransferDto;
import model.Transactions;

public interface TransactionService {
    void deposit(TransactionDto transactionDto);
    void withdraw(TransactionDto transactionDto);
    void transfer(TransferDto transferDto);
    Collection<Transactions> getTransactionsByUserEmail(String userEmail);
}