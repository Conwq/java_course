package by.htp.ex.bean;

import java.io.Serializable;

public final class NewUserInfo implements Serializable {
	private int userId;
	private String login;
	private String email;
	private String password;
	private Role role;


	//TODO ДОБАВИТЬ ЛИСТ ВСЕХ НОВОСТЕЙ, КОТОРЫЕ ПРИНАДЛЕЖАТ ПОЛЬЗОВАТЕЛЮ


	public NewUserInfo(){
	}

	public NewUserInfo(int id, String login, String email, String password){
		userId = id;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public NewUserInfo(String login, String email, String password){
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public NewUserInfo(String login, String email, String password, Role role) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return  getClass().getName() +
				" {login='" + login + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}
}

