package com.rayvision.rpc.business.service.impl;

import com.rayvision.rpc.business.entity.Order;
import com.rayvision.rpc.business.mapper.OrderMapper;
import com.rayvision.rpc.business.service.OrderService;
import com.rayvision.rpc.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order generateOrder(Integer userId, Byte paymethod) throws BusinessException {
        Order order = new Order();
        order.setUserId(userId);
        order.setPayMethod(paymethod);
        order.setOrderNo(UUID.randomUUID().toString());
        orderMapper.insertSelective(order);
        return order;
    }
}