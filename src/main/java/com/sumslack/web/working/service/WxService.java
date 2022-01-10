package com.sumslack.web.working.service;

import java.util.Date;

import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.app.jd.front.tool.PayUtil;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.StrUtil;

public class WxService {
	private static WxService instance = new WxService();
	
	private WxService(){
		
	}
	public synchronized static WxService getInstance(){
		if(instance == null){
			instance = new WxService();
		}
		return instance;
	}
	
	
	private static long lastTime = 0;
	private static String accessToken = "";
	
	public void genQrCode(String scene,int width,ServletResponse response){
			String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + getMsgToken();
			String json = "{"+
				    "\"scene\":\""+scene+"\","+
				    "\"width\":\""+width+"\""+
			"}";
			 response.setContentType("image/jpeg");  
	        PayUtil.httpRequestDownload(url, "POST", json,response);
	}
	
	
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
}
