package com.rayvision.rpc.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.rayvision.rpc.api.business.RpcOrderService;
import com.rayvision.rpc.api.business.RpcProductService;
import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.common.enums.ApiResponseEnum;
import com.rayvision.rpc.web.entity.UserAccount;
import com.rayvision.rpc.web.entity.UserAccountExample;
import com.rayvision.rpc.web.mapper.UserAccountMapper;
import com.rayvision.rpc.web.mapper.UserMapper;
import com.rayvision.rpc.web.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Reference
    private RpcOrderService rpcOrderService;

    @Reference
    private RpcProductService rpcProductService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    @Transactional
    public ApiResponse purchase(Integer userId, Integer productId, Integer quantity,Byte payMethod) {
        // 订单微服务创建订单
        ApiResponse orderResponse = rpcOrderService.createOrder(userId, payMethod, productId, quantity);
        // 获取用户的账户详情
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria().andUserIdEqualTo(userId);
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        if(CollectionUtils.isEmpty(userAccounts)) {
            return ApiResponse.returnFail(ApiResponseEnum.FAIL);
        }
        UserAccount userAccount = userAccounts.get(0);
        Map<String,Object> objectMap = (Map<String, Object>) orderResponse.getData();
        // 计算商品的总价
        BigDecimal totalPrice = (BigDecimal) objectMap.get("totalPrice");
        if(userAccount.getBalance().compareTo(totalPrice) < 0) {
            ApiResponse.returnFail(ApiResponseEnum.FAIL);
        }
        // 跟新账户余额
        UserAccount userAccountForUpdate = new UserAccount();
        userAccountForUpdate.setId(userAccount.getId());
        userAccountForUpdate.setBalance(userAccount.getBalance().subtract(totalPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
        userAccountMapper.updateByPrimaryKeySelective(userAccountForUpdate);
        // 保存订单
        rpcOrderService.saveOrder(JSON.toJSONString(objectMap));
        // 扣减商品库存
        rpcProductService.deductionProductStock(productId,quantity);
        return ApiResponse.returnSuccess();
    }
}