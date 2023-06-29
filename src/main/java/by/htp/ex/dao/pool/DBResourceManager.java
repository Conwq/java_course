package by.htp.ex.dao.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBResourceManager {
		private final static DBResourceManager instance = new DBResourceManager();
		Properties properties;
		private DBResourceManager(){
			properties = new Properties();
			try {
				InputStream file = new FileInputStream("D:\\Soft\\Project\\java_course\\src\\main\\java\\by\\htp\\ex\\dao\\pool\\database.properties");
				properties.load(file);
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		public static DBResourceManager getInstance() {
			return instance;
		}

	public String getValue(String key){
			return properties.getProperty(key);
	}
}

