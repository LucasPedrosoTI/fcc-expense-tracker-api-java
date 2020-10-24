package com.lucaspedrosoti.expensetracker.repositories;

import java.util.List;

import com.lucaspedrosoti.expensetracker.domain.Category;
import com.lucaspedrosoti.expensetracker.exceptions.EtBadRequestException;
import com.lucaspedrosoti.expensetracker.exceptions.EtResourceNotFoundException;

public interface CategoryRepository {

  List<Category> findAll(Integer userId) throws EtResourceNotFoundException;

  Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

  Integer create(Integer userId, String title, String description) throws EtBadRequestException;

  Category update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

  void removeById(Integer userId, Integer categoryId);
}
