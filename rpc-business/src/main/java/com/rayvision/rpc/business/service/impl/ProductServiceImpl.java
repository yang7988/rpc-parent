package com.rayvision.rpc.business.service.impl;

import com.rayvision.rpc.business.entity.Product;
import com.rayvision.rpc.business.mapper.ProductMapper;
import com.rayvision.rpc.business.service.ProductService;
import com.rayvision.rpc.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product deductionStock(Integer productId, Integer quantity) throws BusinessException {
        Product product = productMapper.selectByPrimaryKey(productId);
        Integer stock = product.getStock() - quantity;
        Product productForUpdate = new Product();
        productForUpdate.setId(productId);
        productForUpdate.setStock(stock);
        productForUpdate.setUpdateDate(new Date());
        productMapper.updateByPrimaryKeySelective(productForUpdate);
        return productForUpdate;
    }
}