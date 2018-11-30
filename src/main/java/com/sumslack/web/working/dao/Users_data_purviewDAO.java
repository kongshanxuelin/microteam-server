package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("users_data_purview")
public class Users_data_purviewDAO extends Model<Users_data_purviewDAO>{
	public static Users_data_purviewDAO dao = new Users_data_purviewDAO();

	public String getId(){
		return (String)get("id");
}
	public Users_data_purviewDAO setId(String v){
		set("id",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public Users_data_purviewDAO setUid(String v){
		set("uid",v);
		return this;
}
	public String getTouid(){
		return (String)get("touid");
}
	public Users_data_purviewDAO setTouid(String v){
		set("touid",v);
		return this;
}
	public String getIsview(){
		return (String)get("isview");
}
	public Users_data_purviewDAO setIsview(String v){
		set("isview",v);
		return this;
}
	public String getIsedit(){
		return (String)get("isedit");
}
	public Users_data_purviewDAO setIsedit(String v){
		set("isedit",v);
		return this;
}
	public String getIsremove(){
		return (String)get("isremove");
}
	public Users_data_purviewDAO setIsremove(String v){
		set("isremove",v);
		return this;
}
	public String getTmplid(){
		return (String)get("tmplid");
}
	public Users_data_purviewDAO setTmplid(String v){
		set("tmplid",v);
		return this;
}	
}
