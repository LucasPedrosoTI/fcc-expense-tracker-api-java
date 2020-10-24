package com.lucaspedrosoti.expensetracker.services;

import java.util.List;

import com.lucaspedrosoti.expensetracker.domain.Category;
import com.lucaspedrosoti.expensetracker.exceptions.EtBadRequestException;
import com.lucaspedrosoti.expensetracker.exceptions.EtResourceNotFoundException;

public interface CategoryService {
  List<Category> fetchAllCategories(Integer userId);

  Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

  Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;

  Category updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

  void removeCategory(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}
