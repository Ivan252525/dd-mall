package com.dd.manager.modules.rate.service.impl;

import com.dd.manager.modules.rate.dao.RateDao;
import com.dd.manager.modules.rate.entity.RateEntity;
import com.dd.manager.modules.rate.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service("rateService")
public class RateServiceImpl implements RateService {

	@Autowired
	RateDao rateDao;

	@Override
	public List<RateEntity> getAllRate() {
		return rateDao.getAllRate();
	}

	@Override
	@Transactional
	public void updateBatchById(List<RateEntity> list) {
		if (list != null) {
			for (RateEntity rateEntity : list) {
				rateDao.updateById(rateEntity);
			}
		}
	}

	@Override
	public List<RateEntity> selectByMap(HashMap<String, Object> map) {
		return rateDao.selectByMap(map);
	}

}
