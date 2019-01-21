package com.rayvision.rpc.business.rpc;

import com.rayvision.rpc.api.business.RpcOrderService;
import com.rayvision.rpc.business.entity.Order;
import com.rayvision.rpc.business.service.OrderService;
import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.common.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@com.alibaba.dubbo.config.annotation.Service(provider = "providerConfig",protocol = "protocolConfig")
public class RpcOrderServiceImpl implements RpcOrderService {

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public ApiResponse createOrder(Integer userId, Byte payMethod) {
        Order order = orderService.generateOrder(userId, payMethod);
        return ApiResponse.returnSuccess(ObjectUtil.objectToMap(order));
    }
}