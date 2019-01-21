package com.rayvision.rpc.business.service;

import com.rayvision.rpc.business.entity.Product;
import com.rayvision.rpc.common.exception.BusinessException;

public interface ProductService {

    Product deductionStock(Integer productId, Integer quantity) throws BusinessException;
}
