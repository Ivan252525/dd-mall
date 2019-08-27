package com.dd.manager.modules.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.manager.modules.log.entity.RedpackLogEntity;
import com.dd.manager.modules.log.vo.RedpackLogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2019-04-03 23:10:27
 */
@Repository
public interface RedpackLogDao extends BaseMapper<RedpackLogEntity> {

	int checkRandom(@Param("now") Date now, @Param("genRanNum") String genRanNum);

	void insertgenRanNum(@Param("expire") Date expire, @Param("genRanNum") String genRanNum);

	List<RedpackLogEntity> selectPage(Map<String, Object> param);

	Integer getSendRedPackCount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	BigDecimal getSendRedpackAmount(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	List<RedpackLogVO> selectVOList(Map<String, Object> param);
	
}
