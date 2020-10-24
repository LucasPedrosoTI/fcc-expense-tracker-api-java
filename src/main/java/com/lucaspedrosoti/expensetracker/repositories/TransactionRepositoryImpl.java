package com.lucaspedrosoti.expensetracker.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.lucaspedrosoti.expensetracker.domain.Transaction;
import com.lucaspedrosoti.expensetracker.exceptions.EtBadRequestException;
import com.lucaspedrosoti.expensetracker.exceptions.EtResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

  private static final String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS (TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE) VALUES(NEXTVAL('ET_TRANSACTIONS_SEQ'), ?, ?, ?, ?, ?)";

  private static final String SQL_FIND_BY_ID = "SELECT * FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ?";

  private static final String SQL_FIND_ALL = "SELECT * FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ?";

  private static final String SQL_UPDATE = "UPDATE ET_TRANSACTIONS SET AMOUNT = ?, NOTE = ?, TRANSACTION_DATE = ? WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ?";

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<Transaction> findAll(Integer userId, Integer categoryId) {
    try {
      return jdbcTemplate.query(SQL_FIND_ALL, new Object[] { userId, categoryId }, transactionRowMapper);
    } catch (Exception e) {
      throw new EtResourceNotFoundException("Transaction not found");
    }

  }

  @Override
  public Transaction findById(Integer userId, Integer categoryId, Integer transactionId)
      throws EtResourceNotFoundException {

    try {
      return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { userId, categoryId, transactionId },
          transactionRowMapper);
    } catch (Exception e) {
      throw new EtResourceNotFoundException("Transaction not found");
    }

  }

  @Override
  public Transaction create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate)
      throws EtBadRequestException {
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();

      jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, categoryId);
        ps.setInt(2, userId);
        ps.setDouble(3, amount);
        ps.setString(4, note);
        ps.setLong(5, transactionDate);
        return ps;
      }, keyHolder);

      int transactionId = (Integer) keyHolder.getKeys().get("TRANSACTION_ID");

      return this.findById(userId, categoryId, transactionId);
    } catch (Exception e) {
      throw new EtBadRequestException("Invalid Request");
    }
  }

  @Override
  public Transaction update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
      throws EtBadRequestException {
    try {
      jdbcTemplate.update(SQL_UPDATE, new Object[] { transaction.getAmount(), transaction.getNote(),
          transaction.getTransactionDate(), userId, categoryId, transactionId });

      return this.findById(userId, categoryId, transactionId);
    } catch (Exception e) {
      throw new EtBadRequestException("Invalid Request");
    }
  }

  @Override
  public void removeById(Integer userId, Integer categoryId, Integer transactionId) {
    // TODO Auto-generated method stub

  }

  private RowMapper<Transaction> transactionRowMapper = ((rs, rowNum) -> {
    return new Transaction(rs.getInt("TRANSACTION_ID"), rs.getInt("CATEGORY_ID"), rs.getInt("USER_ID"),
        rs.getDouble("AMOUNT"), rs.getString("NOTE"), rs.getLong("TRANSACTION_DATE"));
  });

}
