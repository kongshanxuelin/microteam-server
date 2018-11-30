package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_docs")
public class M_docsDAO extends Model<M_docsDAO>{
	public static M_docsDAO dao = new M_docsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_docsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_docsDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getPid(){
		return (String)get("pid");
}
	public M_docsDAO setPid(String v){
		set("pid",v);
		return this;
}
	public String getIsfile(){
		return (String)get("isfile");
}
	public M_docsDAO setIsfile(String v){
		set("isfile",v);
		return this;
}
	public Integer get_ord(){
		return (Integer)get("_ord");
}
	public M_docsDAO set_ord(Integer v){
		set("_ord",v);
		return this;
}
	public String getIcon(){
		return (String)get("icon");
}
	public M_docsDAO setIcon(String v){
		set("icon",v);
		return this;
}
	public String getTeamid(){
		return (String)get("teamid");
}
	public M_docsDAO setTeamid(String v){
		set("teamid",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_docsDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getScope(){
		return (String)get("scope");
}
	public M_docsDAO setScope(String v){
		set("scope",v);
		return this;
}
	public String getFileid(){
		return (String)get("fileid");
}
	public M_docsDAO setFileid(String v){
		set("fileid",v);
		return this;
}
	public String getContent(){
		return (String)get("content");
}
	public M_docsDAO setContent(String v){
		set("content",v);
		return this;
}
	public String getCate(){
		return (String)get("cate");
}
	public M_docsDAO setCate(String v){
		set("cate",v);
		return this;
}}
