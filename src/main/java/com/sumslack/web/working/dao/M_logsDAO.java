package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_logs")
public class M_logsDAO extends Model<M_logsDAO>{
	public static M_logsDAO dao = new M_logsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_logsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public M_logsDAO setUid(String v){
		set("uid",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_logsDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getParams(){
		return (String)get("params");
}
	public M_logsDAO setParams(String v){
		set("params",v);
		return this;
}
	public java.util.Date getDt(){
		return (java.util.Date)get("dt");
}
	public M_logsDAO setDt(java.util.Date v){
		set("dt",v);
		return this;
}
	public String getTeamid(){
		return (String)get("teamid");
}
	public M_logsDAO setTeamid(String v){
		set("teamid",v);
		return this;
}	
	public Integer getIstop(){
		return (Integer)get("istop");
	}
	
	public M_logsDAO setIstop(Integer v){
		set("istop",v);
		return this;
}
	
	public String getAddr(){
		return (String)get("addr");
}
	public M_logsDAO setAddr(String v){
		set("addr",v);
		return this;
}	
	
	public String getScope(){
		return (String)get("scope");
}
	public M_logsDAO setScope(String v){
		set("scope",v);
		return this;
}	
	
	
	public String getStype(){
		return (String)get("stype");
}
	public M_logsDAO setStype(String v){
		set("stype",v);
		return this;
}	
}
