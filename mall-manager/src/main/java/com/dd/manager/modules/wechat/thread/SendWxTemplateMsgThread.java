package com.dd.manager.modules.wechat.thread;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendWxTemplateMsgThread extends Thread{
	
	private WxMpTemplateMsgService wxMpTemplateMsgService;
	
	private WxMpTemplateMessage msg;
	
	private static final Logger log =  LoggerFactory.getLogger(SendWxTemplateMsgThread.class);
	public SendWxTemplateMsgThread(WxMpTemplateMsgService wxMpTemplateMsgService, WxMpTemplateMessage msg) {
		super();
		this.wxMpTemplateMsgService = wxMpTemplateMsgService;
		this.msg = msg;
	}


	@Override
	public void run() {
		try {
			wxMpTemplateMsgService.sendTemplateMsg(msg);
		} catch (WxErrorException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		
	}

}
