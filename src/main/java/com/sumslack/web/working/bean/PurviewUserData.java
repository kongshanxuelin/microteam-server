package com.sumslack.web.working.bean;

public class PurviewUserData {
	private String uid;
	private String tmplId;
	private boolean isCanView;
	private boolean isCanEdit;
	private boolean isCanRemove;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTmplId() {
		return tmplId;
	}
	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}
	public boolean isCanView() {
		return isCanView;
	}
	public void setCanView(boolean isCanView) {
		this.isCanView = isCanView;
	}
	public boolean isCanEdit() {
		return isCanEdit;
	}
	public void setCanEdit(boolean isCanEdit) {
		this.isCanEdit = isCanEdit;
	}
	public boolean isCanRemove() {
		return isCanRemove;
	}
	public void setCanRemove(boolean isCanRemove) {
		this.isCanRemove = isCanRemove;
	}
	
	
}
