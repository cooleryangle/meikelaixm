package com.meikelai.controller;

import com.meikelai.entity.Category;
import com.meikelai.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/menu")
    public ResponseEntity<List<Category>> getAllCategoriesWithDishes() {
        List<Category> categories = categoryService.getAllCategoriesWithDishes();

        return ResponseEntity.ok(categories);
    }
}


