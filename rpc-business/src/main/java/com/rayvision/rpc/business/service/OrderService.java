package com.rayvision.rpc.business.service;

import com.rayvision.rpc.business.entity.Order;
import com.rayvision.rpc.common.exception.BusinessException;

import java.util.Map;

public interface OrderService {

    Map<String, Object> generateOrder(Integer userId, Byte paymethod, Integer productId, Integer quantity) throws BusinessException;

    Integer saveOrder(Order order);
}
