package com.dd.manager.modules.rate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.manager.modules.rate.entity.RateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2019-07-05 00:22:29
 */
@Repository
public interface RateDao extends BaseMapper<RateEntity> {

	List<RateEntity> getAllRate();
	
}
