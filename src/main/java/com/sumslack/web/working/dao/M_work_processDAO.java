package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_process")
public class M_work_processDAO extends Model<M_work_processDAO>{
	public static M_work_processDAO dao = new M_work_processDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_processDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_processDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getContent(){
		return (String)get("content");
}
	public M_work_processDAO setContent(String v){
		set("content",v);
		return this;
}
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_processDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_processDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_processDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_processDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public Integer getOrd_(){
		return (Integer)get("ord_");
}
	public M_work_processDAO setOrd_(Integer v){
		set("ord_",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_processDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getTmpl_id(){
		return (String)get("tmpl_id");
}
	public M_work_processDAO setTmpl_id(String v){
		set("tmpl_id",v);
		return this;
}

	
	public String getCompany_id(){
		return (String)get("company_id");
}
	public M_work_processDAO setCompany_id(String v){
		set("company_id",v);
		return this;
}
	
	public Integer getAudit_level(){
		return (Integer)get("audit_level");
}
	public M_work_processDAO setAudit_level(Integer v){
		set("audit_level",v);
		return this;
}
	
}
