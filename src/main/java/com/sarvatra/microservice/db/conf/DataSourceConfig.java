package com.sarvatra.microservice.db.conf;

import java.beans.PropertyVetoException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sarvatra.microservice.ConfigService;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.sarvatra.microservice.db.repository" })
@EnableSpringDataWebSupport
public class DataSourceConfig {


	@Autowired
	private ConfigService configService;
	
	@Autowired
	private HibernateProperties hibernateProperties;

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException, SQLException {
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(dataSource());
		lfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lfb.setPackagesToScan("com.sarvatra.microservice.db.entity");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		lfb.setJpaVendorAdapter(vendorAdapter);
		lfb.setJpaProperties(hibernateProperties());
		return lfb;
	}

	@Bean
	DataSource dataSource() throws PropertyVetoException, SQLException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(configService.driverClassName);
		ds.setJdbcUrl(configService.url);
		ds.setUser(decode(configService.username));
		ds.setPassword(decode(configService.password));
		ds.setMinPoolSize(configService.minPoolSize);
		ds.setMaxPoolSize(configService.maxPoolSize);
		ds.setInitialPoolSize(configService.initialPoolSize);
		ds.setMaxIdleTime(configService.maxIdleTime);
		ds.setIdleConnectionTestPeriod(configService.idleConnectionTestPeriod);
		ds.setLoginTimeout(configService.dbConnectionTimeOut);
		ds.setAcquireRetryAttempts(configService.dbRetryAttempts);
		ds.setCheckoutTimeout(configService.dbPoolCheckoutTimeout);
		ds.setUnreturnedConnectionTimeout(configService.unreturnedConnectionTimeout);
		ds.setDebugUnreturnedConnectionStackTraces(configService.debugUnreturnedConnectionStackTraces);
		return ds;
	}
	
	private Properties hibernateProperties() {
	    Properties properties = new Properties();
	    hibernateProperties.getProperties().forEach(properties::setProperty);
	    return properties;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	private String decode(String value) {
		return URLDecoder.decode(value);
	}

}