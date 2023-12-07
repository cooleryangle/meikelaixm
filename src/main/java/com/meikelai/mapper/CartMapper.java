package com.meikelai.mapper;

import com.meikelai.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Le
 * @since 2023-12-02
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    // 根据用户ID获取购物车
    @Select("SELECT * FROM Cart WHERE userId = #{userId}")
    Cart selectCartByUserId(@Param("userId") Integer userId);

    // 插入新的购物车
    // 需要在对应的 XML 文件中编写具体的 SQL 插入语句
    int insert(Cart cart);

    Cart selectCartInfoByUserId(Integer userId);
}
