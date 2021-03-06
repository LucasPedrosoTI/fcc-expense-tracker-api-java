package com.lucaspedrosoti.expensetracker.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lucaspedrosoti.expensetracker.domain.Category;
import com.lucaspedrosoti.expensetracker.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

  @Autowired
  CategoryService categoryService;

  @GetMapping("")
  public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest req) {
    int userId = (Integer) req.getAttribute("userId");

    List<Category> categories = categoryService.fetchAllCategories(userId);

    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<Category> getCategoryById(HttpServletRequest req,
      @PathVariable("categoryId") Integer categoryId) {

    int userId = (Integer) req.getAttribute("userId");

    Category category = categoryService.fetchCategoryById(userId, categoryId);

    return new ResponseEntity<>(category, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Category> addCategory(HttpServletRequest req, @RequestBody Map<String, Object> categoryMap) {
    int userId = (Integer) req.getAttribute("userId");
    String title = (String) categoryMap.get("title");
    String description = (String) categoryMap.get("description");
    Category category = categoryService.addCategory(userId, title, description);

    return new ResponseEntity<>(category, HttpStatus.CREATED);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<Category> updateCategory(HttpServletRequest req, @PathVariable("categoryId") Integer categoryId,
      @RequestBody Category category) {

    int userId = (Integer) req.getAttribute("userId");

    Category updatedCategory = categoryService.updateCategory(userId, categoryId, category);

    return new ResponseEntity<>(updatedCategory, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("{categoryId}")
  public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest req,
      @PathVariable("categoryId") Integer categoryId) {
    int userId = (Integer) req.getAttribute("userId");

    categoryService.removeCategory(userId, categoryId);

    Map<String, Boolean> response = new HashMap<>();
    response.put("success", true);

    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
  }
}
