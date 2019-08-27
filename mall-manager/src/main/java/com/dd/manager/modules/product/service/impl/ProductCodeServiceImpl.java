package com.dd.manager.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.manager.modules.product.dao.ProductCodeDao;
import com.dd.manager.modules.product.entity.ProductCodeEntity;
import com.dd.manager.modules.product.service.ProductCodeService;
import com.dd.manager.modules.product.vo.ProductCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("productCodeService")
public class ProductCodeServiceImpl implements ProductCodeService {

	@Autowired
	ProductCodeDao productCodeDao;

	@Override
	public List<ProductCodeVO> selectVOList(Map<String, Object> param) {
		return productCodeDao.selectVOList(param);
	}

	@Override
	public void insertBatch(List<ProductCodeEntity> list, int count) {
		for (int i=0; i<count; i++) {
			ProductCodeEntity productCodeEntity = list.get(i);
			productCodeDao.insert(productCodeEntity);
		}
	}

	@Override
	public ProductCodeEntity selectOne(QueryWrapper<ProductCodeEntity> query) {
		return productCodeDao.selectOne(query);
	}

}
