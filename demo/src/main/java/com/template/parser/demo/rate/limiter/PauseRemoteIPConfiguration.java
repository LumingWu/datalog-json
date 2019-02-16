package com.template.parser.demo.rate.limiter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "translation-service.rate-limiter.remote-ip")
class PauseRemoteIPConfiguration {
	
	@Getter @Setter
	private int countLimit;
	
	@Getter @Setter
	private long pauseTime;
		
}
