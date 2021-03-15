package com.revature.models;

public class LoginTemplate {
	
	private String username;
	private String password;
	
	public LoginTemplate() {
		super();
	}

	public LoginTemplate(String un, String pass) {
		super();
		this.username = un;
		this.password = pass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String un) {
		this.username = un;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginTemplate other = (LoginTemplate) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginTemplate [username=" + username + ", password=" + password + "]";
	}
	
	
	

}
