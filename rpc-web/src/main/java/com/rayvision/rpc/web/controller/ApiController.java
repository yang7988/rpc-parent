package com.rayvision.rpc.web.controller;

import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.web.service.GrayUpgradeService;
import com.rayvision.rpc.web.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private GrayUpgradeService grayUpgradeService;

    @GetMapping("/purchase/product/{userId}/{payMethod}/{productId}/{quantity}")
    public ApiResponse purchase(@PathVariable Integer userId, @PathVariable Integer productId,
                                       @PathVariable Integer quantity, @PathVariable Byte payMethod) {
        return userService.purchase(userId, productId, quantity, payMethod);
    }

    @PostMapping(value = "/taskNotify")
    public ApiResponse taskNotify(@RequestBody Map<String,Object> params, HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            LOGGER.warn(name + " = " + request.getHeader(name));
        }
        LOGGER.warn("======回调通知数据=======" + params);
        return ApiResponse.returnSuccess();
    }

    @GetMapping(value = "/getVersion")
    public ApiResponse getVersion() {
        return grayUpgradeService.getDubboServiceVersion();
    }
}