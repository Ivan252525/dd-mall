package com.dd.manager.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.manager.modules.log.entity.CheckLogEntity;
import com.dd.manager.modules.log.vo.CheckLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
@Repository
public interface CheckLogDao extends BaseMapper<CheckLogEntity> {

	Integer getCheckCount(@Param("code") String code);

	Date getFirstCheckTime(@Param("code") String productCode);

	Integer getActiveCheckCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	Integer getAllCheckCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	List<CheckLogVO> selectVOList(Map<String, Object> param);
	
}
