package com.meikelai.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meikelai.entity.Session;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-26
 */

public interface SessionMapper extends BaseMapper<Session> {

    default Session selectByUserId(Integer userId) {
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return selectOne(queryWrapper);
    }
}
