package com.meikelai.service;

import com.meikelai.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
public interface ICategoryService extends IService<Category> {

    List<Category> getAllCategoriesWithDishes();
}
