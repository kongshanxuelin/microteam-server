package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_projects_zhang")
public class M_work_projects_zhangDAO extends Model<M_work_projects_zhangDAO>{
	public static M_work_projects_zhangDAO dao = new M_work_projects_zhangDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_projects_zhangDAO setId(String v){
		set("id",v);
		return this;
}
	public String getPrj_id(){
		return (String)get("prj_id");
}
	public M_work_projects_zhangDAO setPrj_id(String v){
		set("prj_id",v);
		return this;
}
	public Integer getD(){
		return (Integer)get("d");
}
	public M_work_projects_zhangDAO setD(Integer v){
		set("d",v);
		return this;
}
	public Integer getCate_id(){
		return (Integer)get("cate_id");
}
	public M_work_projects_zhangDAO setCate_id(Integer v){
		set("cate_id",v);
		return this;
}
	public String getDescription(){
		return (String)get("description");
}
	public M_work_projects_zhangDAO setDescription(String v){
		set("description",v);
		return this;
}
	public java.sql.Timestamp getDt(){
		return (java.sql.Timestamp)get("dt");
}
	public M_work_projects_zhangDAO setDt(java.sql.Timestamp v){
		set("dt",v);
		return this;
}
	public java.math.BigDecimal getJe(){
		return (java.math.BigDecimal)get("je");
}
	public M_work_projects_zhangDAO setJe(java.math.BigDecimal v){
		set("je",v);
		return this;
}
	public String getFzr(){
		return (String)get("fzr");
}
	public M_work_projects_zhangDAO setFzr(String v){
		set("fzr",v);
		return this;
}
	public String getJoiner(){
		return (String)get("joiner");
}
	public M_work_projects_zhangDAO setJoiner(String v){
		set("joiner",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_projects_zhangDAO setSts(String v){
		set("sts",v);
		return this;
}}
