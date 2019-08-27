package com.dd.manager.modules.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.manager.modules.product.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 产品表
 * 
 * @author lihj
 * @email 
 * @date 2019-04-12 21:57:30
 */
@Repository
public interface ProductDao extends BaseMapper<ProductEntity> {
	
}
