package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_docs_share_rel")
public class M_docs_share_relDAO extends Model<M_docs_share_relDAO>{
	public static M_docs_share_relDAO dao = new M_docs_share_relDAO();

	public String getId(){
		return (String)get("id");
}
	public M_docs_share_relDAO setId(String v){
		set("id",v);
		return this;
}
	public String getDoc_id(){
		return (String)get("doc_id");
}
	public M_docs_share_relDAO setDoc_id(String v){
		set("doc_id",v);
		return this;
}
	public String getShare_scope(){
		return (String)get("share_scope");
}
	public M_docs_share_relDAO setShare_scope(String v){
		set("share_scope",v);
		return this;
}
	public String getShare_scope_id(){
		return (String)get("share_scope_id");
}
	public M_docs_share_relDAO setShare_scope_id(String v){
		set("share_scope_id",v);
		return this;
}
	public Integer get_ops(){
		return (Integer)get("_ops");
}
	public M_docs_share_relDAO set_ops(Integer v){
		set("_ops",v);
		return this;
}}
