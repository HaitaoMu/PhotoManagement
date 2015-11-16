package com.ssh.configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionTimeoutListener implements HttpSessionListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SessionTimeoutListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		LOGGER.info("[Session created]");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LOGGER.info("[Session destroyed]");
		// HttpSession session = event.getSession();
		// session.removeAttribute("userProfileId");
	}

}
