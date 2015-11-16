package com.ssh.configuration;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.ssh.util.Constants;

@Configuration
@PropertySources({
		@PropertySource(value = { "classpath:conf/configuration.properties" }),
		@PropertySource(value = { "file:${ssh.home}/ssh.properties" }) })
@ComponentScan(value = { "com.ssh.dao", "com.ssh.service", "com.ssh.entity" })
public class DatabaseConfigration implements TransactionManagementConfigurer {

	private Logger log = Logger.getLogger(DatabaseConfigration.class);

	@Resource
	Environment env;

	@Resource
	private PlatformTransactionManager transactionManager;

	public DatabaseConfigration() {
		log.info("[Initializing Database Configration...]");
	}

//	@Bean
//	public EhCacheCacheManager ehCacheCacheManager() {
//		return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
//	}
//
//	@Bean
//	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource(
//				"conf/ehcache.xml"));
//		cacheManagerFactoryBean.setShared(true);
//		return cacheManagerFactoryBean;
//	}

	@Bean(name = "localDatasource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env
				.getProperty(Constants.RDM_JDBC_DRIVER));
		dataSource.setUrl(env.getProperty(Constants.RDM_JDBC_URL));
		if (StringUtils
				.isNotEmpty(env.getProperty(Constants.RDM_JDBC_USERNAME))) {
			dataSource
					.setUsername(env.getProperty(Constants.RDM_JDBC_USERNAME));
		} else {
			// Set username from DB or Servie
			dataSource.setUsername("");
		}
		if (StringUtils
				.isNotEmpty(env.getProperty(Constants.RDM_JDBC_PASSWORD))) {
			dataSource
					.setPassword(env.getProperty(Constants.RDM_JDBC_PASSWORD));

		} else {
			// Set password from DB or Servie
			dataSource.setPassword("");
		}

		return dataSource;
	}

	/**
	 * 
	 * Hibernate Configuartion SessionFactory TransactionManager
	 * 
	 */

	@Bean(name = "hibernateTemplate")
	public HibernateTemplate hibernateTemplate() {
		HibernateTemplate template = new HibernateTemplate(sessionFactory()
				.getObject());
		return template;
	}

	@Primary
	@Bean(name = "transactionManager")
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		txManager.setDataSource(dataSource());
		return txManager;
	}

	@Bean
	public SchemaExport schemaGenerator() {
		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(
				dataSource());
		sessionFactoryBuilder.scanPackages(new String[] { "com.ssh.entity" });
		sessionFactoryBuilder.addProperties(hibernateProperties());
		SchemaExport schema = new SchemaExport(sessionFactoryBuilder);
		return schema;
	}

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.ssh.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@SuppressWarnings("serial")
	public Properties hibernateProperties() {
		return new Properties() {
			{

				setProperty("hibernate.hbm2ddl.auto",
						env.getProperty(Constants.RDM_HBM2DLL_ENABLED));
				setProperty("hibernate.show_sql",
						env.getProperty(Constants.RDM_JDBC_SHOW_SQL));
				setProperty("hibernate.dialect",
						env.getProperty(Constants.RDM_JDBC_DIALECT));
				if (StringUtils.isNotEmpty(env
						.getProperty(Constants.RDM_JDBC_USERNAME))) {
					setProperty("hibernate.connection.username",
							env.getProperty(Constants.RDM_JDBC_USERNAME));
				} else {
					// Set username from DB or Servie
					setProperty("hibernate.connection.username", "");
				}

				if (StringUtils.isNotEmpty(env
						.getProperty(Constants.RDM_JDBC_PASSWORD))) {
					setProperty("hibernate.connection.password",
							env.getProperty(Constants.RDM_JDBC_PASSWORD));
				} else {
					// Set password from DB or Servie
					setProperty("hibernate.connection.password", "");
				}

//				setProperty(
//						"hibernate.cache.region.factory_class",
//						env.getProperty(Constants.HIBERNATE_CACHE_FACTORY_CLASS));
//				setProperty(
//						"hibernate.cache.use_second_level_cache",
//						env.getProperty(Constants.HIBERNATE_USE_SECOND_LEVEL_CACHE));
//				setProperty("hibernate.cache.use_query_cache",
//						env.getProperty(Constants.HIBERNATE_USE_QUERY_CACHE));
//				setProperty(
//						"org.hibernate.cache.ehcache.configurationResourceName",
//						env.getProperty(Constants.HIBERNATE_CACHE_RESOURCE_NAME));

				setProperty(
						"hibernate.transaction.factory_class",
						env.getProperty(Constants.HIBERNATE_TRANSACTION_FACTORY));
				setProperty("hibernate.jdbc.batch_size",
						env.getProperty(Constants.HIBERNATE_BATCHSIZE));

				setProperty("hibernate.c3p0.minPoolSize",
						env.getProperty(Constants.HIBERNATE_MIN_POOLSIZE));
				setProperty("hibernate.c3p0.maxPoolSize",
						env.getProperty(Constants.HIBERNATE_MAX_POOLSIZE));
				setProperty("hibernate.c3p0.timeout",
						env.getProperty(Constants.HIBERNATE_TIMEOUT));
				setProperty("hibernate.c3p0.max_statement",
						env.getProperty(Constants.HIBERNATE_MAX_STATEMENT));
				setProperty(
						"hibernate.c3p0.testConnectionOnCheckout",
						env.getProperty(Constants.HIBERNATE_TEST_CONNECTION_ON_CHECKOUT));
			}
		};
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return this.transactionManager;
	}

}
