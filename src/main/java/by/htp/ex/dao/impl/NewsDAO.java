package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;
import by.htp.ex.util.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class NewsDAO implements INewsDAO {
	private final static DatabaseHelper helper = DatabaseHelper.getInstance();
	static {
		try {
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

	@Override
	public List<News> getLatestList(int count) throws DaoException {
		List<News> news = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try{
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY news_date DESC LIMIT ?");
			preparedStatement.setInt(1, count);
			resultSet = preparedStatement.executeQuery();
			news = new ArrayList<>();

			while (resultSet.next()){
				News findNews = helper.parseNews(resultSet);
				news.add(findNews);
			}

			return news;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
		finally {
			helper.closeConnectionResources(connection, preparedStatement, resultSet);
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
				News findNews = helper.parseNews(resultSet);
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
				findNews = helper.parseNews(resultSet);
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
}
