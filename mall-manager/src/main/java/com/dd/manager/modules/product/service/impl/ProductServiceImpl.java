package com.dd.manager.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.manager.modules.product.dao.ProductDao;
import com.dd.manager.modules.product.entity.ProductEntity;
import com.dd.manager.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductEntity> selectList(Map<String, Object> params) {
        return productDao.selectList(new QueryWrapper<ProductEntity>());
    }
}
