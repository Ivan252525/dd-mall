package com.dd.manager.modules.wechat.controller;


import cn.hutool.core.codec.Base64;
import com.alibaba.druid.util.StringUtils;
import com.dd.common.utils.md5.MD5;
import com.dd.manager.modules.log.entity.CheckLogEntity;
import com.dd.manager.modules.log.service.CheckLogService;
import com.dd.manager.modules.log.service.RedpackLogService;
import com.dd.manager.modules.wechat.service.WxUserService;
import com.dd.manager.web.config.profile.RedPackProfile;
import com.dd.manager.web.config.sys.SysProperties;
import com.dd.manager.web.result.R;
import com.dd.manager.web.security.interceptor.JwtUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/openapi/wechat")
public class WechatController {
	private static final Logger log = LoggerFactory.getLogger(WechatController.class);
	@Autowired
    protected WxMpService wxMpService;
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxMpMessageRouter router;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SysProperties sysProperties;
	@Resource
	private RedPackProfile redpackProfile;
	@Autowired
	private RedpackLogService redpackLogService;
	@Autowired
	private CheckLogService checkLogService;
	private static final String pageUrl = "http://openapi/wechat";
	
	private static final String noncestr = "tf65KN2XQfdsfdwa";
	
	private static final Object lock = new Object();
	@GetMapping(produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String wxTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			String token = "Hansk880129";
			log.info("wxtest:=========signature:{},timestamp:{},nonce:{},echostr:{},token:{}",signature,timestamp,nonce,echostr,token);
			boolean flag = wxMpService.checkSignature(timestamp, nonce, signature);
			if(flag) {
				return echostr;
			}
			
			
			
			
		} catch (Exception e) {
			log.error("微信测试报错啦！！！！！！！！！！！！！！！"+e.getMessage(),e);
		}
		return "";
	}
	
	
	@PostMapping(produces = "application/xml; charset=UTF-8")
	@ResponseBody
	public String post(@RequestBody String requestBody,
	                     @RequestParam("signature") String signature,
	                     @RequestParam("timestamp") String timestamp,
	                     @RequestParam("nonce") String nonce,
	                     @RequestParam(name = "encrypt_type",
	                         required = false) String encType,
	                     @RequestParam(name = "msg_signature",
	                         required = false) String msgSignature) {
	    log.info(
	        "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
	            + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
	        signature, encType, msgSignature, timestamp, nonce, requestBody);

	    if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
	      throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
	    }
	    String out = null;
	    if (encType == null) {
	      // 明文传输的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
	      WxMpXmlOutMessage outMessage = this.route(inMessage);
	      if (outMessage == null) {
	        return "";
	      }
	      out = outMessage.toXml();
	    } else if ("aes".equals(encType)) {
	      // aes加密的消息
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
	          requestBody, this.wxMpService.getWxMpConfigStorage(), timestamp,
	          nonce, msgSignature);
	      log.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
	      WxMpXmlOutMessage outMessage = this.route(inMessage);
	      if (outMessage == null) {
	        return "";
	      }

	      out = outMessage
	          .toEncryptedXml(this.wxMpService.getWxMpConfigStorage());
	    }

	    log.debug("\n组装回复信息：{}", out);

	    return out;
	  }

	  private WxMpXmlOutMessage route(WxMpXmlMessage message) {
	    try {
	      return this.router.route(message);
	    } catch (Exception e) {
	      log.error(e.getMessage(), e);
	    }

	    return null;
	  }
	  
	  @RequestMapping("/oauth/{encodeurl}")
	  public String oauth(@RequestParam String code, @RequestParam String state, HttpServletResponse response, @PathVariable("encodeurl") String encodeurl) {
		  String decodeStr = Base64.decodeStr(encodeurl, "UTF-8");
		  log.info("decodeStr:{}",decodeStr);
		  String substring = decodeStr.substring(decodeStr.indexOf("?") + 1);//?productCode="+productCode+"&productId="+productId+"&longitude="+longitude+"&latitude="+latitude
		  String[] split = substring.split("=");
		  String[] split2 = split[1].split("&");
		  String productCode = split2[0];
		  String[] split3 = split[2].split("&");
		  String productId = split3[0];
		  String[] split4 = split[3].split("&");
		  String longitude = split4[0];
		  String latitude = split[4];
//		  String decodeProductCode = Base64.decodeStr(productCode);
//		  String decodeProductId = Base64.decodeStr(productId);
		  log.info("================productCode:{},productId:{}============",productCode,productId);
		  try {
//			  RedpackLogEntity cur_entity = null;
//			  synchronized (lock) {
//				cur_entity = this.redpackLogService.selectOne(new EntityWrapper<RedpackLogEntity>().eq("product_code", productCode));
//				if(cur_entity == null) {
//					cur_entity = new RedpackLogEntity();
//					cur_entity.setProductCode(decodeProductCode);
//					cur_entity.setProductId(decodeProductId);
//					cur_entity.setCreateTime(new Date());
//					cur_entity.setStatus(1);//未发送
//					this.redpackLogService.insert(cur_entity);
//				}
//			  }
			//插入查询日志
			CheckLogEntity checkLogEntity = new CheckLogEntity();
			checkLogEntity.setCreateTime(new Date());
			checkLogEntity.setLongitude(longitude);
			checkLogEntity.setLatitude(latitude);
			checkLogEntity.setProductCode(productCode);
			checkLogService.insert(checkLogEntity);
			WxMpOAuth2AccessToken oauthToken = wxMpService.oauth2getAccessToken(code);
			String openId = oauthToken.getOpenId();
//			if(cur_entity != null) {
//				cur_entity.setOpenId(openId);
//				this.redpackLogService.updateById(cur_entity);
//			}
			String url = decodeStr + "&openId="+openId;
			return "redirect:" + url;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return "redirect:"+redpackProfile.getForeDomain();
		}
		  
	  }
	  
	  
	  
	 @GetMapping("/getSinature")
	 @ResponseBody
	 public R getSinature(String url) throws Exception {
		 if(StringUtils.isEmpty(url)) {
			 return R.error("参数有误");
		 }
		 String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		 url = new String(Base64Utils.decodeFromUrlSafeString(url),"UTF-8");
		 String jsapiTicket = wxMpService.getJsapiTicket();
		 StringBuilder sb = new StringBuilder();
		 sb.append("jsapi_ticket=");
		 sb.append(jsapiTicket);
		 sb.append("&noncestr=");
		 sb.append(noncestr);
		 sb.append("&timestamp=");
		 sb.append(timestamp);
		 sb.append("&url="+url);
		 String signature = SHA1.gen(sb.toString());
		 Sinature s = new Sinature();
		 s.setAppId(wxMpService.getWxMpConfigStorage().getAppId());
		 s.setNonceStr(noncestr);
		 s.setTimestamp(timestamp);
		 s.setSignature(signature);
		 return R.ok().put("data", s);
	 }
	 
	 @GetMapping("/getOauthUrl")
	 @ResponseBody
	 public R getOauthUrl(String productCode,String productId,String longitude,String latitude,String type) {
		 if(StringUtils.isEmpty(productCode) || StringUtils.isEmpty(productId) || StringUtils.isEmpty(longitude) || StringUtils.isEmpty(latitude)|| StringUtils.isEmpty(type)) {
			 return R.error("参数有误！");
		 }
		 String encodeUrlSafe = "";
		 //解密type
		 if(MD5.md5(productCode+"1").equals(type)) {
			encodeUrlSafe = Base64.encodeUrlSafe(redpackProfile.getForeDomain()+"/hong.html?pcode="+productCode+"&pid="+productId+"&longitude="+longitude+"&latitude="+latitude,"UTF-8");
		 }else if(MD5.md5(productCode+"2").equals(type)) {
			encodeUrlSafe = Base64.encodeUrlSafe(redpackProfile.getForeDomain()+"/song.html?pcode="+productCode+"&pid="+productId+"&longitude="+longitude+"&latitude="+latitude,"UTF-8");
		 }
		 
		 String redirectURI = redpackProfile.getApiDomain()+"/openapi/wechat/oauth/"+encodeUrlSafe;
		 String oauthUrl = this.wxMpService.oauth2buildAuthorizationUrl(redirectURI, WxConsts.OAuth2Scope.SNSAPI_BASE, "111");
		 return R.ok().put("data", oauthUrl);
	 }
	 
	 
	 
	 class Sinature {
		private String appId;
		private String timestamp;
		private String nonceStr;
		private String signature;
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public String getNonceStr() {
			return nonceStr;
		}
		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		
		
	 }
}
