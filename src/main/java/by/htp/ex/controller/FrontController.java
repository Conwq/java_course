package by.htp.ex.controller;

import by.htp.ex.controller.command.Command;
import by.htp.ex.controller.command.CommandName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final CommandProvider provider = CommandProvider.getInstance();
	private static final String COMMAND = "command";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> commandErrors = dataValidator(request);

		if (commandErrors.isEmpty()) {
			Command command = provider.getCommand(request.getParameter(COMMAND));
			command.execute(request, response);
		}
		else {
			request.setAttribute("CommandError", commandErrors);
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}

	public List<String> dataValidator(HttpServletRequest request){
		String command = request.getParameter(COMMAND);
		List<String> pathError = new ArrayList<>();
		try{
			CommandName.valueOf(command.toUpperCase());
		}
		catch (NullPointerException e){
			pathError.add("Command can`t be empty");
		}
		catch (IllegalArgumentException e){
			pathError.add("This path doesn't exist: \"localhost:8080/controller?command=" + command + "\"");
		}
		return pathError;
	}
}
