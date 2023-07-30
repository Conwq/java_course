package by.htp.ex.bean;

public enum Role {
	ADMIN,
	USER,
	GUEST;

	public String getRoleName(){
		return name().toLowerCase();
	}
}
