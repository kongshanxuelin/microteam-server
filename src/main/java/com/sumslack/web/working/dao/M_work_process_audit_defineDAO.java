package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_process_audit_define")
public class M_work_process_audit_defineDAO extends Model<M_work_process_audit_defineDAO>{
	public static M_work_process_audit_defineDAO dao = new M_work_process_audit_defineDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_process_audit_defineDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_process_audit_defineDAO setTitle(String v){
		set("title",v);
		return this;
}
	
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_process_audit_defineDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public Integer getAudit_level(){
		return (Integer)get("audit_level");
}
	public M_work_process_audit_defineDAO setAudit_level(Integer v){
		set("audit_level",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_process_audit_defineDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_process_audit_defineDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_process_audit_defineDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_process_audit_defineDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getAudit_level_rel(){
		return (String)get("audit_level_rel");
}
	public M_work_process_audit_defineDAO setAudit_level_rel(String v){
		set("audit_level_rel",v);
		return this;
}
	public String getProcess_id(){
		return (String)get("process_id");
}
	public M_work_process_audit_defineDAO setProcess_id(String v){
		set("process_id",v);
		return this;
}	
}
