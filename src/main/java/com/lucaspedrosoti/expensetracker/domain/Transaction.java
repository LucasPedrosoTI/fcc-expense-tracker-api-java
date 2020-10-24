package com.lucaspedrosoti.expensetracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
  private Integer transactionId;
  private Integer categoryId;
  private Integer userId;
  private Double amount;
  private String note;
  private Long transactionDate;
}
