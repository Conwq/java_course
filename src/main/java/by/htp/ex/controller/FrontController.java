package by.htp.ex.controller;

import by.htp.ex.controller.command.Command;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.dao.pool.DBResourceManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final CommandProvider provider = CommandProvider.getInstance();
	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final String COMMAND = "command";

	@Override
	public void init() throws ServletException {

		try {
			connectionPool.initConnectionPool();
		}
		catch(ConnectionPoolException e){
			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {
		connectionPool.dispose();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = provider.getCommand(request.getParameter(COMMAND));
		command.execute(request, response);
	}
}
