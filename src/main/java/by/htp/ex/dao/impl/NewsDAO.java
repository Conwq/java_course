package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class NewsDAO implements INewsDAO {

	static{
		try{
			Class.forName(ConstantsName.DB_DRIVER);
		}
		catch(ClassNotFoundException e){
			System.out.println("Class not found");
		}
	}


	/////////////////////////////////////////////////////
	////Добавить само создание новости в класс News??///
	////////////////////////////////////////////////////
	//TODO Этот метод пересмотреть, он должен возвращать последние 5 новостей. Сделать что нибудь с count

	private final static String SQL_QUERY_GET_LATEST_NEWS = "SELECT * FROM news ORDER BY news_date DESC LIMIT ?";
	@Override
	public List<News> getLatestList(int count) throws DaoException {
		List<News> news = new ArrayList<>();

		try(Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(SQL_QUERY_GET_LATEST_NEWS)){
			statement.setInt(1, count);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()){
				News findNews = getNewsFromResultSet(resultSet);
				news.add(findNews);
			}

			return news;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_GET_NEWS_LIST = "SELECT * FROM news ORDER BY news_date DESC";
	@Override
	public List<News> getList() throws DaoException {
		List<News> news = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL_QUERY_GET_NEWS_LIST)){
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){
				News findNews = getNewsFromResultSet(resultSet);
				news.add(findNews);
			}

			return news;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_GET_USER_BY_ID = "SELECT * FROM news WHERE news_id = ?";
	@Override
	public News fetchById(int id) throws DaoException {

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL_QUERY_GET_USER_BY_ID)){
			News findNews = null;
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				findNews = getNewsFromResultSet(resultSet);
			}

			return findNews;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	//Время задается автоматически, прописано в конфиге БД
	private final static String SQL_QUERY_ADD_NEWS = "INSERT INTO news (title, brief_news, content, photo_path, users_id, news_date) VALUES (?,?,?,?,?, NOW())";
	@Override
	public void addNews(News news) throws DaoException {

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL_QUERY_ADD_NEWS)){
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getPhotoPath());
			statement.setInt(5, news.getNewUserInfo().getUserId());
			statement.executeUpdate();
		}
		catch(SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_UPDATE_NEWS = "UPDATE news SET title=?, brief_news=?, content=?, news_date=?, photo_path=? WHERE news_id=?";
	@Override
	public void updateNews(News news) throws DaoException {

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL_QUERY_UPDATE_NEWS)){
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getNewsDate());
			statement.setString(5, news.getPhotoPath());
			statement.setInt(6, news.getIdNews());
			statement.executeUpdate();
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_DELETE_NEWSES = "DELETE FROM news WHERE news_id IN (?)";
	@Override
	public void deleteNewses(String[] idNewses) throws DaoException {

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL_QUERY_DELETE_NEWSES)){

			statement.setArray(1, con.createArrayOf("INTEGER", idNewses));
			statement.executeUpdate();
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_DELETE_NEWS ="DELETE FROM news WHERE news_id = ?";
	@Override
	public void deleteNews(int id) throws DaoException {

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL_QUERY_DELETE_NEWS)){
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	private News getNewsFromResultSet(ResultSet resultSet) throws SQLException{
		News findNews = new News();
		findNews.setIdNews(resultSet.getInt("news_id"));
		findNews.setTitle(resultSet.getString("title"));
		findNews.setBriefNews(resultSet.getString("brief_news"));
		findNews.setContent(resultSet.getString("content"));
		findNews.setNewsDate(resultSet.getString("news_date"));
		findNews.setPhotoPath(resultSet.getString("photo_path"));

		return findNews;
	}
}
