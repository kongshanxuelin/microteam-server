package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_project_tasks")
public class M_work_project_tasksDAO extends Model<M_work_project_tasksDAO>{
	public static M_work_project_tasksDAO dao = new M_work_project_tasksDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_project_tasksDAO setId(String v){
		set("id",v);
		return this;
}
	
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_project_tasksDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	
	
	public String getProject_id(){
		return (String)get("project_id");
}
	public M_work_project_tasksDAO setProject_id(String v){
		set("project_id",v);
		return this;
}
	public String getTmpl_id(){
		return (String)get("tmpl_id");
}
	public M_work_project_tasksDAO setTmpl_id(String v){
		set("tmpl_id",v);
		return this;
}
	public String getTmpl_id_id(){
		return (String)get("tmpl_id_id");
}
	public M_work_project_tasksDAO setTmpl_id_id(String v){
		set("tmpl_id_id",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_project_tasksDAO setSts(String v){
		set("sts",v);
		return this;
}
	public Integer getOrd_(){
		return (Integer)get("ord_");
}
	public M_work_project_tasksDAO setOrd_(Integer v){
		set("ord_",v);
		return this;
}
	public String getContent(){
		return (String)get("content");
}
	public M_work_project_tasksDAO setContent(String v){
		set("content",v);
		return this;
}
	public String getAdmin_uid(){
		return (String)get("admin_uid");
}
	public M_work_project_tasksDAO setAdmin_uid(String v){
		set("admin_uid",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_project_tasksDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_project_tasksDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_project_tasksDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_project_tasksDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getTask_auditer(){
		return (String)get("task_auditer");
}
	public M_work_project_tasksDAO setTask_auditer(String v){
		set("task_auditer",v);
		return this;
}
	public java.util.Date getTime_start(){
		return (java.util.Date)get("time_start");
}
	public M_work_project_tasksDAO setTime_start(java.util.Date v){
		set("time_start",v);
		return this;
}
	public java.util.Date getTime_end(){
		return (java.util.Date)get("time_end");
}
	public M_work_project_tasksDAO setTime_end(java.util.Date v){
		set("time_end",v);
		return this;
}
	public String getTask_joiner(){
		return (String)get("task_joiner");
}
	public M_work_project_tasksDAO setTask_joiner(String v){
		set("task_joiner",v);
		return this;
}}
