package com.dd.manager.modules.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.manager.modules.product.entity.ProductCodeEntity;
import com.dd.manager.modules.product.vo.ProductCodeVO;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2019-04-26 23:48:50
 */
public interface ProductCodeService {

	List<ProductCodeVO> selectVOList(Map<String, Object> param);

	void insertBatch(List<ProductCodeEntity> list, int count);

	ProductCodeEntity selectOne(QueryWrapper<ProductCodeEntity> query);

}

