package com.dd.manager.modules.log.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dd.manager.modules.log.entity.RedpackLogEntity;
import com.dd.manager.modules.log.vo.RedpackLogVO;
import com.dd.manager.web.result.R;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2019-04-03 23:10:27
 */
public interface RedpackLogService extends IService<RedpackLogEntity> {

	R send(RedpackLogEntity log);

	List<RedpackLogEntity> selectPage(Map<String, Object> param);

	R getData(RedpackLogEntity log);

	Map<String, Object> getTodyData();

	Map<String, Object> getAllData();
	
	List<RedpackLogVO> selectVOList(Map<String, Object> param);

	Map<String, Object> getStatement(String beginTime, String endTime) throws ParseException;

	Map<String, Object> getThisMonthData();

	void test1();



}

