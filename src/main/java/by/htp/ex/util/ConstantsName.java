package by.htp.ex.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConstantsName {

<<<<<<< HEAD
	///////////////////////////
	//Configuration DATABASE//
	/////////////////////////
//	public static final String DB_URL = properties.getProperty("db.url");
//	public static final String DB_USERNAME = properties.getProperty("db.username");
//	public static final String DB_PASSWORD = properties.getProperty("db.password");
//	public static final String DB_DRIVER = properties.getProperty("db.driver");

	public static final String DB_URL = "jdbc:mysql://localhost:3306/course";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "1";
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
=======
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
>>>>>>> 919920c756abc7c1d7136076a909183def9aec5a

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
