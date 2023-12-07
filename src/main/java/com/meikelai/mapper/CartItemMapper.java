package com.meikelai.mapper;

import com.meikelai.entity.CartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Le
 * @since 2023-12-02
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {

    // 插入新的购物车项
    // 需要在对应的 XML 文件中编写具体的 SQL 插入语句
    int insert(CartItem cartItem);

    // 根据ID更新购物车项
    // 需要在对应的 XML 文件中编写具体的 SQL 更新语句
    int updateById(CartItem cartItem);

    // 根据ID删除购物车项
    @Delete("DELETE FROM Cart_item WHERE cartItemId = #{itemId}")
    void deleteById(@Param("itemId") Integer itemId);

    // 根据购物车ID删除所有购物车项
    @Delete("DELETE FROM Cart_item WHERE cartId = #{cartId}")
    void deleteByCartId(@Param("cartId") Integer cartId);

    // 根据ID查找购物车项
    @Select("SELECT * FROM Cart_item WHERE cartItemId = #{itemId}")
    CartItem selectById(@Param("itemId") Integer itemId);
}
