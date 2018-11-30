package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_work_tmpl_records")
public class M_work_tmpl_recordsDAO extends Model<M_work_tmpl_recordsDAO>{
	public static M_work_tmpl_recordsDAO dao = new M_work_tmpl_recordsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_work_tmpl_recordsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTmpl_id(){
		return (String)get("tmpl_id");
}
	public M_work_tmpl_recordsDAO setTmpl_id(String v){
		set("tmpl_id",v);
		return this;
}
	public String getTmpl_cateid(){
		return (String)get("tmpl_cateid");
}
	public M_work_tmpl_recordsDAO setTmpl_cateid(String v){
		set("tmpl_cateid",v);
		return this;
}
	public String getDeflag(){
		return (String)get("deflag");
}
	public M_work_tmpl_recordsDAO setDeflag(String v){
		set("deflag",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_work_tmpl_recordsDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getModify_uid(){
		return (String)get("modify_uid");
}
	public M_work_tmpl_recordsDAO setModify_uid(String v){
		set("modify_uid",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_work_tmpl_recordsDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_work_tmpl_recordsDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	
	public String getIp(){
		return (String)get("ip");
}
	public M_work_tmpl_recordsDAO setIp(String v){
		set("ip",v);
		return this;
}
	
	public String getBrowser(){
		return (String)get("browser");
}
	public M_work_tmpl_recordsDAO setBrowser(String v){
		set("browser",v);
		return this;
}
	
	public String getRec_id(){
		return (String)get("rec_id");
}
	public M_work_tmpl_recordsDAO setRec_id(String v){
		set("rec_id",v);
		return this;
}

}
