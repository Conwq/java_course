package by.htp.ex.controller.listeners;

import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import javax.management.RuntimeMBeanException;

public final class ContextListener implements ServletContextListener {
	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			connectionPool.initConnectionPool();
		}
		catch(ConnectionPoolException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		connectionPool.dispose();
	}
}
