package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_tmpl_category")
public class M_work_tmpl_categoryDAO extends Model<M_work_tmpl_categoryDAO>{
	public static M_work_tmpl_categoryDAO dao = new M_work_tmpl_categoryDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_tmpl_categoryDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_tmpl_categoryDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getContent(){
		return (String)get("content");
}
	public M_work_tmpl_categoryDAO setContent(String v){
		set("content",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_tmpl_categoryDAO setSts(String v){
		set("sts",v);
		return this;
}
	public Integer getOrd_(){
		return (Integer)get("ord_");
}
	public M_work_tmpl_categoryDAO setOrd_(Integer v){
		set("ord_",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_tmpl_categoryDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_tmpl_categoryDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_tmpl_categoryDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_tmpl_categoryDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getParent_id(){
		return (String)get("parent_id");
}
	public M_work_tmpl_categoryDAO setParent_id(String v){
		set("parent_id",v);
		return this;
}}
