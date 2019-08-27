package com.dd.manager.modules.wechat.service;

import com.dd.manager.web.config.wx.WechatMpProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信接口类
 *
 * @author lihj
 */

@Component
public class WechatService {
	@Autowired
	private WxMpService wxMpService;
	@Autowired
	private WechatMpProperties properties;
	@Autowired
	private WxUserService wxUserService;
	
	private static final Logger log = LoggerFactory.getLogger(WechatService.class);
	

}
