package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class NewsDAO implements INewsDAO {
//	{
//		result.add(new News(++ID, "Мыл голову бруском и сортировал мусор.",
//				"Наш журналист неделю жил по экоправилам и теперь делится опытом.",
//				"Этой весной совместно с Breezy мы выпустили текст о белорусах, которые сортируют мусор, " +
//						"отказываются от полиэтиленовых пакетов, компостируют органику и в целом стараются жить в гармонии с природой. " +
//						"Но одно дело — пересказывать чью-то историю, другое — попробовать все самому. " +
//						"Наш журналист Тарас Щирый открыл для себя принципы zero waste (ноль отходов) и полторы недели разделял дома мусор, " +
//						"пил кофе из силиконового стакана в заведениях, ходил за овощами исключительно с авоськой и затестил соленую зубную пасту.",
//				"26.05.2023",
//				"images/Head.png"));
//
//		result.add(new News(++ID,
//				"Эксперимент",
//				"Что случится, если остаться на Минском море с палаткой на ночь?",
//				"Через этот спор точно проходит любая компания друзей. " +
//						"Одна часть каждые теплые выходные предлагает отправиться в поход с палатками, " +
//						"другая — упорно из года в год от этого отказывается. Потому что зачем спать на земле, " +
//						"если придумали дома? А как мыться? А комары? А холод? А вдруг дикие звери? Если вы во второй категории, " +
//						"то точно придумаете еще с десяток причин, почему это четкое нет. Но если ты журналист, эти доводы перестают действовать. " +
//						"Мы решили поставить эксперимент и отправить в поход того, кто до этого видел палатку только на рынке.",
//				"28.05.2023",
//				"images/Palatka.png"));
//
//		result.add(new News(++ID,
//				"Как в сериале",
//				"Пары вернулись в отношения спустя много лет — и вот к чему это привело",
//				"Встретились, повстречались, разошлись. " +
//						"Снова встретились, повстречались, разошлись. " +
//						"Такие истории нам хорошо знакомы по сериалам. " +
//						"Но реальная жизнь придумывает сюжеты и покруче. " +
//						"Вместе с мы собрали истории пар, которым понадобилась не одна попытка, чтобы понять: они должны быть вместе. " +
//						"Сценаристам бы точно понравилось! " +
//						"Будьте осторожны: после прочтения может возникнуть желание искать по соцсетям свою первую любовь.",
//				"29.05.2023",
//				"images/Serial.png"));
//
//		result.add(new News(++ID,
//				"Экстремальные развлечения в Беларуси",
//				"Как пощекотать себе нервы и сколько это будет стоить?",
//				"Острота ощущений в глазах смотрящего: самым суровым экстремалам уровень драйва может показаться умеренным, " +
//						"а вот на всех остальных любое развлечение из списка может произвести впечатление на всю жизнь. " +
//						"Тем более выбрать есть из чего: стандартные занятия из будней супергероев — и прыгать, и летать, " +
//						"и гонять на необычном транспорте.",
//				"24.05.2023",
//				"images/Playing.png"));
//
//		result.add(new News(++ID,
//				"Есть ли движ в Дроздах и на Минском море?",
//				"Посмотрели, что происходит на популярных пляжах",
//				"Жара не за горами, и минчане все чаще массово — с шашлыками или без " +
//						"— выбираются отдохнуть к популярным водоемам. Мы тоже не устояли перед теплой погодой " +
//						"и в пятницу вырвались в Дрозды и на Минское море посмотреть, работает ли общепит и кто уже открыл купальный сезон. " +
//						"А заодно бесплатно послушали концерт на природе, увидели русалку (но это не точно) и, кажется, " +
//						"нашли любопытную альтернативу шашлычному беспределу.",
//				"21.05.2023",
//				"images/Drozd.png"));
//
//		result.add(new News(++ID,
//				"6",
//				"6",
//				"6",
//				"26.05.2023"));
//
//		result.add(new News(++ID,
//				"7",
//				"7",
//				"7",
//				"26.05.2023"));
//
//		result.add(new News(++ID,
//				"8",
//				"8",
//				"8",
//				"26.05.2023"));
//	}

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
		String SQL = "SELECT * FROM news WHERE date ";
		List<News> news = new ArrayList<>();

		try(Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(SQL)){
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()){
				News findNews = new News();
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
		String SQL = "SELECT * FROM news";
		List<News> news = new ArrayList<>();

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL)){
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){
				News findNews = new News();
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
		News news = null;

		try(Connection con = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			PreparedStatement statement = con.prepareStatement(SQL)){
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()){
				news = new News();
				news.setTitle(resultSet.getString("title"));
				news.setBriefNews(resultSet.getString("brief_news"));
				news.setContent(resultSet.getString("content"));
				news.setNewsDate(resultSet.getString("news_date"));
				news.setPhotoPath(resultSet.getString("photo_path"));
			}
			return news;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	@Override
	public void addNews(News news) throws DaoException {
		String SQL = "INSERT INTO news (title, brief_news, content, news_date, photo_path) VALUES (?,?,?,?,?)";


	}

	@Override
	public void updateNews(News news) throws DaoException {
		String SQL = "UPDATE news SET title=?, brief_news=?, content=?, news_date=?, photo_path=? WHERE news_id=?";
	}

	@Override
	public void deleteNewses(String[] idNewses) throws DaoException {
		String SQL = "DELETE FROM news WHERE news_id = ?";
	}
}
