package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_act_users")
public class M_act_usersDAO extends Model<M_act_usersDAO>{
	public static M_act_usersDAO dao = new M_act_usersDAO();

	public String getId(){
		return (String)get("id");
}
	public M_act_usersDAO setId(String v){
		set("id",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public M_act_usersDAO setUid(String v){
		set("uid",v);
		return this;
}
	public String getAct_id(){
		return (String)get("act_id");
}
	public M_act_usersDAO setAct_id(String v){
		set("act_id",v);
		return this;
}
	public java.util.Date getStart_date(){
		return (java.util.Date)get("start_date");
}
	public M_act_usersDAO setStart_date(java.util.Date v){
		set("start_date",v);
		return this;
}
	public java.util.Date getEnd_date(){
		return (java.util.Date)get("end_date");
}
	public M_act_usersDAO setEnd_date(java.util.Date v){
		set("end_date",v);
		return this;
}
	public java.sql.Timestamp getModifytime(){
		return (java.sql.Timestamp)get("modifytime");
}
	public M_act_usersDAO setModifytime(java.sql.Timestamp v){
		set("modifytime",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_act_usersDAO setSts(String v){
		set("sts",v);
		return this;
}
	
	public String getForm_id(){
		return (String)get("form_id");
}
	public M_act_usersDAO setForm_id(String v){
		set("form_id",v);
		return this;
}
}
