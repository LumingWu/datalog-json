package com.template.parser.demo.rate.limiter;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
class PauseIPRateLimiter implements IPRateLimiter {
	
	private final LoadingCache<String, Boolean> remoteIPCache;
	
	private final PauseRemoteIPConfiguration pauseRemoteIPConfiguration;
	
	@Autowired
	PauseIPRateLimiter(PauseRemoteIPConfiguration pauseRemoteIPConfiguration) {
		this.pauseRemoteIPConfiguration = pauseRemoteIPConfiguration;
		CacheLoader<String, Boolean> loader;
	    loader = new CacheLoader<String, Boolean>() {
	        @Override
	        public Boolean load(String key) {
	            return true;
	        }
	    };
		remoteIPCache = CacheBuilder.newBuilder()
				.expireAfterAccess(pauseRemoteIPConfiguration.getPauseTime(), TimeUnit.MILLISECONDS)
				.build(loader);
	}

	@Override
	public synchronized boolean tryAcquire(String remoteIP) {
		if(remoteIPCache.size() > pauseRemoteIPConfiguration.getCountLimit()
				|| remoteIPCache.getIfPresent(remoteIP) != null) {
			return false;
		}
		remoteIPCache.getUnchecked(remoteIP);
		return true;
	}
	
}
