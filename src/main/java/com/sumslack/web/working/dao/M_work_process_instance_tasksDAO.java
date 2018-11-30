package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_process_instance_tasks")
public class M_work_process_instance_tasksDAO extends Model<M_work_process_instance_tasksDAO>{
	public static M_work_process_instance_tasksDAO dao = new M_work_process_instance_tasksDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_process_instance_tasksDAO setId(String v){
		set("id",v);
		return this;
}
	public String getProcess_define_id(){
		return (String)get("process_define_id");
}
	public M_work_process_instance_tasksDAO setProcess_define_id(String v){
		set("process_define_id",v);
		return this;
}
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_process_instance_tasksDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_process_instance_tasksDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_process_instance_tasksDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_process_instance_tasksDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_process_instance_tasksDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getProcess_instance_id(){
		return (String)get("process_instance_id");
}
	public M_work_process_instance_tasksDAO setProcess_instance_id(String v){
		set("process_instance_id",v);
		return this;
}
	public String getAudit_uid(){
		return (String)get("audit_uid");
}
	public M_work_process_instance_tasksDAO setAudit_uid(String v){
		set("audit_uid",v);
		return this;
}
	public String getAudit_sts(){
		return (String)get("audit_sts");
}
	public M_work_process_instance_tasksDAO setAudit_sts(String v){
		set("audit_sts",v);
		return this;
}
	public String getAudit_memo(){
		return (String)get("audit_memo");
}
	public M_work_process_instance_tasksDAO setAudit_memo(String v){
		set("audit_memo",v);
		return this;
}

	
	public String getAudit_define_id(){
		return (String)get("audit_define_id");
}
	public M_work_process_instance_tasksDAO setAudit_define_id(String v){
		set("audit_define_id",v);
		return this;
}
	public Integer getAudit_level(){
		return (Integer)get("audit_level");
}
	public M_work_process_instance_tasksDAO setAudit_level(Integer v){
		set("audit_level",v);
		return this;
}
}
