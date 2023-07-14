package by.htp.ex.service;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface IUserService {
	NewUserInfo signIn(String login, String password) throws ServiceException;
	void registration(NewUserInfo user) throws ServiceException;
	void registration(NewUserInfo user, Locale locale) throws ServiceException;
	List<NewUserInfo> getUsers() throws ServiceException;
	NewUserInfo getUser(int id) throws ServiceException;
	void updateUserInfo(NewUserInfo userInfo) throws ServiceException;
	void unbanUser(int id) throws ServiceException;
	void banUser (int id) throws ServiceException;
	void downgradeRoleToUser(int id) throws ServiceException;
	void addCookieForUser(int userId, String cookieValue) throws ServiceException;
	NewUserInfo signInWithCookie (String cookieValue) throws ServiceException;
	void deleteCookie(String cookieValue) throws ServiceException;
}
