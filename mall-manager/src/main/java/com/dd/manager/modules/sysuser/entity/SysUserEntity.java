package com.dd.manager.modules.sysuser.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2019-04-01 21:22:48
 */
@TableName("tb_sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	private String user_name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private Date create_time;

	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * 获取：用户名
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreate_time() {
		return create_time;
	}
}
