package com.dd.manager.modules.log.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.manager.modules.log.entity.RedpackLogEntity;
import com.dd.manager.modules.log.entity.SendLogEntity;
import com.dd.manager.modules.log.vo.SendLogVO;
import com.dd.manager.web.result.R;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2019-07-05 23:12:52
 */
public interface SendLogService {

	R getData(RedpackLogEntity log);

	R send(SendLogEntity log);

	List<SendLogVO> selectVOList(Map<String, Object> param);

	void batchSend(Long[] ids);

	SendLogEntity selectOne(QueryWrapper<SendLogEntity> query);

}

