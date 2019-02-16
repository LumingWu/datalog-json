package com.template.parser.demo.rate.limiter;

public interface IPRateLimiter {
	
	boolean tryAcquire(String remoteIP);
	
}
