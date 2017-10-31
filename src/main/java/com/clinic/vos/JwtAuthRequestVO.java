package com.clinic.vos;

import java.io.Serializable;

public class JwtAuthRequestVO implements Serializable {
	
	private static final long serialVersionUID = 9178177782693670099L;

	private String username;
    private String password;

    public JwtAuthRequestVO() {
        super();
    }

    public JwtAuthRequestVO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
