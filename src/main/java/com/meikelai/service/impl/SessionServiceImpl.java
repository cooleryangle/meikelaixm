package com.meikelai.service.impl;

import com.meikelai.entity.Session;
import com.meikelai.mapper.SessionMapper;
import com.meikelai.service.ISessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-26
 */
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session> implements ISessionService {

}
