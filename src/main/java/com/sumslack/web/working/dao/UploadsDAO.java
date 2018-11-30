package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("uploads")
public class UploadsDAO extends Model<UploadsDAO>{
	public static UploadsDAO dao = new UploadsDAO();

	public String getId(){
		return (String)get("id");
}
	public UploadsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getName(){
		return (String)get("name");
}
	public UploadsDAO setName(String v){
		set("name",v);
		return this;
}
	public String getPath(){
		return (String)get("path");
}
	public UploadsDAO setPath(String v){
		set("path",v);
		return this;
}
	public String getSpath(){
		return (String)get("spath");
}
	public UploadsDAO setSpath(String v){
		set("spath",v);
		return this;
}
	public String get_ext(){
		return (String)get("_ext");
}
	public UploadsDAO set_ext(String v){
		set("_ext",v);
		return this;
}
	public java.util.Date getCreate_at(){
		return (java.util.Date)get("create_at");
}
	public UploadsDAO setCreate_at(java.util.Date v){
		set("create_at",v);
		return this;
}}
