package com.lucaspedrosoti.expensetracker.repositories;

import com.lucaspedrosoti.expensetracker.domain.User;
import com.lucaspedrosoti.expensetracker.exceptions.EtAuthException;

public interface UserRepository {

  Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

  User findByEmailAndPassword(String email, String password) throws EtAuthException;

  User findById(Integer id);

  Integer getCountByEmail(String email);
}
