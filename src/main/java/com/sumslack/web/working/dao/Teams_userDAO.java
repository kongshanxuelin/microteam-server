package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("teams_user")
public class Teams_userDAO extends Model<Teams_userDAO>{
	public static Teams_userDAO dao = new Teams_userDAO();

	public String getId(){
		return (String)get("id");
}
	public Teams_userDAO setId(String v){
		set("id",v);
		return this;
}
	
	public String getTeam_tid(){
		return (String)get("team_tid");
}
	public Teams_userDAO setTeam_tid(String v){
		set("team_tid",v);
		return this;
}
	
	
	public String getTeam_id(){
		return (String)get("team_id");
}
	public Teams_userDAO setTeam_id(String v){
		set("team_id",v);
		return this;
}
	public String getRole(){
		return (String)get("role");
}
	public Teams_userDAO setRole(String v){
		set("role",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public Teams_userDAO setUid(String v){
		set("uid",v);
		return this;
}}
