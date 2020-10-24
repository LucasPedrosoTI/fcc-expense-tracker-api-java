package com.lucaspedrosoti.expensetracker.services;

import java.util.List;

import com.lucaspedrosoti.expensetracker.domain.Category;
import com.lucaspedrosoti.expensetracker.exceptions.EtBadRequestException;
import com.lucaspedrosoti.expensetracker.exceptions.EtResourceNotFoundException;
import com.lucaspedrosoti.expensetracker.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public List<Category> fetchAllCategories(Integer userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Category addCategory(Integer userId, String title, String description) throws EtBadRequestException {
    int categoryId = categoryRepository.create(userId, title, description);

    return categoryRepository.findById(userId, categoryId);
  }

  @Override
  public void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeCategory(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
    // TODO Auto-generated method stub

  }

}
