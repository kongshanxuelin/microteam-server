package com.sumslack.web.working.bean;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable{
	private String username;  //用户ID，小程序同步进来的是一个uuid
	private String uid;
	private String nick;
	
	private String teamId; //团队ID或公司ID
	private String teamName;//当前团队名
	
	private String session_key;
	private String avatar;
	private String token;
	
	private List teamList;
	
	
	private List<PurviewUserTmpl> tmplList;//当前用户可以访问的电子模板
	private List<PurviewUserData> userDataList;//当前用户可以访问的其他用户模板数据的权限

	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List getTeamList() {
		return teamList;
	}

	public void setTeamList(List teamList) {
		this.teamList = teamList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public List<PurviewUserTmpl> getTmplList() {
		return tmplList;
	}

	public void setTmplList(List<PurviewUserTmpl> tmplList) {
		this.tmplList = tmplList;
	}

	public List<PurviewUserData> getUserDataList() {
		return userDataList;
	}

	public void setUserDataList(List<PurviewUserData> userDataList) {
		this.userDataList = userDataList;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
