package by.htp.ex.dao.pool;

import java.util.ResourceBundle;


public final class DBResourceManager {
	private static final DBResourceManager instance = new DBResourceManager();
	private static final String DATABASE_CONFIG = "database";
	private final ResourceBundle resourceBundle;

	private DBResourceManager() {
		resourceBundle = ResourceBundle.getBundle(DATABASE_CONFIG);
	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
}
