package com.rayvision.rpc.business.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.rayvision.rpc.api.business.RateService;
import com.rayvision.rpc.business.entity.Rate;
import com.rayvision.rpc.business.mapper.RateMapper;
import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.common.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0",provider = "providerConfig",protocol = "protocolConfig")
public class RateServiceImpl implements RateService {
    private Logger logger = LoggerFactory.getLogger(RateServiceImpl.class);

    @Autowired
    private RateMapper rateMapper;

    public ApiResponse selectById(Integer id) throws Exception {
        this.logger.info("rate id is " + id);
        Rate rate = rateMapper.selectByPrimaryKey(1);
        return ApiResponse.returnSuccess(ObjectUtil.objectToMap(rate));
    }
}
