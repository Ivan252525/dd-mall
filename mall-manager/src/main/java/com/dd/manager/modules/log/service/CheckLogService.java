package com.dd.manager.modules.log.service;

import com.dd.manager.modules.log.dao.CheckLogDao;
import com.dd.manager.modules.log.entity.CheckLogEntity;
import com.dd.manager.modules.log.vo.CheckLogVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2019-04-08 21:16:16
 */
public interface CheckLogService {

	void insert(CheckLogEntity entity);

	Integer getCheckCount(String code);

	Date getFirstCheckTime(String productCode);

	Integer getActiveCheckCount(Date beginTime, Date endTime);

	Integer getAllCheckCount(Date beginTime, Date endTime);

	List<CheckLogVO> selectVOList(Map<String, Object> param);

}

