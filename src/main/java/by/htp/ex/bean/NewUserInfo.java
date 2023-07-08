package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public final class NewUserInfo implements Serializable {
	private int userId;
	private String login;
	private String email;
	private String password;
	private Role role;
	private boolean banned;

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

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NewUserInfo that = (NewUserInfo) o;
		return banned == that.banned && Objects.equals(login, that.login) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && role == that.role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, email, password, role, banned);
	}
}

