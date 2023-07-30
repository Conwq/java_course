package by.htp.ex.controller.listeners;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public final class CreatingInactiveUserToTheSession implements HttpSessionListener {
	private static final String JSP_USER_PARAM = "user";
	private static final String JSP_NOT_ACTIVE_PARAM = "not active";

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setAttribute(JSP_USER_PARAM, JSP_NOT_ACTIVE_PARAM);
	}
}
