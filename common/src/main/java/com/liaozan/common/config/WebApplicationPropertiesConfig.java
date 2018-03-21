package com.liaozan.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@Configuration
@ConfigurationProperties(prefix = "customerapplication.config", ignoreUnknownFields = false)
public class WebApplicationPropertiesConfig {

	private String savapath;

	private String domain;

	private String nginxserverprefix;

	public String getSavapath () {
		return savapath;
	}

	public void setSavapath (String savapath) {
		this.savapath = savapath;
	}

	public String getDomain () {
		return domain;
	}

	public void setDomain (String domain) {
		this.domain = domain;
	}

	public String getNginxserverprefix () {
		return nginxserverprefix;
	}

	public void setNginxserverprefix (String nginxserverprefix) {
		this.nginxserverprefix = nginxserverprefix;
	}
}
