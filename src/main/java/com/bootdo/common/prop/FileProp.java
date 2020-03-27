package com.bootdo.common.prop;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "huhste.file", ignoreUnknownFields = true)
public class FileProp {
	@NotNull
	private String basePath;
	@NotNull
	private String tempPath;

	public FileProp() {

	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
		if (!this.basePath.endsWith("/")) {
			this.basePath = this.basePath + "/";
		}
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
		if (!this.tempPath.endsWith("/")) {
			this.tempPath = this.tempPath + "/";
		}
	}


}
