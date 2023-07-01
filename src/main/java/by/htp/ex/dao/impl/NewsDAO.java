package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.util.ConstantsName;
import by.htp.ex.util.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class NewsDAO implements INewsDAO {
	private final static DatabaseHelper helper = DatabaseHelper.getInstance();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	//TODO Этот метод пересмотреть, он должен возвращать последние 5 новостей. Сделать что нибудь с count

	@Override
	public List<News> getLatestList(int count) throws DaoException {
		List<News> news = null;

		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY news_date DESC LIMIT ?")){

			preparedStatement.setInt(1, count);
			ResultSet resultSet = preparedStatement.executeQuery();
			news = new ArrayList<>();

			while (resultSet.next()){
				News findNews = helper.parseNews(resultSet);
				news.add(findNews);
			}
		}
		catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
		return news;
	}

	@Override
	public List<News> getList() throws DaoException {
		List<News> news = new ArrayList<>();

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM news ORDER BY news_date DESC");
			ResultSet resultSet = statement.executeQuery()){

			while(resultSet.next()){
				News findNews = helper.parseNews(resultSet);
				news.add(findNews);
			}
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
		return news;
	}

	@Override
	public News fetchById(int id) throws DaoException {
		News findNews = null;

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM news WHERE news_id = ?")){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				findNews = helper.parseNews(resultSet);
			}
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
		return findNews;
	}

	//Время задается автоматически, прописано в конфиге БД
	@Override
	public void addNews(News news) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO news (title, brief_news, content, photo_path, users_id, news_date) VALUES (?,?,?,?,?, NOW())")){

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

	@Override
	public void updateNews(News news) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE news SET title=?, brief_news=?, content=?, news_date=?, photo_path=? WHERE news_id=?")){

			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getNewsDate());
			statement.setString(5, news.getPhotoPath());
			statement.setInt(6, news.getIdNews());

			statement.executeUpdate();
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
	}

	//TODO при удалении новостей, мы будем менять статус колонки в таблице, которая будет очищаться через месяц, и вот именно по этой колонке и будет проходить удаление
	@Override
	public void deleteNewses(String[] idNewses) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM news WHERE news_id IN (?)")){

			statement.setArray(1, connection.createArrayOf("INTEGER", idNewses));
			statement.executeUpdate();
		}
		catch (SQLException |ConnectionPoolException e){
			throw new DaoException(e);
		}
	}

	//TODO при удалении новостей, мы будем менять статус колонки в таблице, которая будет очищаться через месяц, и вот именно по этой колонке и будет проходить удаление
	@Override
	public void deleteNews(int id) throws DaoException {

		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM news WHERE news_id = ?")){

			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
	}
}
