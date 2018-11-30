package com.sumslack.web.working.dao;
import com.sumslack.jsptagex.anno.ar.PrimaryKey;
import com.sumslack.jsptagex.anno.ar.Table;
import com.sumslack.jsptagex.db.ar.Model;
@PrimaryKey("id")
@Table("m_act")
public class M_actDAO extends Model<M_actDAO>{
	public static M_actDAO dao = new M_actDAO();

	public String getId(){
		return (String)get("id");
}
	public M_actDAO setId(String v){
		set("id",v);
		return this;
}
	public String getTitle(){
		return (String)get("title");
}
	public M_actDAO setTitle(String v){
		set("title",v);
		return this;
}
	public String getD(){
		return (String)get("d");
}
	public M_actDAO setD(String v){
		set("d",v);
		return this;
}
	public java.util.Date getStart_date(){
		return (java.util.Date)get("start_date");
}
	public M_actDAO setStart_date(java.util.Date v){
		set("start_date",v);
		return this;
}
	public java.util.Date getEnd_date(){
		return (java.util.Date)get("end_date");
}
	public M_actDAO setEnd_date(java.util.Date v){
		set("end_date",v);
		return this;
}
	public Integer getFreq_num(){
		return (Integer)get("freq_num");
}
	public M_actDAO setFreq_num(Integer v){
		set("freq_num",v);
		return this;
}
	public String getFreq_unit(){
		return (String)get("freq_unit");
}
	public M_actDAO setFreq_unit(String v){
		set("freq_unit",v);
		return this;
}
	public String getIs_alarm(){
		return (String)get("is_alarm");
}
	public M_actDAO setIs_alarm(String v){
		set("is_alarm",v);
		return this;
}
	public java.sql.Timestamp getModifytime(){
		return (java.sql.Timestamp)get("modifytime");
}
	public M_actDAO setModifytime(java.sql.Timestamp v){
		set("modifytime",v);
		return this;
}
	public String getTeamid(){
		return (String)get("teamid");
}
	public M_actDAO setTeamid(String v){
		set("teamid",v);
		return this;
}
	public Integer getAlarm_before_num(){
		return (Integer)get("alarm_before_num");
}
	public M_actDAO setAlarm_before_num(Integer v){
		set("alarm_before_num",v);
		return this;
}
	public String getAlarm_before_unit(){
		return (String)get("alarm_before_unit");
}
	public M_actDAO setAlarm_before_unit(String v){
		set("alarm_before_unit",v);
		return this;
}
	public java.util.Date getCur_start_date(){
		return (java.util.Date)get("cur_start_date");
}
	public M_actDAO setCur_start_date(java.util.Date v){
		set("cur_start_date",v);
		return this;
}
	public java.util.Date getCur_end_date(){
		return (java.util.Date)get("cur_end_date");
}
	public M_actDAO setCur_end_date(java.util.Date v){
		set("cur_end_date",v);
		return this;
}
	public java.util.Date getAct_time(){
		return (java.util.Date)get("act_time");
}
	public M_actDAO setAct_time(java.util.Date v){
		set("act_time",v);
		return this;
}
	public String getAct_addr(){
		return (String)get("act_addr");
}
	public M_actDAO setAct_addr(String v){
		set("act_addr",v);
		return this;
}
	public String getSts(){
		return (String)get("sts");
}
	public M_actDAO setSts(String v){
		set("sts",v);
		return this;
}
	
	public String getUid(){
		return (String)get("uid");
}
	public M_actDAO setUid(String v){
		set("uid",v);
		return this;
}
	public Integer getMin_num(){
		return (Integer)get("min_num");
}
	public M_actDAO setMin_num(Integer v){
		set("min_num",v);
		return this;
}
	public String getCur_sts(){
		return (String)get("cur_sts");
}
	public M_actDAO setCur_sts(String v){
		set("cur_sts",v);
		return this;
}}
