package com.rayvision.rpc.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rayvision.rpc.api.business.RpcVersionTestService;
import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.web.service.GrayUpgradeService;
import org.springframework.stereotype.Service;

@Service
public class GrayUpgradeServiceImpl implements GrayUpgradeService {

    @Reference(check = false,version = "0.0.1")
    private RpcVersionTestService rpcVersionTestService;

    @Override
    public ApiResponse getDubboServiceVersion() {
        return ApiResponse.returnSuccess(rpcVersionTestService.queryServiceVersion());
    }
}