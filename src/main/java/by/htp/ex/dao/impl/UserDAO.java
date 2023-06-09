package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.util.NewsManagerHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class UserDAO implements IUserDAO {
	private final static NewsManagerHelper helper = NewsManagerHelper.getInstance();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static String ru_PARAM = "ru";
	private final static String RU_PARAM = "RU";
	private final static String en_PARAM = "en";
	private final static String US_PARAM = "US";
	private final static String USER_INFO_RU_PARAM = "user_info_ru";
	private final static String USER_INFO_EN_PARAM = "user_info_en";

	@Override
	public void registration(NewUserInfo user) throws DaoException {
	}
	
	private final static String SQL_TO_ADD_USER = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
	private final static String SQL_ADD_LOCALE = "INSERT INTO locales (users_id, language, country) VALUES (?,?,?)";
	private final static String SQL_ADD_USER_INFO_BY_LOCALE = "INSERT INTO %s (users_id, name, surname, city_of_residence) VALUES (?,?,?,?)";
	@Override
	public void registrationByLocale(NewUserInfo user, String locale) throws DaoException{
		Connection connection = null;
		PreparedStatement preparedStatementAddUser = null;
		PreparedStatement preparedStatementAddLocal = null;
		PreparedStatement preparedStatementAddUserInfo = null;

		try {
			connection = connectionPool.takeConnection();
			
			if(isExistUser(user, connection)) {
				throw new DaoException("User with this email or login exists"); 
			}
			
			String language = locale.equals(ru_PARAM) ? ru_PARAM : en_PARAM;
			String country = locale.equals(ru_PARAM) ? RU_PARAM : US_PARAM;
			String table = locale.equals(ru_PARAM) ? USER_INFO_RU_PARAM : USER_INFO_EN_PARAM;
			
			connection.setAutoCommit(false);
				
			preparedStatementAddUser = connection.prepareStatement(SQL_TO_ADD_USER, Statement.RETURN_GENERATED_KEYS);
			preparedStatementAddLocal = connection.prepareStatement(SQL_ADD_LOCALE);
			preparedStatementAddUserInfo = connection.prepareStatement(String.format(SQL_ADD_USER_INFO_BY_LOCALE, table));
			
			preparedStatementAddUser.setString(1, user.getLogin());
			preparedStatementAddUser.setString(2, user.getPassword());
			preparedStatementAddUser.setString(3, user.getEmail());
			preparedStatementAddUser.executeUpdate();
			ResultSet resultSet = preparedStatementAddUser.getGeneratedKeys();
			resultSet.next();
			int userId = resultSet.getInt(1);

			preparedStatementAddLocal.setInt(1, userId);
			preparedStatementAddLocal.setString(2, language);
			preparedStatementAddLocal.setString(3, country);
			preparedStatementAddLocal.executeUpdate();

			preparedStatementAddUserInfo.setInt(1, userId);
			preparedStatementAddUserInfo.setString(2, user.getName());
			preparedStatementAddUserInfo.setString(3, user.getSurname());
			preparedStatementAddUserInfo.setString(4, user.getCityOfResidence());
			preparedStatementAddUserInfo.executeUpdate();

			connection.commit();
			connection.setAutoCommit(true);
		}
		catch(ConnectionPoolException | SQLException e){
			if(connection != null) {
				try {
					connection.rollback();
					if(!connection.getAutoCommit()) {
						connection.setAutoCommit(true);
					}
				}
				catch(SQLException ex) {
					throw new DaoException(ex);
				}
			}
			throw new DaoException(e);
		}
		finally {
			try {
				connectionPool.closeConnection(connection, preparedStatementAddUser);
				connectionPool.closeConnection(preparedStatementAddLocal);
				connectionPool.closeConnection(preparedStatementAddUserInfo);
			}
			catch(ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}
	}
	
	private final static String SQL_TO_AUTH_USER = "SELECT * FROM users LEFT JOIN locales ON users.id = locales.users_id WHERE login = ?";
	@Override
	public NewUserInfo authorization(String login, String password) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_AUTH_USER)) {

			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				throw new DaoException("User not found with this login");
			}
			
			return helper.getUserForAuthorization(resultSet, password);
		}
		catch (SQLException e) {
			throw new DaoException("Error with SQL", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("Error with ConnectionPool", e);
		}
	}

	private final static String SQL_TO_GET_ALL_USERS =  "SELECT * FROM users";
	@Override
	public List<NewUserInfo> getUsers() throws DaoException {
		List<NewUserInfo> usersInfo = new ArrayList<>();

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_GET_ALL_USERS);
			 ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				usersInfo.add(helper.parseUserInfo(resultSet));
			}
		}
		catch (SQLException e) {
			throw new DaoException("SQLException", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("ConnectionPoolException", e);
		}
		return usersInfo;
	}

	private final static String SQL_TO_GET_USER = "SELECT * FROM users WHERE id = ?";
	@Override
	public NewUserInfo getUser(int id) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_GET_USER)) {

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				throw new DaoException("User with current data not exist");
			}

			return helper.parseUserInfo(resultSet);
		}
		catch (SQLException e) {
			throw new DaoException("SQLException", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("ConnectionPoolException", e);
		}
	}

	private final static String SQL_TO_UPDATE_USER = "UPDATE users SET login=?, password=?, email=? WHERE id=?";
	@Override
	public void updateUserInfo(NewUserInfo userInfo) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_UPDATE_USER)) {

			if (isExistUser(userInfo, connection)){
				throw new DaoException("User with current email or login exist");
			}

			preparedStatement.setString(1, userInfo.getLogin());
			preparedStatement.setString(2, userInfo.getPassword());
			preparedStatement.setString(3, userInfo.getEmail());
			preparedStatement.setInt(4, userInfo.getUserId());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException("SQLException", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("ConnectionPoolException", e);
		}
	}

	private final static String SQL_UNBAN_USER = "UPDATE users SET banned = 0 WHERE id = ?";
	@Override
	public void unbanUser(int id) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UNBAN_USER)){
			NewUserInfo user = getUser(id);

			if (!user.isBanned()){
				throw new DaoException("User can not banned");
			}
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_BAN_USER = "UPDATE users SET banned = 1 WHERE id = ?";
	@Override
	public void banUser(int id) throws DaoException{
		
		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_BAN_USER)){
			 preparedStatement.setInt(1, id);
			 preparedStatement.executeUpdate();
		}
		catch(SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	private final static String SQL_DOWNGRADE_ROLE_USER = "UPDATE users SET role = 'user' WHERE id = ?";
	@Override
	public void downgradeRoleToUser(int id) throws DaoException {
		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_DOWNGRADE_ROLE_USER)){
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch(SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	private final static String SQL_TO_CHECK_USER_EXIST = "SELECT * FROM users WHERE email = ? OR login = ? AND id <> ?";
	private boolean isExistUser(NewUserInfo user, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_CHECK_USER_EXIST);

		preparedStatement.setString(1, user.getEmail());
		preparedStatement.setString(2, user.getLogin());
		preparedStatement.setInt(3, user.getUserId());
		ResultSet resultSet = preparedStatement.executeQuery();

		return resultSet.next();
	}
}
