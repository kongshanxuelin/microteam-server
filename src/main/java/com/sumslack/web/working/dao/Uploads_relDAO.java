package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("uploads_rel")
public class Uploads_relDAO extends Model<Uploads_relDAO>{
	public static Uploads_relDAO dao = new Uploads_relDAO();

	public String getId(){
		return (String)get("id");
}
	public Uploads_relDAO setId(String v){
		set("id",v);
		return this;
}
	public String getObjType(){
		return (String)get("objType");
}
	public Uploads_relDAO setObjType(String v){
		set("objType",v);
		return this;
}
	public String getObjId(){
		return (String)get("objId");
}
	public Uploads_relDAO setObjId(String v){
		set("objId",v);
		return this;
}
	public String getUploadId(){
		return (String)get("uploadId");
}
	public Uploads_relDAO setUploadId(String v){
		set("uploadId",v);
		return this;
}
	public java.util.Date getDt(){
		return (java.util.Date)get("dt");
}
	public Uploads_relDAO setDt(java.util.Date v){
		set("dt",v);
		return this;
}}
