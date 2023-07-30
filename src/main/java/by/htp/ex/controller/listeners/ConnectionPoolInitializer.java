package by.htp.ex.controller.listeners;

import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.exception.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public final class ConnectionPoolInitializer implements ServletContextListener {
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
		try {
			connectionPool.dispose();
		}
		catch (ConnectionPoolException e){
			throw new RuntimeException(e);
		}
	}
}
