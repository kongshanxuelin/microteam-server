package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_tmpl_fields_value")
public class M_work_tmpl_fields_valueDAO extends Model<M_work_tmpl_fields_valueDAO>{
	public static M_work_tmpl_fields_valueDAO dao = new M_work_tmpl_fields_valueDAO();

	public String getTitle(){
		return (String)get("title");
}
	public M_work_tmpl_fields_valueDAO setTitle(String v){
		set("title",v);
		return this;
}
	
	public String getId(){
		return (String)get("id");
}
	public M_work_tmpl_fields_valueDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTmpl_id(){
		return (String)get("tmpl_id");
}
	public M_work_tmpl_fields_valueDAO setTmpl_id(String v){
		set("tmpl_id",v);
		return this;
}
	public String getField_id(){
		return (String)get("field_id");
}
	public M_work_tmpl_fields_valueDAO setField_id(String v){
		set("field_id",v);
		return this;
}
	public String getField_value(){
		return (String)get("field_value");
}
	public M_work_tmpl_fields_valueDAO setField_value(String v){
		set("field_value",v);
		return this;
}
	
	public String getField_value_display(){
		return (String)get("field_value_display");
}
	public M_work_tmpl_fields_valueDAO setField_value_display(String v){
		set("field_value_display",v);
		return this;
}
	
	
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_work_tmpl_fields_valueDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public String getTmpl_id_id(){
		return (String)get("tmpl_id_id");
}
	public M_work_tmpl_fields_valueDAO setTmpl_id_id(String v){
		set("tmpl_id_id",v);
		return this;
}}
