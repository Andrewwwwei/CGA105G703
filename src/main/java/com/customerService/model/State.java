package com.customerService.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class State {
	private String type;
	// the user changing the state
	private String user;
	// total users
//	private Set<String> users;
	private List<Map<String, String>> userList;

	public State(String type, String user, List<Map<String, String>> userList) {
		super();
		this.type = type;
		this.user = user;
		this.userList = userList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Map<String, String>> getUserList() {
		return userList;
	}

	public void setUserList(List<Map<String, String>> userList) {
		this.userList = userList;
	}

	
}
