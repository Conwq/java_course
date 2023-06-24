package by.htp.ex.service;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;

public interface IUserService {
	NewUserInfo signIn(String login, String password) throws ServiceException;
	void registration(NewUserInfo user) throws ServiceException;
	List<NewUserInfo> getUsers() throws ServiceException;
	NewUserInfo getUser(int id) throws ServiceException;
	void updateUserInfo(NewUserInfo userInfo) throws ServiceException;
}
