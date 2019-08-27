package com.dd.manager.modules.wechat.service.impl;

import com.dd.manager.modules.wechat.dao.WxUserDao;
import com.dd.manager.modules.wechat.entity.WxUserEntity;
import com.dd.manager.modules.wechat.service.WxUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {
	
	@Autowired
	private WxUserDao wxUserDao;
	
	private static final Logger log = LoggerFactory.getLogger(WxUserServiceImpl.class);
	@Override
	public WxUserEntity getByOpenId(String openId) {
		return wxUserDao.getByOpenId(openId);
	}

	

}
