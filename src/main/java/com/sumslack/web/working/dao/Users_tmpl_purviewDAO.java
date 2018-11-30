package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("users_tmpl_purview")
public class Users_tmpl_purviewDAO extends Model<Users_tmpl_purviewDAO>{
	public static Users_tmpl_purviewDAO dao = new Users_tmpl_purviewDAO();

	public String getId(){
		return (String)get("id");
}
	public Users_tmpl_purviewDAO setId(String v){
		set("id",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public Users_tmpl_purviewDAO setUid(String v){
		set("uid",v);
		return this;
}
	public String getTmpl_id(){
		return (String)get("tmpl_id");
}
	public Users_tmpl_purviewDAO setTmpl_id(String v){
		set("tmpl_id",v);
		return this;
}
	public String getIsedit(){
		return (String)get("isedit");
}
	public Users_tmpl_purviewDAO setIsedit(String v){
		set("isedit",v);
		return this;
}
	public String getIsview(){
		return (String)get("isview");
}
	public Users_tmpl_purviewDAO setIsview(String v){
		set("isview",v);
		return this;
}
	public String getIsremove(){
		return (String)get("isremove");
}
	public Users_tmpl_purviewDAO setIsremove(String v){
		set("isremove",v);
		return this;
}
	public String getIsowner(){
		return (String)get("isowner");
}
	public Users_tmpl_purviewDAO setIsowner(String v){
		set("isowner",v);
		return this;
}
	
}
