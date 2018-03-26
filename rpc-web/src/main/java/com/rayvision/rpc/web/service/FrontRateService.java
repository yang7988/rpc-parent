package com.rayvision.rpc.web.service;

import com.rayvision.rpc.common.ApiResponse;

public interface FrontRateService {
    public ApiResponse selectById(Integer id) throws Exception;
}
