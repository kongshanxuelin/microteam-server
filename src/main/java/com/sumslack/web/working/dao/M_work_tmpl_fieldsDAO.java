package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_tmpl_fields")
public class M_work_tmpl_fieldsDAO extends Model<M_work_tmpl_fieldsDAO>{
	public static M_work_tmpl_fieldsDAO dao = new M_work_tmpl_fieldsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_tmpl_fieldsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTmpl_id(){
		return (String)get("tmpl_id");
}
	public M_work_tmpl_fieldsDAO setTmpl_id(String v){
		set("tmpl_id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_work_tmpl_fieldsDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getLabel_suf(){
		return (String)get("label_suf");
}
	public M_work_tmpl_fieldsDAO setLabel_suf(String v){
		set("label_suf",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_work_tmpl_fieldsDAO setSts(String v){
		set("sts",v);
		return this;
}
	public Integer getOrd_(){
		return (Integer)get("ord_");
}
	public M_work_tmpl_fieldsDAO setOrd_(Integer v){
		set("ord_",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_tmpl_fieldsDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_tmpl_fieldsDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_tmpl_fieldsDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_tmpl_fieldsDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getUi_component(){
		return (String)get("ui_component");
}
	public M_work_tmpl_fieldsDAO setUi_component(String v){
		set("ui_component",v);
		return this;
}
	public String getUi_defaultvalue(){
		return (String)get("ui_defaultvalue");
}
	public M_work_tmpl_fieldsDAO setUi_defaultvalue(String v){
		set("ui_defaultvalue",v);
		return this;
}
	public Integer getUi_maxlen(){
		return (Integer)get("ui_maxlen");
}
	public M_work_tmpl_fieldsDAO setUi_maxlen(Integer v){
		set("ui_maxlen",v);
		return this;
}
	public Integer getUi_minlen(){
		return (Integer)get("ui_minlen");
}
	public M_work_tmpl_fieldsDAO setUi_minlen(Integer v){
		set("ui_minlen",v);
		return this;
}
	public String getUi_isreq(){
		return (String)get("ui_isreq");
}
	public M_work_tmpl_fieldsDAO setUi_isreq(String v){
		set("ui_isreq",v);
		return this;
}
	public String getUi_input_type(){
		return (String)get("ui_input_type");
}
	public M_work_tmpl_fieldsDAO setUi_input_type(String v){
		set("ui_input_type",v);
		return this;
}
	public String getUi_other(){
		return (String)get("ui_other");
}
	public M_work_tmpl_fieldsDAO setUi_other(String v){
		set("ui_other",v);
		return this;
}
	
	public String getUi_showlist(){
		return (String)get("ui_showlist");
}
	public M_work_tmpl_fieldsDAO setUi_showlist(String v){
		set("ui_showlist",v);
		return this;
}
	
	public String getUi_attrs(){
		return (String)get("ui_attrs");
}
	public M_work_tmpl_fieldsDAO setUi_attrs(String v){
		set("ui_attrs",v);
		return this;
}
	
	
}
