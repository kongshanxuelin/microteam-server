package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("ID")
@Table("m_work_anno")
public class M_work_annoDAO extends Model<M_work_annoDAO>{
	public static M_work_annoDAO dao = new M_work_annoDAO();

	public String getID(){
		return (String)get("ID");
}
	public M_work_annoDAO setID(String v){
		set("ID",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_annoDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getContent(){
		return (String)get("content");
}
	public M_work_annoDAO setContent(String v){
		set("content",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_annoDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_annoDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_annoDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_annoDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getCompany_id(){
		return (String)get("company_id");
}
	public M_work_annoDAO setCompany_id(String v){
		set("company_id",v);
		return this;
}}
