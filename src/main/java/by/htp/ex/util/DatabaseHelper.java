package by.htp.ex.util;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.News;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.exception.DaoException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class DatabaseHelper {
	private final static DatabaseHelper instance = new DatabaseHelper();
	private DatabaseHelper(){
	}

	public static DatabaseHelper getInstance(){
		return instance;
	}

	public News parseNews(ResultSet resultSet) throws SQLException{
		News findNews = new News();
		findNews.setIdNews(resultSet.getInt("news_id"));
		findNews.setTitle(resultSet.getString("title"));
		findNews.setBriefNews(resultSet.getString("brief_news"));
		findNews.setContent(resultSet.getString("content"));
		findNews.setNewsDate(resultSet.getString("news_date"));
		findNews.setPhotoPath(resultSet.getString("photo_path"));

		return findNews;
	}

	public NewUserInfo parseUserInfo(ResultSet resultSet) throws SQLException{
		NewUserInfo newUserInfo = new NewUserInfo();
		newUserInfo.setUserId(resultSet.getInt("id"));
		newUserInfo.setLogin(resultSet.getString("login"));
		newUserInfo.setEmail(resultSet.getString("email"));
		newUserInfo.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
		newUserInfo.setBanned(resultSet.getBoolean("banned"));

		return newUserInfo;
	}
	
	public NewUserInfo parseUserInfo(ResultSet resultSet, String password) throws SQLException, DaoException{

		if(BCrypt.checkpw(password, resultSet.getString("password"))) {
			if (resultSet.getBoolean("banned")) {
				throw new DaoException("Current user was banned");
			}
			return parseUserInfo(resultSet);
		}
		else {
			throw new DaoException("Incorrect password");
		}
	}
}
