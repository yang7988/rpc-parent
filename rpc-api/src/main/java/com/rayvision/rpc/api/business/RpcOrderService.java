package com.rayvision.rpc.api.business;

import com.rayvision.rpc.common.ApiResponse;

public interface RpcOrderService {
    ApiResponse createOrder(Integer userId,Byte payMethod,Integer productId,Integer quantity);

    ApiResponse saveOrder(String orderJson);
}
