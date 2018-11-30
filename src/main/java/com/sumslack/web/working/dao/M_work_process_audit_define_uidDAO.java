package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_process_audit_define_uid")
public class M_work_process_audit_define_uidDAO extends Model<M_work_process_audit_define_uidDAO>{
	public static M_work_process_audit_define_uidDAO dao = new M_work_process_audit_define_uidDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_process_audit_define_uidDAO setId(String v){
		set("id",v);
		return this;
}
	public String getAudit_id(){
		return (String)get("audit_id");
}
	public M_work_process_audit_define_uidDAO setAudit_id(String v){
		set("audit_id",v);
		return this;
}
	public String getCompany_id(){
		return (String)get("company_id");
}
	public M_work_process_audit_define_uidDAO setCompany_id(String v){
		set("company_id",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_process_audit_define_uidDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_process_audit_define_uidDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_process_audit_define_uidDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_process_audit_define_uidDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public String getAudit_uid(){
		return (String)get("audit_uid");
}
	public M_work_process_audit_define_uidDAO setAudit_uid(String v){
		set("audit_uid",v);
		return this;
}

	
}
