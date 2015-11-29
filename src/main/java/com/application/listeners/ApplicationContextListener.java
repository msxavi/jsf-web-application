package com.application.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author Emerson
 *
 */
public class ApplicationContextListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextListener.class);


	/**
	 * 
	 */
	public void contextInitialized(ServletContextEvent event) {
		LOG.debug("Initializing {}...", this.getClass().getSimpleName());
		executeContextInitialized(event);
		LOG.debug("{} initialized with success!", this.getClass().getSimpleName());
	}

	/**
	 * 
	 */
	public void contextDestroyed(ServletContextEvent event) {
		LOG.debug("Finishing {}...", this.getClass().getSimpleName());
		executeContextDestroyed(event);
		LOG.debug("Context Destroyed: {}.", this.getClass().getSimpleName());
	}

	private void executeContextInitialized(ServletContextEvent event) {
		// TODO IMPLEMENTATION HERE	
	}

	private void executeContextDestroyed(ServletContextEvent event) {
		// TODO IMPLEMENTATION HERE		
	}

}
