package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_webstat_projects")
public class M_webstat_projectsDAO extends Model<M_webstat_projectsDAO>{
	public static M_webstat_projectsDAO dao = new M_webstat_projectsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_webstat_projectsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getPrj_id(){
		return (String)get("prj_id");
}
	public M_webstat_projectsDAO setPrj_id(String v){
		set("prj_id",v);
		return this;
}
	public String getPrj_title(){
		return (String)get("prj_title");
}
	public M_webstat_projectsDAO setPrj_title(String v){
		set("prj_title",v);
		return this;
}
	public String getDsname(){
		return (String)get("dsname");
}
	public M_webstat_projectsDAO setDsname(String v){
		set("dsname",v);
		return this;
}
	public String getTeamid(){
		return (String)get("teamid");
}
	public M_webstat_projectsDAO setTeamid(String v){
		set("teamid",v);
		return this;
}}
