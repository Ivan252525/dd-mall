package com.dd.manager.modules.log.vo;

import java.util.Date;

public class CheckLogVO {
	
	private String productCode;
	
	private Integer scanCount;
	
	private Date firstScanTime;
	
	private Date scanTime;
	
	private String longitude;
	
	private String latitude;
	
	private Date createTime;
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getScanCount() {
		return scanCount;
	}

	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}

	public Date getFirstScanTime() {
		return firstScanTime;
	}

	public void setFirstScanTime(Date firstScanTime) {
		this.firstScanTime = firstScanTime;
	}

	public Date getScanTime() {
		return scanTime;
	}

	public void setScanTime(Date scanTime) {
		this.scanTime = scanTime;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
