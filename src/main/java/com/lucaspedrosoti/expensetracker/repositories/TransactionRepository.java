package com.lucaspedrosoti.expensetracker.repositories;

import java.util.List;

import com.lucaspedrosoti.expensetracker.domain.Transaction;
import com.lucaspedrosoti.expensetracker.exceptions.EtBadRequestException;
import com.lucaspedrosoti.expensetracker.exceptions.EtResourceNotFoundException;

public interface TransactionRepository {
  List<Transaction> findAll(Integer userId, Integer categoryId);

  Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

  Transaction create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate)
      throws EtBadRequestException;

  Transaction update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
      throws EtBadRequestException;

  void removeById(Integer userId, Integer categoryId, Integer transactionId);
}
