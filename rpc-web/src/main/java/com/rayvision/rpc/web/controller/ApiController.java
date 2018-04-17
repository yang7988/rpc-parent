package com.rayvision.rpc.web.controller;

import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.common.enums.ApiResponseEnum;
import com.rayvision.rpc.web.service.FrontRateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rpc")
public class ApiController {

    @Autowired
    private FrontRateService frontRateService;

    @RequestMapping(value = "/getRate/{id}",method = RequestMethod.GET)
    public ApiResponse getRate(@PathVariable Integer id) throws Exception {
        if(id == null) {
            ApiResponse.returnFail(ApiResponseEnum.PARAMETER_CANT_BE_EMPTY);
        }
        return frontRateService.selectById(id);
    }

    @RequestMapping(value = "/getUserByUsername/{userName}",method = RequestMethod.GET)
    public ApiResponse getUserByUsername(@PathVariable String userName) throws Exception {
        if(StringUtils.isEmpty(userName)) {
            ApiResponse.returnFail(ApiResponseEnum.PARAMETER_CANT_BE_EMPTY);
        }
        return frontRateService.selectUserByUsername(userName);
    }
}