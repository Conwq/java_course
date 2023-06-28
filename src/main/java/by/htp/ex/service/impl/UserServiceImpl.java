package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.ReentrantLockSingleton;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public final class UserServiceImpl implements IUserService {
	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
<<<<<<< HEAD
	private final ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance().getReentrantLock();
=======
	private final ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance();
>>>>>>> 4673edc38e4c0758e6556f7badd7b7bb6611b3b4

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
			reentrantLock.lock();

			if (userDAO.isExistUser(user)) {
				throw new ServiceException("User with this email exists");
			}
			userDAO.registration(user);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
		finally {
			reentrantLock.unlock();
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
