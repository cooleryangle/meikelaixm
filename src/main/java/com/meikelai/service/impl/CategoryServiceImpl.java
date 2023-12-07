package com.meikelai.service.impl;

import com.meikelai.entity.Category;
import com.meikelai.entity.Dish;
import com.meikelai.mapper.CategoryMapper;
import com.meikelai.mapper.DishMapper;
import com.meikelai.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Value("${file.storage.location}")
    private String fileLocation;
    public List<Category> getAllCategoriesWithDishes() {
        List<Category> categories = categoryMapper.findAllCategoriesWithDishes();

        // 转换为DTO列表
        List<Category> categoryDtos = categories.stream().map(category -> {
            // 获取每个类别的所有菜品
            List<Dish> dishes = categoryMapper.findDishesByCategoryId(category.getCategoryId());
            // 创建类别DTO，并设置菜品列表
            dishes.stream().map(dish -> {
                String realUrl="http://127.0.0.1:8081/"+fileLocation+"/"+dish.getImageUrl();
                dish.setImageUrl(realUrl);
                return dish;
            }).collect(Collectors.toList());
            category.setDishes(dishes);
            return category;
        }).collect(Collectors.toList());

        return categoryDtos;

    }
}
