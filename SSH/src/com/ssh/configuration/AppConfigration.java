package com.ssh.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@ComponentScan({ "com.ssh.dao", "com.ssh.bean", "com.ssh.service" })
@Import({ DatabaseConfigration.class, DatabaseDataInitializer.class })
public class AppConfigration {

	private Logger log = Logger.getLogger(AppConfigration.class);

	public AppConfigration() {
		log.info("[Initializing Spring Root...]");
	}
}
