package com.dd.manager.modules.rate.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2019-07-05 00:22:29
 */
@TableName("tb_rate")
public class RateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 类型：1-红包比例 2-再来一盒比例
	 */
	private Integer type;
	/**
	 * 比例（0-100）
	 */
	private Integer rate;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：类型：1-红包比例 2-再来一盒比例
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型：1-红包比例 2-再来一盒比例
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：比例（0-100）
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	/**
	 * 获取：比例（0-100）
	 */
	public Integer getRate() {
		return rate;
	}
}
