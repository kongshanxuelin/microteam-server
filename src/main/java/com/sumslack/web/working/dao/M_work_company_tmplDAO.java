package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_company_tmpl")
public class M_work_company_tmplDAO extends Model<M_work_company_tmplDAO>{
	public static M_work_company_tmplDAO dao = new M_work_company_tmplDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_company_tmplDAO setId(String v){
		set("id",v);
		return this;
}
	public Integer getCompany_id(){
		return (Integer)get("company_id");
}
	public M_work_company_tmplDAO setCompany_id(Integer v){
		set("company_id",v);
		return this;
}
	public String getTmpl_id(){
		return (String)get("tmpl_id");
}
	public M_work_company_tmplDAO setTmpl_id(String v){
		set("tmpl_id",v);
		return this;
}
	public String getTmpl_cateid(){
		return (String)get("tmpl_cateid");
}
	public M_work_company_tmplDAO setTmpl_cateid(String v){
		set("tmpl_cateid",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_company_tmplDAO setSts(String v){
		set("sts",v);
		return this;
}
	public Integer getOrd_(){
		return (Integer)get("ord_");
}
	public M_work_company_tmplDAO setOrd_(Integer v){
		set("ord_",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_company_tmplDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_company_tmplDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_company_tmplDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_company_tmplDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}}
