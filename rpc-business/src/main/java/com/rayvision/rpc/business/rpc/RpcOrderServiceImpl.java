package com.rayvision.rpc.business.rpc;

import com.alibaba.fastjson.JSONObject;
import com.rayvision.rpc.api.business.RpcOrderService;
import com.rayvision.rpc.business.entity.Order;
import com.rayvision.rpc.business.service.OrderService;
import com.rayvision.rpc.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@com.alibaba.dubbo.config.annotation.Service(provider = "providerConfig",protocol = "protocolConfig")
public class RpcOrderServiceImpl implements RpcOrderService {

    @Autowired
    private OrderService orderService;

    @Override
    public ApiResponse createOrder(Integer userId, Byte payMethod,Integer productId,Integer quantity) {
        Map<String, Object> order = orderService.generateOrder(userId, payMethod, productId, quantity);
        return ApiResponse.returnSuccess(order);
    }

    @Override
    public ApiResponse saveOrder(String orderJson) {
        Order order = JSONObject.parseObject(orderJson, Order.class);
        orderService.saveOrder(order);
        return ApiResponse.returnSuccess();
    }
}