package by.htp.ex.util;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.News;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.exception.DaoException;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public final class NewsManagerHelper {
	private final static NewsManagerHelper instance = new NewsManagerHelper();
	private final String RESULT_SET_NEWS_ID_PARAM = "news_id";

	private NewsManagerHelper(){
	}

	public static NewsManagerHelper getInstance(){
		return instance;
	}

	public News parseNews(ResultSet resultSet, Locale locale) throws SQLException{
		News findNews = new News();
		findNews.setIdNews(resultSet.getInt("news_id"));
		findNews.setTitle(resultSet.getString("title"));
		findNews.setBriefNews(resultSet.getString("brief_news"));
		findNews.setContent(resultSet.getString("content"));
		findNews.setPhotoPath(resultSet.getString("photo_path"));
		findNews.setNewsDate(definingDateOutputFormat(resultSet.getString("news_date"), locale));

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

	public NewUserInfo getUserForAuthorization(ResultSet resultSet, String password) throws SQLException, DaoException{

		if(BCrypt.checkpw(password, resultSet.getString("password"))) {
			if (resultSet.getBoolean("banned")) {
				throw new DaoException("Current user was banned");
			}
			NewUserInfo newUserInfo = parseUserInfo(resultSet);
			newUserInfo.setLocale(getLocale(resultSet.getString("language"), resultSet.getString("country")));
			return newUserInfo;
		}
		else {
			throw new DaoException("Incorrect password");
		}
	}

	private String definingDateOutputFormat(String date, Locale locale) {

		if (locale == null){
			locale = Locale.getDefault();
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTimeNews = LocalDateTime.parse(date, formatter);

		LocalDate currentDate = LocalDate.now();
		LocalDate datePublicationNews = dateTimeNews.toLocalDate();

		String formatDateTime = dateTimeNews.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(locale));
		String formatDate = dateTimeNews.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale));

		if (datePublicationNews.equals(currentDate)) {
			return formatDateTime;
		}
		return formatDate;
	}

	public String definingDateOutputFormatForComments(String date) {
		Locale locale = Locale.getDefault();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTimeNews = LocalDateTime.parse(date, formatter);

		return dateTimeNews.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(locale));
	}

	public String buildSQLQuery(int[] idNewses){
		StringBuilder builderSqlQuery = new StringBuilder();

		for (int i = 0; i < idNewses.length; i++) {
			builderSqlQuery.append("?");
			if (i < idNewses.length -1){
				builderSqlQuery.append(",");
			}
		}
		return builderSqlQuery.toString();
	}

	public String getRefererPath(String referer) throws URISyntaxException {
		String path = "index.jsp";

		if (referer != null){
			URI uri = new URI(referer);
			path = uri.getPath();
			String query = uri.getQuery();

			if (query != null){
				path += "?" + query;
			}
		}
		return path;
	}

	public Locale getLocale (String localeParam){
		Locale locale;

		if (localeParam == null || localeParam.equals("en_US")){
			locale = new Locale("en", "US");
			return locale;
		}

		String[] resultLocale = localeParam.split("_");
		String language = resultLocale[0];
		String country = resultLocale[1];
		locale = new Locale(language, country);
		return locale;
	}

	public Locale getLocale (String language, String country){
		if (language == null || country == null){
			return Locale.getDefault();
		}

		return new Locale(language, country);
	}
}
