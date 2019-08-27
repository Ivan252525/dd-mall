package com.dd.manager.modules.wechat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2018-08-26 23:46:27
 */
@TableName("tb_wx_user")
public class WxUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 微信昵称
	 */
	private String nickName;
	/**
	 * 微信openId
	 */
	private String openId;
	
	private Date createTime;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：微信昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：微信昵称
	 */
	public String getNickName() {
		return nickName;
	}
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 将WxMpUser 转化为 WxUserEntity（不含userId）
	 * @param wxUser
	 * @return
	 */
	public static WxUserEntity transfor(WxMpUser wxUser) {
		if(wxUser == null) {
			return null;
		}
		WxUserEntity wxUserEntity = new WxUserEntity();
		wxUserEntity.setNickName(wxUser.getNickname());
		wxUserEntity.setCreateTime(new Date());
		wxUserEntity.setOpenId(wxUser.getOpenId());
		return wxUserEntity;
	}
}
