package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.CacheName;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("uid")
@Table("users")
@CacheName(name="tbl_users")
public class UsersDAO extends Model<UsersDAO>{
	public static UsersDAO dao = new UsersDAO();

	public String getUid(){
		return (String)get("uid");
}
	public UsersDAO setUid(String v){
		set("uid",v);
		return this;
}
	
	public String getUsername(){
		return (String)get("username");
}
	public UsersDAO setUsername(String v){
		set("username",v);
		return this;
}
	
	public String getPwd(){
		return (String)get("pwd");
}
	public UsersDAO setPwd(String v){
		set("pwd",v);
		return this;
}
	
	public String getNick(){
		return (String)get("nick");
}
	public UsersDAO setNick(String v){
		set("nick",v);
		return this;
}
	public String getSource(){
		return (String)get("source");
}
	public UsersDAO setSource(String v){
		set("source",v);
		return this;
}
	public String getCompany_id(){
		return (String)get("company_id");
}
	public UsersDAO setCompany_id(String v){
		set("company_id",v);
		return this;
}
	public String getCompany_name(){
		return (String)get("company_name");
}
	public UsersDAO setCompany_name(String v){
		set("company_name",v);
		return this;
}
	public String getToken(){
		return (String)get("token");
}
	public UsersDAO setToken(String v){
		set("token",v);
		return this;
}
	

	public String getAvatar(){
		return (String)get("avatar");
}
	public UsersDAO setAvatar(String v){
		set("avatar",v);
		return this;
}
	
	public String getDept(){
		return (String)get("dept");
}
	public UsersDAO setDept(String v){
		set("dept",v);
		return this;
}
	public String getSession_key(){
		return (String)get("session_key");
}
	public UsersDAO setSession_key(String v){
		set("session_key",v);
		return this;
}
	
	public String getPy(){
		return (String)get("py");
}
	public UsersDAO setPy(String v){
		set("py",v);
		return this;
}
	
	public String getTel(){
		return (String)get("tel");
}
	public UsersDAO setTel(String v){
		set("tel",v);
		return this;
}
	
	public String getTruename(){
		return (String)get("truename");
}
	public UsersDAO setTruename(String v){
		set("truename",v);
		return this;
}
	public String getAddr(){
		return (String)get("addr");
}
	public UsersDAO setAddr(String v){
		set("addr",v);
		return this;
}
	
	public String getSign(){
		return (String)get("sign");
}
	public UsersDAO setSign(String v){
		set("sign",v);
		return this;
}
	
	public String getEmail(){
		return (String)get("email");
}
	public UsersDAO setEmail(String v){
		set("email",v);
		return this;
}
	
	public String getDept_id(){
		return (String)get("dept_id");
}
	public UsersDAO setDept_id(String v){
		set("dept_id",v);
		return this;
}
	
	public String getDept_name(){
		return (String)get("dept_name");
}
	public UsersDAO setDept_name(String v){
		set("dept_name",v);
		return this;
}
	
	
	public Integer getSumslack_uid(){
		return (Integer)get("sumslack_uid");
}
	public UsersDAO setSumslack_uid(Integer v){
		set("sumslack_uid",v);
		return this;
}
	
	
}
