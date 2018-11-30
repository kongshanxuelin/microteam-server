package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_quan_like")
public class M_quan_likeDAO extends Model<M_quan_likeDAO>{
	public static M_quan_likeDAO dao = new M_quan_likeDAO();

	public String getId(){
		return (String)get("id");
}
	public M_quan_likeDAO setId(String v){
		set("id",v);
		return this;
}
	public String getUid(){
		return (String)get("uid");
}
	public M_quan_likeDAO setUid(String v){
		set("uid",v);
		return this;
}
	public String getFid(){
		return (String)get("fid");
}
	public M_quan_likeDAO setFid(String v){
		set("fid",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_quan_likeDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_quan_likeDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getIp(){
		return (String)get("ip");
}
	public M_quan_likeDAO setIp(String v){
		set("ip",v);
		return this;
}
	public String getPlatform_id(){
		return (String)get("platform_id");
}
	public M_quan_likeDAO setPlatform_id(String v){
		set("platform_id",v);
		return this;
}
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_quan_likeDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_quan_likeDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getNick(){
		return (String)get("nick");
}
	public M_quan_likeDAO setNick(String v){
		set("nick",v);
		return this;
}}
