package com.rayvision.rpc.business.service;

import com.rayvision.rpc.business.entity.Order;
import com.rayvision.rpc.common.exception.BusinessException;

public interface OrderService {

    Order generateOrder(Integer userId, Byte paymethod) throws BusinessException;
}
