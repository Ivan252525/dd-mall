package com.dd.manager.modules.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.manager.modules.product.entity.ProductCodeEntity;
import com.dd.manager.modules.product.vo.ProductCodeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2019-04-26 23:48:50
 */
@Repository
public interface ProductCodeDao extends BaseMapper<ProductCodeEntity> {

	List<ProductCodeVO> selectVOList(Map<String, Object> param);
	
}
