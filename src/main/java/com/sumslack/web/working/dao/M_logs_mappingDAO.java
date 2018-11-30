package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("uri")
@Table("m_logs_mapping")
public class M_logs_mappingDAO extends Model<M_logs_mappingDAO>{
	public static M_logs_mappingDAO dao = new M_logs_mappingDAO();

	public String getUri(){
		return (String)get("uri");
}
	public M_logs_mappingDAO setUri(String v){
		set("uri",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_logs_mappingDAO setTitle(String v){
		set("title",v);
		return this;
}}
