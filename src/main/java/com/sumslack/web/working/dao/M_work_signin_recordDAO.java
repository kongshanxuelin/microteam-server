package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_signin_record")
public class M_work_signin_recordDAO extends Model<M_work_signin_recordDAO>{
	public static M_work_signin_recordDAO dao = new M_work_signin_recordDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_signin_recordDAO setId(String v){
		set("id",v);
		return this;
}
	public String getCompany_id(){
		return (String)get("company_id");
}
	public M_work_signin_recordDAO setCompany_id(String v){
		set("company_id",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public M_work_signin_recordDAO setUid(String v){
		set("uid",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_signin_recordDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_signin_recordDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_signin_recordDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_signin_recordDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getDirect_(){
		return (String)get("direct_");
}
	public M_work_signin_recordDAO setDirect_(String v){
		set("direct_",v);
		return this;
}
	public String getIp(){
		return (String)get("ip");
}
	public M_work_signin_recordDAO setIp(String v){
		set("ip",v);
		return this;
}
	public String getInfo(){
		return (String)get("info");
}
	public M_work_signin_recordDAO setInfo(String v){
		set("info",v);
		return this;
}
	public String getSignin_settings_id(){
		return (String)get("signin_settings_id");
}
	public M_work_signin_recordDAO setSignin_settings_id(String v){
		set("signin_settings_id",v);
		return this;
}}
