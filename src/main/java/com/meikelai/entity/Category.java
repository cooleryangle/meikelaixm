package com.meikelai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "categoryId", type = IdType.AUTO)
    private Integer categoryId;

    private String name;

    private String description;

    private List<Dish> dishes;

    public Category(Category category) {
    }

    public boolean isShowDishes() {
        return showDishes;
    }

    public void setShowDishes(boolean showDishes) {
        this.showDishes = showDishes;
    }

    private boolean showDishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dishes=" + dishes +
                ", showDishes=" + showDishes +
                '}';
    }
}
