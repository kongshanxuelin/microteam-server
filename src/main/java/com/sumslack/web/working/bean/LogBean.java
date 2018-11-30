package com.sumslack.web.working.bean;

import java.io.Serializable;
import java.util.Date;

public class LogBean implements Serializable{
	private String id;
	private String uid;
	private String title;
	private String params;
	private Date dt;
	private String teamid;
	
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
	
}
