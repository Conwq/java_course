package by.htp.ex.dao;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.exception.DaoException;

import java.util.List;

public interface IUserDAO {
	void registration(NewUserInfo user) throws DaoException;
	void registration(NewUserInfo user, String locale) throws DaoException;
	NewUserInfo signIn(String login, String password) throws DaoException;
	List<NewUserInfo> getUsers() throws DaoException;
	NewUserInfo getUser(int id) throws DaoException;
	void updateUserInfo(NewUserInfo userInfo) throws DaoException;
	void unbanUser(int id) throws DaoException;
	void banUser(int id) throws DaoException;
	void downgradeRoleToUser(int id) throws DaoException;
	void addCookieForUser(int userId, String authorizationToken) throws DaoException;
	NewUserInfo signInByToken(String authorizationToken) throws DaoException;
	void deleteCookie(String authorizationToken) throws DaoException;
}
