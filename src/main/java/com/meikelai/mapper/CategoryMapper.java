package com.meikelai.mapper;

import com.meikelai.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meikelai.entity.Dish;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */

public interface CategoryMapper extends BaseMapper<Category> {


//    @Select("SELECT c.categoryId, c.name, c.description, " +
//            "d.dishId, d.name as dishName, d.description as dishDescription, d.price, d.imageUrl " +
//            "FROM Category c " +
//            "LEFT JOIN Dish d ON c.categoryId = d.categoryId")
//    @Results({
//            @Result(property = "categoryId", column = "categoryId"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "description", column = "description"),
//            @Result(property = "dishes", column = "categoryId", javaType = List.class,
//                    many = @Many(select = "findDishesByCategoryId"))
//    })
@Select("SELECT * FROM Category ")
    List<Category> findAllCategoriesWithDishes();
//
    @Select("SELECT * FROM Dish WHERE categoryId = #{categoryId}")
    List<Dish> findDishesByCategoryId(int categoryId);
}
