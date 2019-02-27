package com.predicate.json.demo.rate.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class UserKeyResolvers {

    @Bean
    KeyResolver remoteIPResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

}
