package com.sumslack.web.working.bean.request;

import java.io.Serializable;

public class DynaPubRequest implements Serializable{
	private String token;
	private String content;
	private String addr;
	private String scope;
	private String fileIds;
	private String teamid;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	
	
	
}
