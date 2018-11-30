package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_quan_comments")
public class M_quan_commentsDAO extends Model<M_quan_commentsDAO>{
	public static M_quan_commentsDAO dao = new M_quan_commentsDAO();

	public String getId(){
		return (String)get("id");
}
	public M_quan_commentsDAO setId(String v){
		set("id",v);
		return this;
}
	public String getFid(){
		return (String)get("fid");
}
	public M_quan_commentsDAO setFid(String v){
		set("fid",v);
		return this;
}
	public String getComment_content(){
		return (String)get("comment_content");
}
	public M_quan_commentsDAO setComment_content(String v){
		set("comment_content",v);
		return this;
}
	public java.util.Date getCreate_time(){
		return (java.util.Date)get("create_time");
}
	public M_quan_commentsDAO setCreate_time(java.util.Date v){
		set("create_time",v);
		return this;
}
	public String getCreate_uid(){
		return (String)get("create_uid");
}
	public M_quan_commentsDAO setCreate_uid(String v){
		set("create_uid",v);
		return this;
}
	public String getIp(){
		return (String)get("ip");
}
	public M_quan_commentsDAO setIp(String v){
		set("ip",v);
		return this;
}
	public String getReply_uid(){
		return (String)get("reply_uid");
}
	public M_quan_commentsDAO setReply_uid(String v){
		set("reply_uid",v);
		return this;
}
	public String getDelflag(){
		return (String)get("delflag");
}
	public M_quan_commentsDAO setDelflag(String v){
		set("delflag",v);
		return this;
}
	public java.sql.Timestamp getModify_time(){
		return (java.sql.Timestamp)get("modify_time");
}
	public M_quan_commentsDAO setModify_time(java.sql.Timestamp v){
		set("modify_time",v);
		return this;
}
	public String getCreate_nick(){
		return (String)get("create_nick");
}
	public M_quan_commentsDAO setCreate_nick(String v){
		set("create_nick",v);
		return this;
}
	public String getReply_nick(){
		return (String)get("reply_nick");
}
	public M_quan_commentsDAO setReply_nick(String v){
		set("reply_nick",v);
		return this;
}}
