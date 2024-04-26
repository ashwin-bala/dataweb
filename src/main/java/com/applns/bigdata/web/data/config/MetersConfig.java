package com.applns.bigdata.web.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;

@Configuration
public class MetersConfig {

	 @Bean    
	    public MeterRegistry getMeterRegistry() {
	        CompositeMeterRegistry meterRegistry = new CompositeMeterRegistry();        
	        return meterRegistry;    
	    }
}
