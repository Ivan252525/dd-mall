package com.dd.manager.modules.wechat.service;


import com.dd.manager.modules.wechat.entity.WxUserEntity;

/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2018-08-26 23:46:27
 */
public interface WxUserService {
	/**
	 * 根据openId获取用户微信账号信息
	 * @param openId 
	 * @return
	 */
	WxUserEntity getByOpenId(String openId);
}

