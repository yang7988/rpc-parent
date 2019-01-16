package com.rayvision.rpc.business.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.rayvision.rpc.api.business.UserService;
import com.rayvision.rpc.business.entity.User;
import com.rayvision.rpc.business.entity.UserExample;
import com.rayvision.rpc.business.mapper.UserMapper;
import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.common.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(version = "1.0.0",provider = "providerConfig",protocol = "protocolConfig")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public ApiResponse getUserByUserName(String userName) throws Exception {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<User> list = userMapper.selectByExample(example);
        User user = list.get(0);
        return ApiResponse.returnSuccess(ObjectUtil.objectToMap(user));
    }
}