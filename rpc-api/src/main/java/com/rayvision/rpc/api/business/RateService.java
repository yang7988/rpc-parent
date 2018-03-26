package com.rayvision.rpc.api.business;

import com.rayvision.rpc.common.ApiResponse;

public interface RateService {
    ApiResponse selectById(Integer paramInteger) throws Exception;
}
