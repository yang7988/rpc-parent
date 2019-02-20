package com.rayvision.rpc.web.controller;

import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/purchase/product/{userId}/{payMethod}/{productId}/{quantity}")
    public ApiResponse purchase(@PathVariable Integer userId, @PathVariable Integer productId,
                                       @PathVariable Integer quantity, @PathVariable Byte payMethod) {
        return userService.purchase(userId, productId, quantity, payMethod);
    }

    @PostMapping(value = "/taskNotify")
    public ApiResponse taskNotify(@RequestBody Map<String,Object> params) {
        LOGGER.warn("======回调通知数据======="+params);
        return ApiResponse.returnSuccess();
    }
}