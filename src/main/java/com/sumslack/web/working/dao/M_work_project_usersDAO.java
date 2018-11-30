package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_project_users")
public class M_work_project_usersDAO extends Model<M_work_project_usersDAO>{
	public static M_work_project_usersDAO dao = new M_work_project_usersDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_project_usersDAO setId(String v){
		set("id",v);
		return this;
}
	public String getProject_id(){
		return (String)get("project_id");
}
	public M_work_project_usersDAO setProject_id(String v){
		set("project_id",v);
		return this;
}
	public String getRole_id(){
		return (String)get("role_id");
}
	public M_work_project_usersDAO setRole_id(String v){
		set("role_id",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public M_work_project_usersDAO setUid(String v){
		set("uid",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_project_usersDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_project_usersDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_project_usersDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}}
