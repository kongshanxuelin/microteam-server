package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_tmpl")
public class M_work_tmplDAO extends Model<M_work_tmplDAO>{
	public static M_work_tmplDAO dao = new M_work_tmplDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_tmplDAO setId(String v){
		set("id",v);
		return this;
}
	

	public String getPid(){
		return (String)get("pid");
}
	public M_work_tmplDAO setPid(String v){
		set("pid",v);
		return this;
}
	
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_tmplDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	
	
	public String getTmpl_cateid(){
		return (String)get("tmpl_cateid");
}
	public M_work_tmplDAO setTmpl_cateid(String v){
		set("tmpl_cateid",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_tmplDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getTmpl_content(){
		return (String)get("tmpl_content");
}
	public M_work_tmplDAO setTmpl_content(String v){
		set("tmpl_content",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_tmplDAO setSts(String v){
		set("sts",v);
		return this;
}
	public Integer getOrd_(){
		return (Integer)get("ord_");
}
	public M_work_tmplDAO setOrd_(Integer v){
		set("ord_",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_tmplDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_tmplDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_tmplDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_tmplDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	
	public String getDesc_header(){
		return (String)get("desc_header");
}
	public M_work_tmplDAO setDesc_header(String v){
		set("desc_header",v);
		return this;
}
	
	public String getDesc_footer(){
		return (String)get("desc_footer");
}
	public M_work_tmplDAO setDesc_footer(String v){
		set("desc_footer",v);
		return this;
}
	
	public String getForm_js(){
		return (String)get("form_js");
}
	public M_work_tmplDAO setForm_js(String v){
		set("form_js",v);
		return this;
}
	
	public String getView_page(){
		return (String)get("view_page");
}
	public M_work_tmplDAO setView_page(String v){
		set("view_page",v);
		return this;
}
	
	
}
