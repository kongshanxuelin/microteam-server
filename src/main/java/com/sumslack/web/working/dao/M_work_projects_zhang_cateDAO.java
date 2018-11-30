package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_projects_zhang_cate")
public class M_work_projects_zhang_cateDAO extends Model<M_work_projects_zhang_cateDAO>{
	public static M_work_projects_zhang_cateDAO dao = new M_work_projects_zhang_cateDAO();

	public Integer getId(){
		return (Integer)get("id");
}
	public M_work_projects_zhang_cateDAO setId(Integer v){
		set("id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_projects_zhang_cateDAO setTitle(String v){
		set("title",v);
		return this;
}
	public java.sql.Timestamp getDt(){
		return (java.sql.Timestamp)get("dt");
}
	public M_work_projects_zhang_cateDAO setDt(java.sql.Timestamp v){
		set("dt",v);
		return this;
}
	public Integer getD(){
		return (Integer)get("d");
}
	public M_work_projects_zhang_cateDAO setD(Integer v){
		set("d",v);
		return this;
}}
