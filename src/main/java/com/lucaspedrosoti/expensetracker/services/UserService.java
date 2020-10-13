package com.lucaspedrosoti.expensetracker.services;

import com.lucaspedrosoti.expensetracker.domain.User;
import com.lucaspedrosoti.expensetracker.exceptions.EtAuthException;

public interface UserService {

  User validateUser(String email, String password) throws EtAuthException;

  User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
