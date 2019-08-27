package com.dd.manager.web.config.sys;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sys.config")
@Component
public class SysProperties {
	private String host;
	
	private int qrWidth;
	
	private int qrHeight;
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getQrWidth() {
		return qrWidth;
	}

	public void setQrWidth(int qrWidth) {
		this.qrWidth = qrWidth;
	}

	public int getQrHeight() {
		return qrHeight;
	}

	public void setQrHeight(int qrHeight) {
		this.qrHeight = qrHeight;
	}
	
	
	
}
