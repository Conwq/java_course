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
			System.out.println("Class not find");
		}
	}


	/////////////////////////////////////////////////////
	//TODO Все тут перепроверить согласно таблице в БД//
	////Убрать дублирование кода при создании новости///
	////////////////////////////////////////////////////





	//TODO Этот метод пересмотреть, он должен возвращать последние 5 новостей.
	// Наверное лучше сделать по дате добавления
	@Override
	public List<News> getLatestList(int count) throws DaoException {
		String SQL = "SELECT * FROM news ORDER BY news_date DESC LIMIT ?";
		List<News> news = new ArrayList<>();

		try(Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(SQL)){
			statement.setInt(1, count);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()){
				News findNews = new News();
				findNews.setIdNews(resultSet.getInt("news_id"));
				findNews.setTitle(resultSet.getString("title"));
				findNews.setBriefNews(resultSet.getString("brief_news"));
				findNews.setContent(resultSet.getString("content"));
				findNews.setNewsDate(resultSet.getString("news_date"));
				findNews.setPhotoPath(resultSet.getString("photo_path"));
				news.add(findNews);
			}
			return news;
		}
		catch (SQLException e){
			System.out.println("SQL exception");
			e.printStackTrace();
			throw new DaoException(e);
		}

	}

	@Override
	public List<News> getList() throws DaoException {
		String SQL = "SELECT * FROM news ORDER BY news_date DESC";
		List<News> news = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL)){
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){
				News findNews = new News();
				findNews.setIdNews(resultSet.getInt("news_id"));
				findNews.setTitle(resultSet.getString("title"));
				findNews.setBriefNews(resultSet.getString("brief_news"));
				findNews.setContent(resultSet.getString("content"));
				findNews.setNewsDate(resultSet.getString("news_date"));
				findNews.setPhotoPath(resultSet.getString("photo_path"));
				news.add(findNews);
			}

			return news;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	@Override
	public News fetchById(int id) throws DaoException {
		String SQL = "SELECT * FROM news WHERE news_id = ?";
		News findNews = null;

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL)){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()){
				findNews = new News();
				findNews.setIdNews(resultSet.getInt("news_id"));
				findNews.setTitle(resultSet.getString("title"));
				findNews.setBriefNews(resultSet.getString("brief_news"));
				findNews.setContent(resultSet.getString("content"));
				findNews.setNewsDate(resultSet.getString("news_date"));
				findNews.setPhotoPath(resultSet.getString("photo_path"));
			}
			return findNews;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	@Override
	public void addNews(News news) throws DaoException {
		String SQL = "INSERT INTO news (title, brief_news, content, news_date, photo_path) VALUES (?,?,?,NOW(),?)";

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL)){
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getBriefNews());
			statement.setString(3, news.getContent());
			statement.setString(4, news.getPhotoPath());
			statement.executeUpdate();
		}
		catch(SQLException e){
			throw new DaoException(e);
		}
	}

	@Override
	public void updateNews(News news) throws DaoException {
		String SQL = "UPDATE news SET title=?, brief_news=?, content=?, news_date=?, photo_path=? WHERE news_id=?";

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL)){
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

	@Override
	public void deleteNewses(String[] idNewses) throws DaoException {
		String SQL = "DELETE FROM news WHERE news_id IN (?)";

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL)){

			statement.setArray(1, con.createArrayOf("INTEGER", idNewses));
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}
}
