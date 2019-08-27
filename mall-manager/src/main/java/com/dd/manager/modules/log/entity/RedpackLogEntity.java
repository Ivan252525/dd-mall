package com.dd.manager.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dd.manager.web.annotation.ParamCheck;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lihj
 * @email 
 * @date 2019-04-03 23:10:27
 */
@TableName("tb_redpack_log")
public class RedpackLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * openId
	 */
	@ParamCheck(notNull = true)
	private String openId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 流水号
	 */
	private String orderNo;
	/**
	 * 产品验证code
	 */
	@ParamCheck(notNull = true)
	private String productCode;
	/**
	 * 产品编号
	 */
	@ParamCheck(notNull = true)
	private String productId;
	/**
	 * 经度
	 */
	@ParamCheck(notNull = true)
	private String longitude;
	/**
	 * 纬度
	 */
	@ParamCheck(notNull = true)
	private String latitude;
	
	private Integer totalAmount;
	
	private Integer status;
	
	private Date sendTime;
	
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
	 * 设置：经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：经度
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 设置：纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：纬度
	 */
	public String getLatitude() {
		return latitude;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
