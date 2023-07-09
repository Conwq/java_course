package by.htp.ex.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;

public final class EncodingFilter extends HttpFilter {

	private static String encoding;

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		if (encoding == null){
			encoding = "utf-8";
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		chain.doFilter(req, res);
	}
}
