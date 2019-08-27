package com.dd.manager.modules.log.service.impl;

import com.dd.manager.modules.log.dao.CheckLogDao;
import com.dd.manager.modules.log.entity.CheckLogEntity;
import com.dd.manager.modules.log.service.CheckLogService;
import com.dd.manager.modules.log.vo.CheckLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("checkLogService")
public class CheckLogServiceImpl implements CheckLogService {

	@Autowired
	CheckLogDao checkLogDao;

	@Override
	public void insert(CheckLogEntity entity) {
		checkLogDao.insert(entity);
	}

	@Override
	public Integer getCheckCount(String code) {
		return checkLogDao.getCheckCount(code);
	}

	@Override
	public Date getFirstCheckTime(String productCode) {
		return checkLogDao.getFirstCheckTime(productCode);
	}

	@Override
	public Integer getActiveCheckCount(Date beginTime, Date endTime) {
		return checkLogDao.getActiveCheckCount(beginTime,endTime);
	}

	@Override
	public Integer getAllCheckCount(Date beginTime, Date endTime) {
		return checkLogDao.getAllCheckCount(beginTime,endTime);
	}

	@Override
	public List<CheckLogVO> selectVOList(Map<String, Object> param) {
		return checkLogDao.selectVOList(param);
	}

}
