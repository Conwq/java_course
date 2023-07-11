package by.htp.ex.controller;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class FrontController extends HttpServlet {
	private final static long serialVersionUID = 1L;
	private final static CommandProvider provider = CommandProvider.getInstance();
	private final static String COMMAND = "command";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getServletContext().getAttribute("ConnectionPoolInitError") != null){
			response.sendRedirect("/error/error.jsp");
			return;
		}
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
