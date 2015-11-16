package com.ssh.configuration;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.ssh.util.Constants;

@Configuration
public class DatabaseDataInitializer {

	private Logger log = Logger.getLogger(DatabaseDataInitializer.class);

	private final static String NONE = "none";

	private final static String UPDATE = "update";

	private final static String CREATE = "create";

	@Autowired
	Environment env;

	@Autowired
	@Qualifier("localDatasource")
	private DataSource dataSource;

	@Autowired
	private SchemaExport schema;

	@Value("classpath:conf/resource.sql")
	private Resource menuResourceScript;

	@Bean(name = "quartzInitialize")
	public DataSourceInitializer quartzInitialize() {
		exportSqlSchema();
		if (!NONE.equals(env.getProperty(Constants.RDM_HBM2DLL_ENABLED))
				&& !UPDATE.equals(env
						.getProperty(Constants.RDM_HBM2DLL_ENABLED))) {
			final DataSourceInitializer initializer = new DataSourceInitializer();
			initializer.setDataSource(dataSource);
			initializer.setDatabasePopulator(databasePopulator());
			return initializer;
		}
		return null;
	}

	private void exportSqlSchema() {
		if (log.isDebugEnabled()) {
			log.debug("export sql schema  operation");
		}
		if (StringUtils.isEmpty(env.getProperty(Constants.EXPORT_RDMSQL_PATH))) {
			log.error("Export schema path is empty");
			return;
		}
		File schemaFile = new File(
				env.getProperty(Constants.EXPORT_RDMSQL_PATH));
		if (!schemaFile.isDirectory() && !schemaFile.exists()) {
			try {
				String fileDirectory = schemaFile.getParent();
				File directory = new File(fileDirectory);
				directory.mkdir();
				schemaFile.createNewFile();
			} catch (IOException e) {
				log.error("create schema file failed" + e.getMessage());
			}
		}

		if (env.getProperty(Constants.RDM_HBM2DLL_ENABLED).equalsIgnoreCase(
				CREATE)) {
			schema.setOutputFile(env.getProperty(Constants.EXPORT_RDMSQL_PATH));
			schema.create(false, false);
		}
	}

	private DatabasePopulator databasePopulator() {
		final ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.setSqlScriptEncoding("UTF-8");
		resourceDatabasePopulator.addScript(menuResourceScript);
		resourceDatabasePopulator.setIgnoreFailedDrops(true);
		return resourceDatabasePopulator;
	}

	@PostConstruct
	public void PropertiesSetter() {
		log.info("[Set system environment]");
		// File Path
		System.setProperty(Constants.LOG_PATH,
				env.getProperty(Constants.LOG_PATH));
		System.setProperty(Constants.UPLOAD_PATH,
				env.getProperty(Constants.UPLOAD_PATH));

		// Email
		System.setProperty(Constants.EMAIL_SMTP,
				env.getProperty(Constants.EMAIL_SMTP));
		System.setProperty(Constants.EMAIL_PASSWORD,
				env.getProperty(Constants.EMAIL_PASSWORD));
		System.setProperty(Constants.EMAIL_ACCOUNT,
				env.getProperty(Constants.EMAIL_ACCOUNT));
		System.setProperty(Constants.EMAIL_ALL_SUBJECT,
				env.getProperty(Constants.EMAIL_ALL_SUBJECT));
	}
}
