package com.sarvatra.microservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigService {

	@Value("${smui.jdbc.driverClassName}")
    public String driverClassName;

    @Value("${smui.jdbc.url}")
    public String url;

    @Value("${smui.jdbc.username}")
    public String username;

    @Value("${smui.jdbc.password}")
    public String password;

    @Value("${smui.minPoolSize}")
    public int minPoolSize;

    @Value("${smui.maxPoolSize}")
    public int maxPoolSize;

    @Value("${smui.initialPoolSize}")
    public int initialPoolSize;

    @Value("${smui.maxIdleTime}")
    public int maxIdleTime;

    @Value("${smui.idleConnectionTestPeriod}")
    public int idleConnectionTestPeriod;

    @Value("${smui.unreturnedConnectionTimeout}")
    public int unreturnedConnectionTimeout;

    @Value("${smui.debugUnreturnedConnectionStackTraces}")
    public boolean debugUnreturnedConnectionStackTraces;
    
    @Value("${db.connection.timeout}")
	public int dbConnectionTimeOut;
	
	@Value("${db.retry.attemps}")
	public int dbRetryAttempts;
	
	@Value("${db.pool.checkout.timeout.millisec}")
	public int dbPoolCheckoutTimeout;
}
