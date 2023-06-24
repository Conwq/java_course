package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;

public final class UserServiceImpl implements IUserService {
	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

	@Override
	public NewUserInfo signIn(String login, String password) throws ServiceException {

		try {
			return userDAO.authorization(login, password);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void registration(NewUserInfo user) throws ServiceException {

		try {
			if (userDAO.isExistUser(user))
				throw new ServiceException("User with this email exists");

			userDAO.registration(user);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<NewUserInfo> getUsers() throws ServiceException {
		try {
			return userDAO.getUsers();
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}

	@Override
	public NewUserInfo getUser(int id) throws ServiceException {
		try {
			return userDAO.getUser(id);
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateUserInfo(NewUserInfo userInfo) throws ServiceException {
		try{
			userDAO.updateUserInfo(userInfo);
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}
}
