package by.htp.ex.dao.pool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	private static Properties properties;

	private DBResourceManager() {
		properties = new Properties();
		try {
			String filePath = getClass().getClassLoader().getResource("database.properties").getFile();
			InputStream file = new FileInputStream(filePath);
//			InputStream file = new FileInputStream("D:\\Работа\\IntUlt\\work place\\java_course\\src\\main\\resources\\database.properties");
			properties.load(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
