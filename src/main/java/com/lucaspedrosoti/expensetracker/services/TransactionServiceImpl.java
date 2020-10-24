package com.lucaspedrosoti.expensetracker.services;

import java.util.List;

import com.lucaspedrosoti.expensetracker.domain.Transaction;
import com.lucaspedrosoti.expensetracker.exceptions.EtBadRequestException;
import com.lucaspedrosoti.expensetracker.exceptions.EtResourceNotFoundException;
import com.lucaspedrosoti.expensetracker.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  TransactionRepository transactionRepository;

  @Override
  public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {
    return transactionRepository.findAll(userId, categoryId);
  }

  @Override
  public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId)
      throws EtResourceNotFoundException {
    return transactionRepository.findById(userId, categoryId, transactionId);
  }

  @Override
  public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note,
      Long transactionDate) throws EtBadRequestException {

    return transactionRepository.create(userId, categoryId, amount, note, transactionDate);
  }

  @Override
  public Transaction updateTransaction(Integer userId, Integer categoryId, Integer transactionId,
      Transaction transaction) throws EtBadRequestException {
    return transactionRepository.update(userId, categoryId, transactionId, transaction);
  }

  @Override
  public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId)
      throws EtResourceNotFoundException {
    try {
      transactionRepository.removeById(userId, categoryId, transactionId);
    } catch (Exception e) {
      throw new EtBadRequestException("Invalid Request");
    }
  }

}
