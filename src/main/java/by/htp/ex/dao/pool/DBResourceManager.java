package by.htp.ex.dao.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	private static Properties properties;

	private DBResourceManager() {
		properties = new Properties();
		try {
			String filePath = getClass().getClassLoader().getResource("database.properties").getFile();
			InputStream file = new FileInputStream(filePath);
			properties.load(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
