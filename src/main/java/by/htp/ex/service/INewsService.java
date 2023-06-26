package by.htp.ex.service;

import by.htp.ex.bean.News;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;

public interface INewsService {
  void save(News news) throws ServiceException;
  void update(News news) throws ServiceException;
  List<News> latestList(int count)  throws ServiceException;
  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException;
  void delete(int id) throws ServiceException;
}
