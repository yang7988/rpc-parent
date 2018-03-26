package com.rayvision.rpc.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rayvision.rpc.api.business.RateService;
import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.web.service.FrontRateService;
import org.springframework.stereotype.Service;

@Service
public class FrontRateServiceImpl implements FrontRateService {

    @Reference(version = "1.0.0")
    private RateService rateService;

    public ApiResponse selectById(Integer id) throws Exception {
        return this.rateService.selectById(id);
    }
}
