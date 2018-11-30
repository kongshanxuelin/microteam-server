package com.sumslack.app.jd.front.tool;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.UserBean;

public class WXTemplateMsgManager {
	
	public static final class FORM {
		//参与活动提醒
		public static final String ACT_START = "BPNQiJCpBiyQdHTgqa7MsecMpq9emDYw2M5CIZTNBUQ";
		//报名活动参加提醒
		public static final String ACT_JOIN = "2D933T0U3JxQgou3dEnhERP3uOmWYr8TrM8yzE11Hn0";
	}
	
	public static void sendMsgCard(String openId,String formId,String time,String title,String description ){
		String temp_id = StrUtil.formatNullStr(TagConst.globalMap.get("wx.template.notify"));
		String dataKeyWorks = "{";
		dataKeyWorks += "\"keyword1\":{\"color\":\"#173177\",\"value\":\""+time+"\"},";
		dataKeyWorks += "\"keyword2\":{\"color\":\"#173177\",\"value\":\""+title+"\"},";
		dataKeyWorks += "\"keyword3\":{\"color\":\"#173177\",\"value\":\""+description+"\"}";
		dataKeyWorks += "}";
		
		String json = "{"+
				    "\"touser\":\""+openId+"\","+
				    "\"template_id\":\""+temp_id+"\","+
				    "\"page\":\"pages/index/index\","+
				    "\"form_id\":\""+formId+"\","+
				    "\"data\":"+dataKeyWorks+""+
					"}";
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + getMsgToken();
        String result =PayUtil.httpRequest(url, "POST", json);
	}
	
	public static void sendMsgCard(String date,String openId,String formId,String time,String title,String description ){
		String temp_id = StrUtil.formatNullStr(TagConst.globalMap.get("wx.template.notify"));
		String dataKeyWorks = "{";
		dataKeyWorks += "\"keyword1\":{\"color\":\"#173177\",\"value\":\""+time+"\"},";
		dataKeyWorks += "\"keyword2\":{\"color\":\"#173177\",\"value\":\""+title+"\"},";
		dataKeyWorks += "\"keyword3\":{\"color\":\"#173177\",\"value\":\""+description+"\"}";
		dataKeyWorks += "}";
		
		String json = "{"+
				    "\"touser\":\""+openId+"\","+
				    "\"template_id\":\""+temp_id+"\","+
				    "\"page\":\"pages/index/index?dt="+date.split(" ")[0]+"\","+
				    "\"form_id\":\""+formId+"\","+
				    "\"data\":"+dataKeyWorks+""+
					"}";
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + getMsgToken();
        String result =PayUtil.httpRequest(url, "POST", json);
	}
	
	public static void sendAlarm_ActStart(String formId,String touid,String actId,String time,String title,String d) {
		String dataKeyWorks = "{";
		dataKeyWorks += "\"keyword1\":{\"color\":\"#173177\",\"value\":\""+title+"\"},";
		dataKeyWorks += "\"keyword2\":{\"color\":\"#173177\",\"value\":\""+time+"\"},";
		dataKeyWorks += "\"keyword3\":{\"color\":\"#173177\",\"value\":\""+d+"\"}";
		dataKeyWorks += "}";
		
		String json = "{"+
				    "\"touser\":\""+touid+"\","+
				    "\"template_id\":\""+FORM.ACT_START+"\","+
				    "\"page\":\"pages/paper/index?type=actDetail&id="+actId+"\","+
				    "\"form_id\":\""+formId+"\","+
				    "\"data\":"+dataKeyWorks+""+
					"}";
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + getMsgToken();
        String result =PayUtil.httpRequest(url, "POST", json);
	}
	
	public static void sendAlarm_ActJoin(String formId,String touid,String actId,String time,int num,String title,String d) {
		String dataKeyWorks = "{";
		dataKeyWorks += "\"keyword1\":{\"color\":\"#173177\",\"value\":\""+title+"\"},";
		dataKeyWorks += "\"keyword2\":{\"color\":\"#173177\",\"value\":\""+time+"\"},";
		dataKeyWorks += "\"keyword3\":{\"color\":\"#173177\",\"value\":\""+num+"\"},";
		dataKeyWorks += "\"keyword4\":{\"color\":\"#173177\",\"value\":\""+d+"\"}";
		dataKeyWorks += "}";
		
		String json = "{"+
				    "\"touser\":\""+touid+"\","+
				    "\"template_id\":\""+FORM.ACT_JOIN+"\","+
				    "\"page\":\"pages/paper/index?type=actDetail&id="+actId+"\","+
				    "\"form_id\":\""+formId+"\","+
				    "\"data\":"+dataKeyWorks+""+
					"}";
		String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + getMsgToken();
        String result =PayUtil.httpRequest(url, "POST", json);
	}
	
