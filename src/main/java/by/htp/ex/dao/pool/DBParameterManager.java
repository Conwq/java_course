package by.htp.ex.dao.pool;

import java.util.ResourceBundle;

public final class DBParameterManager {
	
	private static final DBParameterManager instance = new DBParameterManager();
	private final ResourceBundle bundle = ResourceBundle.getBundle("src.main.resources.db");

	private DBParameterManager() {
		
	}
	
	public static DBParameterManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
	
}
