package com.ssh.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import com.ssh.service.UploadServlet;

@SuppressWarnings("deprecation")
public class PojectInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		// Initial Spring Root Container
		initializeSpringConfig(container);

		// Initial Spring MVC With Restful Style
		initializeSpringMVCConfig(container);

		// Log4j Configuration
		initializeLog4jConfig(container);

		// Register Servlet
		registerServlet(container);

		// Register Filter
		registerFilter(container);

		// Register Listener
		registerListener(container);
	}

	private void initializeSpringConfig(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfigration.class);

		// Manage the life cycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));
	}

	private void initializeLog4jConfig(ServletContext container) {
		// Log4jConfigListener
		container.setInitParameter("log4jConfigLocation",
				"file:${ssh.home}/log4j.properties");
		container.addListener(Log4jConfigListener.class);
	}

	private void initializeSpringMVCConfig(ServletContext container) {
		// Create the spring rest servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(RestServiceConfigration.class);

		// Register and map the spring rest servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"SpringMVC", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.setAsyncSupported(true);
		dispatcher.addMapping("/rest/*");
	}

	private void registerServlet(ServletContext container) {
		initializeUploadServlet(container);
	}

	private void registerFilter(ServletContext container) {
		// Register Structs2 Filter
		initializeStructs2Filter(container);
	}

	private void initializeStructs2Filter(ServletContext container) {
		// Register and map the structs2 servlet
		FilterRegistration.Dynamic filterRegistration = container.addFilter(
				"Structs2", StrutsPrepareAndExecuteFilter.class);
		filterRegistration.addMappingForUrlPatterns(null, false, "/structs2/*");
		filterRegistration.setAsyncSupported(true);
	}

	private void initializeUploadServlet(ServletContext container) {
		ServletRegistration.Dynamic dynamic = container.addServlet(
				"uploadFileServlet", UploadServlet.class);
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("/upload");
	}

	private void registerListener(ServletContext container) {
		container.addListener(RequestContextListener.class);
		container.addListener(SessionTimeoutListener.class);
	}
}
