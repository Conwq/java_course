package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.exception.DaoException;

public interface IUserDAO {
	void registration(NewUserInfo user) throws DaoException;
	void registrationByLocale(NewUserInfo user, String locale) throws DaoException;
	NewUserInfo authorization(String login, String password) throws DaoException;
	List<NewUserInfo> getUsers() throws DaoException;
	NewUserInfo getUser(int id) throws DaoException;
	void updateUserInfo(NewUserInfo userInfo) throws DaoException;
	void unbanUser(int id) throws DaoException;
	void banUser(int id) throws DaoException;
	void downgradeRoleToUser(int id) throws DaoException;
	void addCookieForUser(int userId, String cookieValue) throws DaoException;
	NewUserInfo signInWithCookie (String cookieValue) throws DaoException;
	void deleteCookie(String cookieValue) throws DaoException;
}
