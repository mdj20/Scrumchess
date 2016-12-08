package com.scrumchess.data;
import java.util.Date;
import com.google.appengine.api.datastore.*;

public class User {
	private String name;
	private String id;
	private Date joined;
	private Date lastLogin;

	protected User(String id){
		this.id = id;
		Date date = new Date();
		this.name = "";
		this.joined = date;
		this.lastLogin = date;
	}
	
	protected User(String id, String name, Date joined, Date lastLogin){
		this.id = id;
		this.name = name;
		this.joined = joined;
		this.lastLogin = lastLogin;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	protected void setId(String id) {
		this.id = id;
	}
	protected void setJoined(Date joined) {
		this.joined = joined;
	}
	protected void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public Date getJoined() {
		return joined;
	}
	public Date getLastLogin() {
		return lastLogin;
	}

}
