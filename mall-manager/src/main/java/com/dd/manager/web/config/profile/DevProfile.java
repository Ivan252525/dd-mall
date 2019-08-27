package com.dd.manager.web.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DevProfile implements RedPackProfile {

	@Override
	public String getProfile() {
		return "http://m.zhong-shang.com";
	}

	@Override
	public String getDomain() {
		return "http://m.zhong-shang.com";
	}

	@Override
	public String getApiDomain() {
		return "http://a.zhong-shang.com";
	}

	@Override
	public String getForeDomain() {
		return "http://m.zhong-shang.com";
	}
	
}
