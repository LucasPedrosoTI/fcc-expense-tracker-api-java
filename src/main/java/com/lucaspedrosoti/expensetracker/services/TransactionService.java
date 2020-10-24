package com.lucaspedrosoti.expensetracker.services;

import java.util.List;

import com.lucaspedrosoti.expensetracker.domain.Transaction;
import com.lucaspedrosoti.expensetracker.exceptions.EtBadRequestException;
import com.lucaspedrosoti.expensetracker.exceptions.EtResourceNotFoundException;

public interface TransactionService {

  List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

  Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId)
      throws EtResourceNotFoundException;

  Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate)
      throws EtBadRequestException;

  Transaction updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
      throws EtBadRequestException;

  void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

}
