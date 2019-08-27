package com.dd.manager.modules.product.vo;

import java.util.Date;

public class ProductCodeVO {
	private String code;
	
	private Date createTime;
	
	private Integer checkCount;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}
}
