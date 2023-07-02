package by.htp.ex.bean;

import java.io.Serializable;

public final class News implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idNews;
	private String title;
	private String briefNews;
	private String content;
	private String newsDate;
	private String photoPath;
	private NewUserInfo newUserInfo;

	public News() {
	}

	public News(int idNews, String title, String briefNews, String content, String newsDate, String photoPath){
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
		this.photoPath = photoPath;
	}

	public News(String title, String briefNews, String content, String photoPath, NewUserInfo newUserInfo) {
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.photoPath = photoPath;
		this.newUserInfo = newUserInfo;
	}

	public News(int idNews, String title, String briefNews, String content, String photoPath) {
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.photoPath = photoPath;
	}

	public News(String title, String briefNews, String content, String photoPath) {
		this.photoPath = photoPath;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
	}

	public News(String title, String briefNews, String content) {
		this.photoPath = null;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
	}

	public NewUserInfo getNewUserInfo() {
		return newUserInfo;
	}

	public void setNewUserInfo(NewUserInfo newUserInfo) {
		this.newUserInfo = newUserInfo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}

	@Override
	public String toString() {
		return getClass().getName() +
				" {idNews=" + idNews +
				", title='" + title + '\'' +
				", briefNews='" + briefNews + '\'' +
				", content='" + content + '\'' +
				", newsDate='" + newsDate + '\'' +
				", photoPath='" + photoPath + '\'' +
				", newUserInfo=" + newUserInfo +
				'}';
	}
}
