package com.rayvision.rpc.api.business;

import com.rayvision.rpc.common.ApiResponse;

public interface RpcProductService {

    ApiResponse deductionProductStock(Integer productId,Integer quantity);
}
