package com.dd.manager.modules.wechat.wxhandler;

import com.dd.manager.modules.wechat.entity.WxUserEntity;
import com.dd.manager.modules.wechat.service.WxUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {
    @Autowired
    private WxUserService wxUserService;
    private static final Logger log = LoggerFactory.getLogger(SubscribeHandler.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        WxMpUser userWxInfo = weixinService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);

        if (userWxInfo != null) {
            String openId = userWxInfo.getOpenId();
            WxUserEntity wxUser = wxUserService.getByOpenId(openId);
            if (wxUser == null) {
//    	  //新关注，静默注册
//    	  try {
//    		  ServiceResult<IndividualUserVo> rs = userService.saveWxMpUserInfo(userWxInfo);
//        	  if(!rs.isSucceed()) {
//        		  log.error("\n新关注公众号用户保存失败！openId:{},unionId:{},关注时间：{},微信昵称：{}.",
//        				  userWxInfo.getOpenId(),userWxInfo.getUnionId(),DateUtils.format(new Date(),
//        						  DateUtils.DATE_TIME_PATTERN),userWxInfo.getNickname());
//        	  }
//		} catch (Exception e) {
//			log.error("\n由于系统报错新关注公众号用户保存失败！openId:{},unionId:{},关注时间：{},微信昵称：{}.报错信息：{}", userWxInfo.getOpenId(),userWxInfo.getUnionId(),DateUtils.format(new Date(),
//        						  DateUtils.DATE_TIME_PATTERN),userWxInfo.getNickname(), e);
//		}

            }
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return WxMpXmlOutMessage.TEXT()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser())
                    .content("感谢关注典律师，但是这里什么都没有，啊哈哈哈哈哈~！！！！").build();
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}
