package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_signin")
public class M_work_signinDAO extends Model<M_work_signinDAO>{
	public static M_work_signinDAO dao = new M_work_signinDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_signinDAO setId(String v){
		set("id",v);
		return this;
}
	public String getCompany_id(){
		return (String)get("company_id");
}
	public M_work_signinDAO setCompany_id(String v){
		set("company_id",v);
		return this;
}
	public java.util.Date getWorkon_time(){
		return (java.util.Date)get("workon_time");
}
	public M_work_signinDAO setWorkon_time(java.util.Date v){
		set("workon_time",v);
		return this;
}
	public java.util.Date getWorkoff_time(){
		return (java.util.Date)get("workoff_time");
}
	public M_work_signinDAO setWorkoff_time(java.util.Date v){
		set("workoff_time",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_signinDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_signinDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_signinDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_signinDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getWork_week(){
		return (String)get("work_week");
}
	public M_work_signinDAO setWork_week(String v){
		set("work_week",v);
		return this;
}
	public java.math.BigDecimal getLet(){
		return (java.math.BigDecimal)get("let");
}
	public M_work_signinDAO setLet(java.math.BigDecimal v){
		set("let",v);
		return this;
}
	public java.math.BigDecimal getLot(){
		return (java.math.BigDecimal)get("lot");
}
	public M_work_signinDAO setLot(java.math.BigDecimal v){
		set("lot",v);
		return this;
}
	public java.math.BigDecimal getError_scope(){
		return (java.math.BigDecimal)get("error_scope");
}
	public M_work_signinDAO setError_scope(java.math.BigDecimal v){
		set("error_scope",v);
		return this;
}
	public java.util.Date getStart_date(){
		return (java.util.Date)get("start_date");
}
	public M_work_signinDAO setStart_date(java.util.Date v){
		set("start_date",v);
		return this;
}
	public java.util.Date getEnd_date(){
		return (java.util.Date)get("end_date");
}
	public M_work_signinDAO setEnd_date(java.util.Date v){
		set("end_date",v);
		return this;
}}
