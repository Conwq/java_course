package by.htp.ex.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public final class CookiesHelper {

	private static final CookiesHelper instance = new CookiesHelper();

	private CookiesHelper(){
	}

	public Cookie getCookie(HttpServletRequest request, String name){
		Cookie cookie =  null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c:cookies) {
				if(c.getName().equals(name)) {
					cookie = c;
				}
			}
		}
		return cookie;
	}

	public static CookiesHelper getInstance(){
		return instance;
	}
}
