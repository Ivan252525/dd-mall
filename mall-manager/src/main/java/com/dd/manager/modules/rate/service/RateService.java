package com.dd.manager.modules.rate.service;

import com.dd.manager.modules.rate.entity.RateEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2019-07-05 00:22:29
 */
public interface RateService{

	List<RateEntity> getAllRate();

	void updateBatchById(List<RateEntity> list);

	List<RateEntity> selectByMap(HashMap<String, Object> map);

}

