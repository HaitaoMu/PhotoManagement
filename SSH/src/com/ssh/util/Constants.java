package com.ssh.util;

public final class Constants {

	public static final String EXPORT_RDMSQL_ENABLED = "export.sql";
	public static final String EXPORT_RDMSQL_PATH = "export.sql.path";

	public static final String EXPORT_PATH = "export.path";
	public static final String UPLOAD_PATH = "upload.path";

	public static final String LOG_PATH = "log.configuration.path";
	
	public static final String RDM_JDBC_URL = "ssh.jdbc.url";
	public static final String RDM_JDBC_DRIVER = "ssh.jdbc.driver";
	public static final String RDM_JDBC_USERNAME = "ssh.jdbc.userName";
	public static final String RDM_JDBC_PASSWORD = "ssh.jdbc.password";

	public static final String RDM_DEFAULT_SCHEMA = "hibernate.default_schema";
	public static final String RDM_HBM2DLL_ENABLED = "hibernate.hbm2ddl.auto";

	public static final String RDM_JDBC_DIALECT = "ssh.hibernate.dialect";
	public static final String RDM_JDBC_SHOW_SQL = "ssh.hibernate.show_sql";

	public static final String HIBERNATE_BATCHSIZE = "hibernate.jdbc.batch_size";
	public static final String HIBERNATE_CACHE_FACTORY_CLASS = "hibernate.cache.region.factory_class";
	public static final String HIBERNATE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
	public static final String HIBERNATE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
	public static final String HIBERNATE_CACHE_RESOURCE_NAME = "org.hibernate.cache.ehcache.configurationResourceName";

	public static final String HIBERNATE_TRANSACTION_FACTORY = "hibernate.transaction.factory_class";
	public static final String HIBERNATE_MIN_POOLSIZE = "hibernate.c3p0.minPoolSize";
	public static final String HIBERNATE_MAX_POOLSIZE = "hibernate.c3p0.maxPoolSize";
	public static final String HIBERNATE_TIMEOUT = "hibernate.c3p0.timeout";
	public static final String HIBERNATE_MAX_STATEMENT = "hibernate.c3p0.max_statement";
	public static final String HIBERNATE_TEST_CONNECTION_ON_CHECKOUT = "hibernate.c3p0.testConnectionOnCheckout";

	public static final String EMAIL_SMTP = "sys.email.stmp";
	public static final String EMAIL_ACCOUNT = "sys.email.account";
	public static final String EMAIL_PASSWORD = "sys.email.passwd";
	public static final String EMAIL_CORRECT_SUBJECT = "sys.email.correct.subject";
	public static final String EMAIL_WRONG_SUBJECT = "sys.email.wrong.subject";
	public static final String EMAIL_ALL_SUBJECT = "sys.email.all.subject";

	public static final String QUARTZ_EXPORT = "org.quartz.scheduler.rmi.export";
	public static final String QUARTZ_PROXY = "org.quartz.scheduler.rmi.proxy";
	public static final String QUARTZ_CLASS = "org.quartz.threadPool.class";
	public static final String QUARTZ_THREAD_COUNT = "org.quartz.threadPool.threadCount";
	public static final String QUARTZ_THREAD_PRIORITY = "org.quartz.threadPool.threadPriority";
	public static final String QUARTZ_THREAD_LOADER = "org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread";
	public static final String QUARTZ_USE_PROPERTIES = "org.quartz.jobStore.useProperties";
	public static final String QUARTZ_JOBSTORE_CLASS = "org.quartz.jobStore.class";
	public static final String QUARTZ_JOBSTORE_PREFIX = "org.quartz.jobStore.tablePrefix";
	public static final String QUARTZ_JOBSTORE_DELEGATE = "org.quartz.jobStore.driverDelegateClass";

	public static final String QUARTZ_JOBSTORE_DATASOURCE = "org.quartz.jobStore.datasource";
}
