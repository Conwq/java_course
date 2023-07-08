package by.htp.ex.dao;

import by.htp.ex.bean.News;
import by.htp.ex.dao.exception.DaoException;

import java.util.List;


public interface INewsDAO {
    List<News> getList() throws DaoException;

    List<News> getLatestList(int count) throws DaoException;

    News fetchById(int id) throws DaoException;

    void addNews(News news) throws DaoException;

    void updateNews(News news) throws DaoException;

    void deleteNewses(int[] idNewses) throws DaoException;
    void deleteNews(int id) throws DaoException;
}
