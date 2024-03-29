package com.dd.manager.web.config.wx;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wechat mp properties
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {
  /**
   * 设置微信公众号的appid
   */
  private String appId;

  /**
   * 设置微信公众号的app secret
   */
  private String secret;

  /**
   * 设置微信公众号的token
   */
  private String token;

  /**
   * 设置微信公众号的EncodingAESKey
   */
  private String aesKey;
  
  private String consultReplyToUserId;
  
  private String consultNoticeLawyerId;
  
  private String caseUndertakeNoticeUserId;
  
  private String newCasePublishNoticeId;
  
  public String getAppId() {
    return this.appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getSecret() {
    return this.secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAesKey() {
    return this.aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }

	public String getConsultReplyToUserId() {
		return consultReplyToUserId;
	}
	
	public void setConsultReplyToUserId(String consultReplyToUserId) {
		this.consultReplyToUserId = consultReplyToUserId;
	}

	public String getConsultNoticeLawyerId() {
		return consultNoticeLawyerId;
	}

	public void setConsultNoticeLawyerId(String consultNoticeLawyerId) {
		this.consultNoticeLawyerId = consultNoticeLawyerId;
	}

	public String getCaseUndertakeNoticeUserId() {
		return caseUndertakeNoticeUserId;
	}

	public void setCaseUndertakeNoticeUserId(String caseUndertakeNoticeUserId) {
		this.caseUndertakeNoticeUserId = caseUndertakeNoticeUserId;
	}

	public String getNewCasePublishNoticeId() {
		return newCasePublishNoticeId;
	}

	public void setNewCasePublishNoticeId(String newCasePublishNoticeId) {
		this.newCasePublishNoticeId = newCasePublishNoticeId;
	}
	
	
}
