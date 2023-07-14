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
	private final static String NEWS_ID_PARAM = "news_id";
	private final static String TITLE_PARAM = "title";
	private final static String BRIEF_NEWS_PARAM = "brief_news";
	private final static String CONTENT_PARAM = "content";
	private final static String PHOTO_PATH_PARAM = "photo_path";
	private final static String NEWS_DATE_PARAM = "news_date";
	private final static String ID_PARAM = "id";
	private final static String LOGIN_PARAM = "login";
	private final static String EMAIL_PARAM = "email";
	private final static String ROLE_PARAM = "role";
	private final static String BANNED_PARAM = "banned";
	private final static String PASSWORD_PARAM = "password";
	private final static String LANGUAGE_PARAM = "language";
	private final static String COUNTRY_PARAM = "country";
	private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private NewsManagerHelper(){
	}

	public static NewsManagerHelper getInstance(){
		return instance;
	}

	public News parseNews(ResultSet resultSet, Locale locale) throws SQLException{
		News findNews = new News();
		findNews.setIdNews(resultSet.getInt(NEWS_ID_PARAM));
		findNews.setTitle(resultSet.getString(TITLE_PARAM));
		findNews.setBriefNews(resultSet.getString(BRIEF_NEWS_PARAM));
		findNews.setContent(resultSet.getString(CONTENT_PARAM));
		findNews.setPhotoPath(resultSet.getString(PHOTO_PATH_PARAM));
		findNews.setNewsDate(definingDateOutputFormat(resultSet.getString(NEWS_DATE_PARAM), locale));

		return findNews;
	}

	public NewUserInfo getUserInfo(ResultSet resultSet) throws SQLException{
		NewUserInfo newUserInfo = new NewUserInfo();

		newUserInfo.setUserId(resultSet.getInt(ID_PARAM));
		newUserInfo.setLogin(resultSet.getString(LOGIN_PARAM));
		newUserInfo.setEmail(resultSet.getString(EMAIL_PARAM));
		newUserInfo.setRole(Role.valueOf(resultSet.getString(ROLE_PARAM).toUpperCase()));
		newUserInfo.setBanned(resultSet.getBoolean(BANNED_PARAM));

		return newUserInfo;
	}

	public NewUserInfo getUserForAuthorization(ResultSet resultSet, String password) throws SQLException, DaoException{


		if(BCrypt.checkpw(password, resultSet.getString(PASSWORD_PARAM))) {
			if (resultSet.getBoolean(BANNED_PARAM)) {
				throw new DaoException("Current user was banned");
			}
			NewUserInfo newUserInfo = getUserInfo(resultSet);
			newUserInfo.setLocale(getLocale(resultSet.getString(LANGUAGE_PARAM), resultSet.getString(COUNTRY_PARAM)));
			return newUserInfo;
		}
		else {
			throw new DaoException("Incorrect password");
		}
	}

	public NewUserInfo getUserForCookies(ResultSet resultSet) throws SQLException, DaoException{

		if (resultSet.getBoolean(BANNED_PARAM)) {
			throw new DaoException("Current user was banned");
		}
		NewUserInfo newUserInfo = getUserInfo(resultSet);
		newUserInfo.setLocale(getLocale(resultSet.getString(LANGUAGE_PARAM), resultSet.getString(COUNTRY_PARAM)));
		return newUserInfo;
	}

	private String definingDateOutputFormat(String date, Locale locale) {

		if (locale == null){
			locale = Locale.getDefault();
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
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
