package com.lucaspedrosoti.expensetracker.resources;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lucaspedrosoti.expensetracker.domain.Transaction;
import com.lucaspedrosoti.expensetracker.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

  @Autowired
  TransactionService transactionService;

  @GetMapping("")
  public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest req,
      @PathVariable("categoryId") Integer categoryId) {
    int userId = (Integer) req.getAttribute("userId");

    List<Transaction> transactions = transactionService.fetchAllTransactions(userId, categoryId);

    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  @GetMapping("{transactionId}")
  public ResponseEntity<Transaction> getTransactionById(HttpServletRequest req,
      @PathVariable("categoryId") Integer categoryId, @PathVariable("transactionId") Integer transactionId) {
    int userId = (Integer) req.getAttribute("userId");

    Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);

    return new ResponseEntity<>(transaction, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Transaction> addTransaction(HttpServletRequest req,
      @PathVariable("categoryId") Integer categoryId, @RequestBody Map<String, Object> transactionMap) {

    int userId = (Integer) req.getAttribute("userId");
    Double amount = (Double) Double.valueOf(transactionMap.get("amount").toString());
    String note = (String) transactionMap.get("note");
    Long transactionDate = Long.valueOf(transactionMap.get("transactionDate").toString());

    Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, transactionDate);

    return new ResponseEntity<>(transaction, HttpStatus.CREATED);
  }

  @PutMapping("/{transactionId}")
  public ResponseEntity<Transaction> updateTransaction(HttpServletRequest req,
      @PathVariable("categoryId") Integer categoryId, @PathVariable("transactionId") Integer transactionId,
      @RequestBody Transaction transaction) {
    int userId = (Integer) req.getAttribute("userId");

    Transaction updatedTransaction = transactionService.updateTransaction(userId, categoryId, transactionId,
        transaction);

    return new ResponseEntity<>(updatedTransaction, HttpStatus.ACCEPTED);
  }
}
