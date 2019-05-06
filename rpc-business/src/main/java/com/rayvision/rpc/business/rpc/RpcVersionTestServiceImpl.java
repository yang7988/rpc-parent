package com.rayvision.rpc.business.rpc;

import com.rayvision.rpc.api.business.RpcVersionTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service(provider = "providerConfig",protocol = "protocolConfig")
public class RpcVersionTestServiceImpl implements RpcVersionTestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcVersionTestServiceImpl.class);

    @Override
    public String queryServiceVersion() {
        LOGGER.warn("current dubbo service version is 0.0.1");
        return "0.0.0";
    }
}