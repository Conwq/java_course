package by.htp.ex.service;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.service.exception.ServiceException;

public interface IUserService {
	String signIn(String login, String password) throws ServiceException;
	void registration(NewUserInfo user) throws ServiceException;
}
