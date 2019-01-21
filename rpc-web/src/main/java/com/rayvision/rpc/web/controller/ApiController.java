package com.rayvision.rpc.web.controller;

import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/purchase/product/{userId}/{payMethod}/{productId}/{quantity}")
    public ApiResponse purchaseProduct(@PathVariable Integer userId, @PathVariable Integer productId,
                                       @PathVariable Integer quantity, @PathVariable Byte payMethod) {
        return userService.purchaseProduct(userId, productId, quantity, payMethod);
    }
}