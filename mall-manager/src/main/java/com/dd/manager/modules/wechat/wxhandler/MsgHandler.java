package com.dd.manager.modules.wechat.wxhandler;

import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.builder.outxml.TextBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) {

    if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
    	String recContent = wxMessage.getContent();
    	String ansContent = "";
    	if("典律师真棒".equals(recContent)) {
    		ansContent = "谢谢！";
    	}else {
    		ansContent = "输入\"典律师真棒\"会有惊喜哦！";
    	}
    	
      return WxMpXmlOutMessage.TEXT()
    		  .fromUser(wxMessage.getToUser())
    		  .toUser(wxMessage.getFromUser())
    		  .content(ansContent).build();
    }

    //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
    try {
      if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
          && weixinService.getKefuService().kfOnlineList()
          .getKfOnlineList().size() > 0) {
        return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
            .fromUser(wxMessage.getToUser())
            .toUser(wxMessage.getFromUser()).build();
      }
    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    // 组装回复消息
    String content = "测试回复" ;

    return new TextBuilder().content(content).build();

  }

}
