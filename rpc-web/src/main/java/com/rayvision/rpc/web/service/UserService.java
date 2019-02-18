package com.rayvision.rpc.web.service;

import com.rayvision.rpc.common.ApiResponse;

public interface UserService {
    ApiResponse purchase(Integer userId, Integer productId,Integer quantity,Byte payMethod);
}