	public static String getJSSDKTicket() {
		if(lastTime_ticket>0 && ((new Date().getTime()-lastTime_ticket)<6000*1000)){
			return jssdk_tiket;
		}
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=" + getMsgTokenGongZhongHao();
		String json =PayUtil.httpRequest(url, "POST",null);
		if(json!=null) {
			JSONObject _json = JSON.parseObject(json);
			if(_json!=null && !StrUtil.formatNullStr(_json.get("ticket")).equals("")) {
				lastTime_ticket = new Date().getTime();
				int errcode = StrUtil.formatNullStrInt(_json.get("errcode"), 40000);
				if(errcode>0) {
					return null;
				}else {
					jssdk_tiket = StrUtil.formatNullStr(_json.get("ticket"));
				}
			}
		}
		return jssdk_tiket;
	}
	
	public static void sendMsg2User(String openId,String data){
		String json = "{"+
				    "\"touser\":\""+openId+"\","+
				    "\"msgtype\":\"text\","+
				    "\"text\":"+
				    "{"+
				    "\"content\":\""+data+"\""+
				    "}"+
					"}";
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + getMsgToken();
        String result =PayUtil.httpRequest(url, "POST", json);
	}
	
	
	public static void genQrCodeUnLimit(String scene,int width,String localPath){
		String url = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + getMsgToken();
		String json = "{"+
			    "\"scene\":\""+scene+"\","+
			    "\"width\":\""+width+"\""+
				"}";
        PayUtil.httpRequestDownload(url, "POST", json,localPath);
        
	}
	
