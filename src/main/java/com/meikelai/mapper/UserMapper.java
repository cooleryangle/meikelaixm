package com.meikelai.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meikelai.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;



/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */

public interface UserMapper extends BaseMapper<User> {

    default User selectByOpenId(String openId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wechatOpenId", openId);
        return selectOne(queryWrapper);
    }
}
