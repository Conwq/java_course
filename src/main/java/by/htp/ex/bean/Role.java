package by.htp.ex.bean;

public enum Role {
	ADMIN,
	USER,
	GUEST;

	public String getRole(){
		return name().toLowerCase();
	}
}
