package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("teams")
public class TeamsDAO extends Model<TeamsDAO>{
	public static TeamsDAO dao = new TeamsDAO();

	public String getId(){
		return (String)get("id");
}
	public TeamsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getPid(){
		return (String)get("pid");
}
	public TeamsDAO setPid(String v){
		set("pid",v);
		return this;
}
	
	public String getTitle(){
		return (String)get("title");
}
	public TeamsDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getAvatar(){
		return (String)get("avatar");
}
	public TeamsDAO setAvatar(String v){
		set("avatar",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public TeamsDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public TeamsDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getDelflag(){
		return (String)get("delflag");
}
	public TeamsDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	
}
