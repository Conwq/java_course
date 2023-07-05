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
	private final static IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private final static ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance().getReentrantLock();

	@Override
	public void unbanUser(int id) throws ServiceException {
		try {
			userDAO.unbanUser(id);
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}
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
	
	@Override
	public void banUser(int id) throws ServiceException{
		try {
			userDAO.banUser(id);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void downgradeRoleToUser(int id) throws ServiceException {
		try {
			userDAO.downgradeRoleToUser(id);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
