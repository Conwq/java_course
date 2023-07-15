package by.htp.ex.controller.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;


public final class ErrorFilter extends HttpFilter {
	private static final String COMMAND_PARAM = "command";
	private static String errorPattern;
	private static String commandPattern;

	@Override
	public void init(FilterConfig config) throws ServletException {
		commandPattern = config.getInitParameter("commandPattern") == null ? ".+_e.*" : config.getInitParameter("commandPattern");
		errorPattern = config.getInitParameter("errorPattern") == null ? "error_.+" : config.getInitParameter("errorPattern");
	}

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req,res);

		String command = req.getParameter(COMMAND_PARAM);
		if (command != null && command.matches(commandPattern)){
			Enumeration<String> attributes = req.getSession().getAttributeNames();
			String error = null;
			while(attributes.hasMoreElements()){
				String attribute = attributes.nextElement();
				if (attribute.matches(errorPattern)){
					error = attribute;
				}
			}
			req.getSession().removeAttribute(error);
		}
	}
}
