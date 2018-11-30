package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_project_tasks_comments")
public class M_work_project_tasks_commentsDAO extends Model<M_work_project_tasks_commentsDAO>{
	public static M_work_project_tasks_commentsDAO dao = new M_work_project_tasks_commentsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_project_tasks_commentsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTask_id(){
		return (String)get("task_id");
}
	public M_work_project_tasks_commentsDAO setTask_id(String v){
		set("task_id",v);
		return this;
}
	public String getContent(){
		return (String)get("content");
}
	public M_work_project_tasks_commentsDAO setContent(String v){
		set("content",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_project_tasks_commentsDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_project_tasks_commentsDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_project_tasks_commentsDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_project_tasks_commentsDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getStype(){
		return (String)get("stype");
}
	public M_work_project_tasks_commentsDAO setStype(String v){
		set("stype",v);
		return this;
}}
