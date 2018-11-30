package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_process_instance")
public class M_work_process_instanceDAO extends Model<M_work_process_instanceDAO>{
	public static M_work_process_instanceDAO dao = new M_work_process_instanceDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_process_instanceDAO setId(String v){
		set("id",v);
		return this;
}
	public String getProcess_define_id(){
		return (String)get("process_define_id");
}
	public M_work_process_instanceDAO setProcess_define_id(String v){
		set("process_define_id",v);
		return this;
}
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_process_instanceDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_process_instanceDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_process_instanceDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_process_instanceDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_process_instanceDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_process_instanceDAO setSts(String v){
		set("sts",v);
		return this;
}
	
	public String getTmpl_id_id(){
		return (String)get("tmpl_id_id");
}
	public M_work_process_instanceDAO setTmpl_id_id(String v){
		set("tmpl_id_id",v);
		return this;
}
	
	
	public Integer getAudit_level(){
		return (Integer)get("audit_level");
}
	public M_work_process_instanceDAO setAudit_level(Integer v){
		set("audit_level",v);
		return this;
}
	public String getIsfinish(){
		return (String)get("isfinish");
}
	public M_work_process_instanceDAO setIsfinish(String v){
		set("isfinish",v);
		return this;
}	
}
