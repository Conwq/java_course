package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.ReentrantLockSingleton;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public final class UserServiceImpl implements IUserService {
	private static final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private static final ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance().getReentrantLock();

	@Override
	public NewUserInfo signIn(String login, String password) throws ServiceException {

		try {
			return userDAO.signIn(login, password);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public NewUserInfo signInByToken(String authorizationToken) throws ServiceException {
		try {
			return userDAO.signInByToken(authorizationToken);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void unbanUser(String id) throws ServiceException {
		try {
			int convertUserId = Integer.parseInt(id);
			userDAO.unbanUser(convertUserId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("User with this id not found", e);
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
	public void registration(NewUserInfo user, Locale locale) throws ServiceException {
		try{
			reentrantLock.lock();
			userDAO.registration(user, locale.getLanguage());
		}
		catch (DaoException e){
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
	public NewUserInfo getUser(String id) throws ServiceException {
		try {
			int convertUserId = Integer.parseInt(id);
			return userDAO.getUser(convertUserId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("User with this id not found", e);
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
	public void banUser(String id) throws ServiceException{
		try {
			int convertUserId = Integer.parseInt(id);
			userDAO.banUser(convertUserId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch(NumberFormatException e){
			throw new ServiceException("User with this id not found", e);
		}
	}

	@Override
	public void downgradeRoleToUser(String id) throws ServiceException {
		try {
			int convertUserId = Integer.parseInt(id);
			userDAO.downgradeRoleToUser(convertUserId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("User with this id not found", e);
		}
	}

	@Override
	public void addTokenToSaveData(int userId, String authorizationToken) throws ServiceException {
		try {
			userDAO.addCookieForUser(userId, authorizationToken);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteCookie(String authorizationToken) throws ServiceException{
		try {
			userDAO.deleteCookie(authorizationToken);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
