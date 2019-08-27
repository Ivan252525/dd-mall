package com.dd.manager.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.manager.modules.log.entity.SendLogEntity;
import com.dd.manager.modules.log.vo.SendLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2019-07-05 23:12:52
 */
@Repository
public interface SendLogDao extends BaseMapper<SendLogEntity> {

	List<SendLogVO> selectVOList(Map<String, Object> param);

	void batchSend(@Param("ids") Long[] ids);
	
}
