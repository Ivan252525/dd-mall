package com.dd.manager.modules.product.service;

import com.dd.manager.modules.product.entity.ProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 产品表
 *
 * @author lihj
 * @email 
 * @date 2019-04-12 21:57:30
 */
public interface ProductService {


    List<ProductEntity> selectList(Map<String, Object> params);

}

