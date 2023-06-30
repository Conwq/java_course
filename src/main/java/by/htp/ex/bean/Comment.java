package by.htp.ex.bean;

import java.util.Date;

public final class Comment {
	private int commentId;
	private NewUserInfo user;
	private News news;
	private Date date;
	private String text;
	
	public Comment() {
		
	}

	public Comment(NewUserInfo user, News news, Date date, String text) {
		this.user = user;
		this.news = news;
		this.date = date;
		this.text = text;
	}
	
	public int getCommentId() {
		return commentId;
	}
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public NewUserInfo getNewUserInfo() {
		return user;
	}
	
	public void setNewUserInfo(NewUserInfo user) {
		this.user = user;
	}
	
	public News getNews() {
		return news;
	}
	
	public void setNews(News news) {
		this.news = news;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
