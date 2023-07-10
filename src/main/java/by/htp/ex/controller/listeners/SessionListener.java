package by.htp.ex.controller.listeners;

import java.util.Locale;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public final class SessionListener implements HttpSessionListener {
	private final static String JSP_USER_PARAM = "user";
	private final static String JSP_NOT_ACTIVE_PARAM = "not active";
	private final static String JSP_LOCAL_PARAM = "local";
	private static Locale locale;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		if(event.getSession().getAttribute(JSP_LOCAL_PARAM) == null) {
			locale = Locale.getDefault();
		}
		event.getSession().setAttribute(JSP_USER_PARAM, JSP_NOT_ACTIVE_PARAM);
		event.getSession().setAttribute(JSP_LOCAL_PARAM, locale);
	}
}