	public static String genQrCodeTextTempl(HttpServletRequest request,String uid, String name,String phone,String company,String title,String addr,String industry,String qrcodeLink,String avatorUrl){
		String _avator = "";
		try {
			String _path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/upload";
			ImageUtils.download(avatorUrl, _path + "/avator_" + uid + ".jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String _domain = StrUtil.formatNullStr(TagConst.globalMap.get("domain"));
		_domain = StrUtil.replace(_domain, "https://", "http://");
		String imageUrl = _domain + "/upload/avator_" + uid + ".jpg";
		String str = "<table width=\"100%\" style='background-color:#FAFAFA'>"
						+"<tr>"
						+"	<td>"
						+"		<div style='margin:20px;'>"
							+"			<div style='font-size:16px;margin-bottom:10px'>"+name+"</div>"
							+"		<div style='font-size:14px;margin-bottom:10px'>"+StrUtil.formatNullStr(title) + "-" + StrUtil.formatNullStr(company)+"</div>"
							+"		<div style='font-size:14px;margin-bottom:10px'>"+StrUtil.formatNullStr(industry)+"</div>"
							+"		<div style='height:22px;line-height:22px;font-size:16px;margin-top:28px;'><img width=22 height=22 src='http://h5.sumslack.com/wx/phone.png'>&nbsp; "+StrUtil.formatNullStr(phone)+"</div>"
								+"	<div style='height:22px;line-height:22px;font-size:16px;margin-top:10px'><img width=22 height=22 src='http://h5.sumslack.com/wx/addr.png'>&nbsp;"+StrUtil.formatNullStr(addr)+"</div>"
									+"</div>"
							+"</td>"
							+"<td align='right' valign='top' style='margin-top:20px;'>"
							+"	<img width=80 height=80 style='border-radius:10%' src='"+imageUrl+"'>"
								+"</td>"
						+"</tr></table>"
						+"<table width=\"100%\"><tr>"
						+"	<td align='center' valign='center'>"
						+"	  <img width=180 height=180 src='"+qrcodeLink+"'>"
							+"	</td>"
							+"<td align='center' valign='center'>"
							+"	<div style='margin-left:10px' style='color:#000;margin-top:30px;'>"
							+"	<div style='font-size:14px;margin-bottom:10px'><b style='color:green'>"+name+"</b>专属小程序码</div>"
							+"	<div style='font-size:14px;margin-bottom:10px'>长按识别二维码保存名片</div>"
							+"	<div style='font-size:12px;color:gray'>名片由商务工作记事册小程序生成</div>"
							+"	</div>"
							+"</td>"
					+"	</tr>"
					  +"</table>";
		return str;
	}
	
	public static String genQrCodeTextTempl_eForm(HttpServletRequest request,String qrcodeLink,UserBean ub,String formId){
		Map formMap = new HashMap();
		try {
			formMap = TagJDBCInstance.getInstance().queryOne("work", "select m_work_tmpl.*,(select count(*) from m_work_tmpl_fields where tmpl_id=m_work_tmpl.id) num from m_work_tmpl where id=?", new Object[]{formId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(formMap==null){
			formMap = new HashMap();
		}
		
		String _avator = "";
		String str = "<table width=\"100%\" style='background-color:#FAFAFA'>"
						+"<tr>"
						+"	<td>"
						+"		<div style='margin:20px;'>"
							+"			<div style='font-size:20px;margin-bottom:10px'>"+StrUtil.formatNullStr(formMap.get("title"))+"</div>"
							+"		<div style='font-size:14px;margin-bottom:10px'>"+StrUtil.formatNullStr(formMap.get("tmpl_content"))+"</div>"
							+"		<div style='font-size:14px;margin-bottom:10px'>该表单共有"+StrUtil.formatNullStr(formMap.get("num"))+"个问题</div>"
									+"</div>"
							+"</td>"
						+"</tr></table>"
						+"<table width=\"100%\"><tr>"
						+"	<td align='center' valign='center'>"
						+"	  <img width=180 height=180 src='"+qrcodeLink+"'>"
							+"	</td>"
							+"<td align='center' valign='center'>"
							+"	<div style='margin-left:10px' style='margin-top:30px;'>"
							+"	<div style='font-size:14px;margin-bottom:10px'><b style='color:green'>"+StrUtil.formatNullStr(formMap.get("title"))+"</b>专属小程序码</div>"
							+"	<div style='font-size:14px;margin-bottom:10px'>长按识别二维码填写表单</div>"
							+"	<div style='font-size:12px;color:gray'>表单由商务工作记事册小程序生成</div>"
							+"	</div>"
							+"</td>"
					+"	</tr>"
					  +"</table>";
		return str;
	}
	
	public static String genQrCodeTextTempl(HttpServletRequest request,String qrcodeLink,UserBean ub,String title,String content){	
		String _avator = "";
		String str = "<table width=\"100%\" style='background-color:#FAFAFA'>"
						+"<tr>"
						+"	<td>"
						+"		<div style='margin:20px;'>"
						+"			<div style='font-size:14px;margin-bottom:10px'>"+content+"</div></div>" 
						+"</td>"
						+"</tr></table>"
						+"<table width=\"100%\"><tr>"
						+"	<td align='center' valign='center'>"
						+"	  <img width=180 height=180 src='"+qrcodeLink+"'>"
							+"	</td>"
							+"<td align='center' valign='center'>"
							+"	<div style='margin-left:10px' style='margin-top:30px;'>"
							+"	<div style='font-size:14px;margin-bottom:10px'><b style='color:green'>"+title+"</b>专属小程序码</div>"
							+"	<div style='font-size:14px;margin-bottom:10px'>长按识别二维码</div>"
							+"	<div style='font-size:12px;color:gray'>表单由商务工作记事册小程序生成</div>"
							+"	</div>"
							+"</td>"
					+"	</tr>"
					  +"</table>";
		return str;
	}
	
	public static void main(String[] args) {
		//genQrCodeUnLimit("test",250,"f:\\1.jpg");
		
		//final HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		//imageGenerator.loadHtml(genQrCodeTextTempl("xingming","122222","宁波森浦","CTO","因周进龙等哈阿斯顿飞撒旦发","http://192.168.1.63/1.jpg","http://192.168.1.63/1.jpg"));
		//imageGenerator.saveAsImage("f:/test2.png");
		
	}
	
	private static long lastTime = 0;
	private static long lastTime_ticket = 0;
	private static String accessToken = "";
	private static String jssdk_tiket = "";
	
	private static String getMsgToken(){
		if(lastTime>0 && ((new Date().getTime()-lastTime)<6000*1000)){
			return accessToken;
		}
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
	StrUtil.formatNullStr(TagConst.globalMap.get("wx.appid"),"wxac3f2ac1a0efa13e")+"&secret=" + StrUtil.formatNullStr(TagConst.globalMap.get("wx.seckey"),"edc02c8a1a6efd127b977edd93286685");
		String retStr = PayUtil.httpRequest(url, "GET", null);
		JSONObject json = JSON.parseObject(retStr);
		if(json!=null){
			lastTime = new Date().getTime();
			accessToken = StrUtil.formatNullStr(json.get("access_token"));
		}
		return accessToken;
	}
	
	private static long lastTime_gzh = 0;
	private static String accessToken_gzh = "";
	private static String getMsgTokenGongZhongHao(){
		String appid = (StrUtil.formatNullStr(TagConst.globalMap.get("gzh.appid")));
		String appkey = (StrUtil.formatNullStr(TagConst.globalMap.get("gzh.appkey")));
		if(lastTime_gzh>0 && ((new Date().getTime()-lastTime_gzh)<6000*1000)){
			return accessToken_gzh;
		}
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+
				appid+"&secret=" + appkey;
		String retStr = PayUtil.httpRequest(url, "GET", null);
		JSONObject json = JSON.parseObject(retStr);
		if(json!=null){
			lastTime_gzh = new Date().getTime();
			accessToken_gzh = StrUtil.formatNullStr(json.get("access_token"));
		}
		return accessToken_gzh;
	}
	

	
	
}
