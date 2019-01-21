package com.rayvision.rpc.business.service.impl;

import com.rayvision.rpc.business.entity.Order;
import com.rayvision.rpc.business.entity.Product;
import com.rayvision.rpc.business.mapper.OrderMapper;
import com.rayvision.rpc.business.mapper.ProductMapper;
import com.rayvision.rpc.business.service.OrderService;
import com.rayvision.rpc.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Map<String, Object> generateOrder(Integer userId, Byte paymethod,Integer productId,Integer quantity) throws BusinessException {
        Order order = new Order();
        order.setUserId(userId);
        order.setPayMethod(paymethod);
        order.setOrderNo(UUID.randomUUID().toString());
        orderMapper.insertSelective(order);
        Product product = productMapper.selectByPrimaryKey(productId);
        BigDecimal unitPrice = product.getUnitPrice();
        BigDecimal total = unitPrice.multiply(new BigDecimal(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP);
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("payMethod", paymethod);
        response.put("orderNo", order.getOrderNo());
        response.put("unitPrice", unitPrice);
        response.put("totalPrice", total);
        return response;
    }
}