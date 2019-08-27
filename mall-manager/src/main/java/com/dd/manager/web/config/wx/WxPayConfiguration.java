package com.dd.manager.web.config.wx;

import com.dd.manager.web.config.profile.RedPackProfile;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayConfiguration {
  private WxPayProperties properties;
  
  private RedPackProfile redpackProfile;
  @Autowired
  public WxPayConfiguration(WxPayProperties properties,RedPackProfile redpackProfile) {
    this.properties = properties;
    this.redpackProfile = redpackProfile;
  }
  
  @Bean
  @ConditionalOnMissingBean
  public WxPayService wxService() {
    WxPayConfig payConfig = new WxPayConfig();
    payConfig.setAppId(StringUtils.trimToNull(this.properties.getAppId()));
    payConfig.setMchId(StringUtils.trimToNull(this.properties.getMchId()));
    payConfig.setSubAppId(StringUtils.trimToNull(this.properties.getSubAppId()));
    payConfig.setSubMchId(StringUtils.trimToNull(this.properties.getSubMchId()));
    payConfig.setKeyPath(StringUtils.trimToNull(this.properties.getKeyPath()));
    payConfig.setSignType("MD5");
    WxPayService wxPayService = new WxPayServiceImpl();
    wxPayService.setConfig(payConfig);
	if("test".equals(redpackProfile.getProfile()) || "dev".equals(redpackProfile.getProfile())) {
		try {
			String sandboxSignKey = wxPayService.getSandboxSignKey();
			payConfig.setMchKey(sandboxSignKey);
			payConfig.setUseSandboxEnv(true);
		} catch (WxPayException e) {
			e.printStackTrace();
		}
	}else {
		payConfig.setMchKey(StringUtils.trimToNull(this.properties.getMchKey()));
	}
	wxPayService.setConfig(payConfig);
    return wxPayService;
  }

}
