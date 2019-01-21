package com.rayvision.rpc.business.rpc;

import com.rayvision.rpc.api.business.RpcProductService;
import com.rayvision.rpc.business.entity.Product;
import com.rayvision.rpc.business.service.ProductService;
import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.common.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service(provider = "providerConfig",protocol = "protocolConfig")
public class RpcProductServiceImpl implements RpcProductService {

    @Autowired
    private ProductService productService;

    @Override
    public ApiResponse deductionProductStock(Integer productId, Integer quantity) {
        Product product = productService.deductionStock(productId, quantity);
        return ApiResponse.returnSuccess(ObjectUtil.objectToMap(product));
    }
}