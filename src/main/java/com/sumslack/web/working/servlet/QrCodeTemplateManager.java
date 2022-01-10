package com.sumslack.web.working.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sumslack.jsptagex.util.StrUtil;

//import gui.ava.html.Html2Image;

public class QrCodeTemplateManager {
	private static QrCodeTemplateManager instance = new QrCodeTemplateManager();
	private QrCodeTemplateManager() {}
	
	Image image;
	private BufferedImage bufImage;  //用于显示的缓冲区图像 
	public static QrCodeTemplateManager getInstance() {
		if(instance == null) {
			instance = new QrCodeTemplateManager();
		}
		return instance;
	}
		
	public String genImageStr(String title,String[] desc,String topLeftImage,String qrcode,String titleColor,String descColor) {
		String str	= "<div style=\"text-align:center;font-family: 'MicrosoftYaHei';font-size:18px;margin-bottom:20px;margin-top:20px;color:#000000\"><b>"+title+"</b></div>" + 
				"  <table width=\"100%\" style='background-color:#FAFAFA'>\r\n" + 
				"	<tr>\r\n" + 
				"		<td>\r\n" + 
				"			<div style='margin:20px;'>\r\n" + 
				"				<div style=\"margin-bottom:20px;\">#desc#</div>\r\n" + 
				"			</div>\r\n" + 
				"		</td>\r\n" + 
				"		<td align='right' valign='top' style='margin-top:20px;'>\r\n" + 
				"			<img width=80 height=80 style='border-radius:10%' src='"+topLeftImage+"'>\r\n" + 
				"			</td>\r\n" + 
				"	</tr></table>\r\n" + 
				"	<table width=\"100%\"><tr>\r\n" + 
				"		<td align='center' valign='center'>\r\n" + 
				"		  <img width=180 height=180 src='"+qrcode+"'>\r\n" + 
				"			</td>\r\n" + 
				"		<td align='center' valign='center'>\r\n" + 
				"			<div style='margin-left:10px' style='color:#000;margin-top:30px;'>\r\n" + 
				"			<div style='font-size:14px;margin-bottom:10px'>保存分享发送给好友</div>\r\n" + 
				"			<div style='font-size:14px;margin-bottom:10px'>长按识别二维码使用</div>\r\n" + 
				"			<div style='font-size:12px;color:gray'>由小团队管理小程序生成</div>\r\n" + 
				"			</div>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"  </table>";
		String r = "";
		for(String _d : desc) {
			r+= "<p style=\"margin-top:10px;margin-bottom:10px;font-family: 'MicrosoftYaHei';font-size:16px;color:"+descColor+"\">"+_d+"</p>";
		}
		str = StrUtil.replace(str, "#desc#", r);
		return str;
	}
	
	
	public String genImageStr(String title,String base64,String qrcode) {
		String str	= "<div style=\"text-align:center;font-family: 'MicrosoftYaHei';font-size:18px;margin-bottom:20px;margin-top:20px;color:#000000\"><b>"+title+"</b></div>" + 
				"<div style=\\\"text-align:center;> <img border=0 src=\""+base64+"\" > </div>  " + 
				"	<table width=\"100%\"><tr>\r\n" + 
				"		<td align='center' valign='center'>\r\n" + 
				"		  <img width=180 height=180 src='"+qrcode+"'>\r\n" + 
				"			</td>\r\n" + 
				"		<td align='center' valign='center'>\r\n" + 
				"			<div style='margin-left:10px' style='color:#000;margin-top:30px;'>\r\n" + 
				"			<div style='font-size:14px;margin-bottom:10px'>保存分享发送给好友</div>\r\n" + 
				"			<div style='font-size:14px;margin-bottom:10px'>长按识别二维码使用</div>\r\n" + 
				"			<div style='font-size:12px;color:gray'>由小团队管理小程序生成</div>\r\n" + 
				"			</div>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"  </table>";
		return str;
	}
	
	public String genImageStr(String title,String[] desc,String topLeftImage,String qrcode) {
		return genImageStr(title,desc,topLeftImage,qrcode,"#000000","#136C5E");
	}
	

	
	
}
