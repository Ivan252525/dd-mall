package com.dd.manager.modules.wechat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.manager.modules.wechat.entity.WxUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2018-08-26 23:46:27
 */
@Mapper
public interface WxUserDao extends BaseMapper<WxUserEntity> {

	WxUserEntity getByOpenId(@Param("openId") String openId);
	
}
