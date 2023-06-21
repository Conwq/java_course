package by.htp.ex.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConstantsName {

	private static final Properties properties = new Properties();

	static{
		try{
			InputStream stream = Files.newInputStream(Path.of("classpath:db.properties"));
			properties.load(stream);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	///////////////////////////
	//Configuration DATABASE//
	/////////////////////////
	public static final String DB_URL = properties.getProperty("db.url");
	public static final String DB_USERNAME = properties.getProperty("db.username");
	public static final String DB_PASSWORD = properties.getProperty("db.password");
	public static final String DB_DRIVER = properties.getProperty("db.driver");

	///////////////////////////
	////////Constants/////////
	/////////////////////////
//	public static final String JSP_LOGIN_PARAM = "login";
//	public static final String JSP_PASSWORD_PARAM = "password";
//	public static final String JSP_USER_PARAM = "user";
//	public static final String JSP_USER_ACTIVE = "active";
//	public static final String JSP_USER_NOT_ACTIVE = "not active";
//	public static final String JSP_USER_ROLE = "role";
//	public static final String JSP_AUTHENTICATION_ERROR_PARAM = "AuthenticationError";
}
