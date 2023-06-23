package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;

public final class NewsServiceImpl implements INewsService{
	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	
	@Override
	public void save() {
	}

	@Override
	public void find() {
	}

	@Override
	public void update() {
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		try {
			return newsDAO.getLatestList(5);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
