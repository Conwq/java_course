package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.util.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class NewsDAO implements INewsDAO {
	private final static Converter converter = Converter.getInstance();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private final static String SQL_TO_GET_LAST_NEWSES = "SELECT * FROM news ORDER BY news_date DESC LIMIT ?";
	@Override
	public List<News> getLatestList(int count, Locale locale) throws DaoException {
		List<News> news = new ArrayList<>();

		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_GET_LAST_NEWSES)){

			preparedStatement.setInt(1, count);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				News findNews = converter.convertNewsDependingOnDate(resultSet, locale);
				news.add(findNews);
			}
		}
		catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
		return news;
	}

	private final static String SQL_TO_GET_ALL_NEWSES = "SELECT * FROM news ORDER BY news_date DESC";
	@Override
	public List<News> getList(Locale locale) throws DaoException {
		List<News> news = new ArrayList<>();

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_TO_GET_ALL_NEWSES);
			ResultSet resultSet = statement.executeQuery()){

			while(resultSet.next()){
				News findNews = converter.convertNewsDependingOnDate(resultSet, locale);
				news.add(findNews);
			}
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
		return news;
	}

	private final static String SQL_TO_GET_NEWS = "SELECT * FROM news WHERE news_id = ?";
	@Override
	public News fetchById(int id, Locale locale) throws DaoException {
		News findNews = null;

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_TO_GET_NEWS)){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				findNews = converter.convertNews(resultSet, locale);
			}
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
		return findNews;
	}

	private final static String SQL_TO_ADD_NEWS = "INSERT INTO news (title, brief_news, content, photo_path, users_id, news_date) VALUES (?,?,?,?,?, NOW())";
	@Override
	public void addNews(News news) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_TO_ADD_NEWS)){

			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getPhotoPath());
			statement.setInt(5, news.getNewUserInfo().getUserId());
			statement.executeUpdate();
		}
		catch(SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_TO_UPDATE_NEWS = "UPDATE news SET title=?, brief_news=?, content=?, photo_path=? WHERE news_id=?";
	@Override
	public void updateNews(News news) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_TO_UPDATE_NEWS)){

			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getPhotoPath());
			statement.setInt(5, news.getIdNews());
			statement.executeUpdate();
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_TO_DELETE_NEWSES = "DELETE FROM news WHERE news_id IN (%s)";
	@Override
	public void deleteNewses(int[] idNewses) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(String.format(SQL_TO_DELETE_NEWSES, buildSQLQuery(idNewses)))){

			for (int i = 0; i < idNewses.length; i++) {
				statement.setInt(i + 1, idNewses[i]);
			}
			statement.executeUpdate();
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
	}

	private String buildSQLQuery(int[] idNewses){
		StringBuilder builderSqlQuery = new StringBuilder();

		for (int i = 0; i < idNewses.length; i++) {
			builderSqlQuery.append("?");
			if (i < idNewses.length -1){
				builderSqlQuery.append(",");
			}
		}
		return builderSqlQuery.toString();
	}

	private final static String SQL_TO_DELETE_NEWS = "DELETE FROM news WHERE news_id = ?";
	@Override
	public void deleteNews(int id) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_TO_DELETE_NEWS)){

			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
	}
}
