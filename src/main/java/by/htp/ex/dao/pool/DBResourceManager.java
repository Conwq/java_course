package by.htp.ex.dao.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;


public final class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	private final ResourceBundle resourceBundle;

	private DBResourceManager() {
		resourceBundle = ResourceBundle.getBundle("database");
	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
}
