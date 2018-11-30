package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_projects")
public class M_work_projectsDAO extends Model<M_work_projectsDAO>{
	public static M_work_projectsDAO dao = new M_work_projectsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_projectsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_projectsDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getContent(){
		return (String)get("content");
}
	public M_work_projectsDAO setContent(String v){
		set("content",v);
		return this;
}
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_projectsDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_projectsDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_projectsDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_projectsDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public Integer getOrd_(){
		return (Integer)get("ord_");
}
	public M_work_projectsDAO setOrd_(Integer v){
		set("ord_",v);
		return this;
}
	public String getAvator_url(){
		return (String)get("avator_url");
}
	public M_work_projectsDAO setAvator_url(String v){
		set("avator_url",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_projectsDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public java.util.Date getStart_time(){
		return (java.util.Date)get("start_time");
}
	public M_work_projectsDAO setStart_time(java.util.Date v){
		set("start_time",v);
		return this;
}
	public java.util.Date getEnd_time(){
		return (java.util.Date)get("end_time");
}
	public M_work_projectsDAO setEnd_time(java.util.Date v){
		set("end_time",v);
		return this;
}
	public String getOwner_uid(){
		return (String)get("owner_uid");
}
	public M_work_projectsDAO setOwner_uid(String v){
		set("owner_uid",v);
		return this;
}
	public String getCompany_id(){
		return (String)get("company_id");
}
	public M_work_projectsDAO setCompany_id(String v){
		set("company_id",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_projectsDAO setSts(String v){
		set("sts",v);
		return this;
}}
