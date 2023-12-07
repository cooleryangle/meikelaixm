package com.meikelai.service.impl;

import com.meikelai.entity.CartItem;
import com.meikelai.mapper.CartItemMapper;
import com.meikelai.service.ICartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Le
 * @since 2023-12-02
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements ICartItemService {

}
