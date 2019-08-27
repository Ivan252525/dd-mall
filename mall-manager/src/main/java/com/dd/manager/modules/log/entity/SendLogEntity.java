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
 * @date 2019-07-05 23:12:52
 */
@TableName("tb_send_log")
public class SendLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 状态 1-未发货 2-已发货
	 */
	private Integer status;
	/**
	 * 
	 */
	@ParamCheck(notNull = true)
	private String openId;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 产品验证code
	 */
	@ParamCheck(notNull = true)
	private String productCode;
	/**
	 * 
	 */
	@ParamCheck(notNull = true)
	private String productId;
	/**
	 * 
	 */
	@ParamCheck(notNull = true)
	private String longitude;
	/**
	 * 
	 */
	@ParamCheck(notNull = true)
	private String latitude;
	/**
	 * 地址
	 */
	@ParamCheck(notNull = true)
	private String address;
	/**
	 * 省份
	 */
	@ParamCheck(notNull = true)
	private String province;
	/**
	 * 城市
	 */
	@ParamCheck(notNull = true)
	private String city;
	/**
	 * 区
	 */
	@ParamCheck(notNull = true)
	private String area;
	@ParamCheck(notNull = true)
	private String receiver;
	@ParamCheck(notNull = true)
	private String phone;
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
	 * 设置：状态 1-未发货 2-已发货
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1-未发货 2-已发货
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 设置：
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省份
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：区
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：区
	 */
	public String getArea() {
		return area;
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
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
