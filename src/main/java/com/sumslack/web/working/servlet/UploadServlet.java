package com.sumslack.web.working.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.app.jd.front.tool.ImageUtils;
import com.sumslack.jsptagex.rest.TagRest;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.tag.TagConst.ContentType;
import com.sumslack.jsptagex.tag.TagConst.Encode;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.FileManager;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.web.servlet.AjaxServlet;
import com.sumslack.web.working.bean.FileBean;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.UploadsDAO;
import com.sumslack.web.working.jobs.JobInit;
import com.sumslack.web.working.service.TencentAIService;
import com.sumslack.web.working.service.UserService;

import gui.ava.html.Html2Image;

@TagRest(value = "wx/upload")
public class UploadServlet extends AjaxServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		final Map retMap = new HashMap();
		retMap.put("res", false);
		request.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String picBaseUrl = StrUtil.formatNullStr(TagConst.globalMap.get("upload.url"));
		List<FileItem> files = new ArrayList();
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator itr = items.iterator();
		Map param = new HashMap();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) {
				String _key = item.getFieldName();
				param.put(StrUtil.formatNullStr(_key), StrUtil.formatNullStr(item.getString()));
			}else {
				if (item.getName() != null && !"".equals(item.getName())) {
					files.add(item);
				}
			}
		}
		List<FileBean> fileList = new ArrayList();
		if (files != null && files.size() > 0) {
			String ymd = DateUtils.getCurrentToDay("yyyy/MM/dd");
			String _path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/upload/" + ymd;
			for (FileItem _file : files) {
				
				String _fileName = IdWorker.getInstance().uuid()
						+ _file.getName().substring(
								_file.getName().lastIndexOf("."));
				FileManager.mkdir(_path);
				File source = new File(_path + "/" + _fileName);
				try {
					_file.write(source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				FileBean _fb = null;
				if(param.containsKey("type") && StrUtil.formatNullStr(param.get("type")).startsWith("ai_")) {
					String _aiType = StrUtil.formatNullStr(param.get("type"));
					_aiType = _aiType.substring(3, _aiType.length());
					String uploadUrl = StrUtil.formatNullStr(TagConst.globalMap.get("upload.url"));
					String qrcode = uploadUrl.substring(0, uploadUrl.lastIndexOf("upload/")) + "r/wx/service/qrcode?path=ai_"+_aiType;
				if (_file.getName().toLowerCase().endsWith(".png")
						|| _file.getName().toLowerCase().endsWith(".jpg")
						|| _file.getName().toLowerCase().endsWith(".jpeg")) { // 生成缩略图
					String _snapfileName = IdWorker.getInstance().uuid()
							+ "_s"
							+ _file.getName().substring(
									_file.getName().lastIndexOf("."));
					ImageUtils.createPreviewImage(source.getAbsolutePath(),
							_path + "/" + _snapfileName);
					_fb = new FileBean(_file.getName(), DateUtils
							.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName,
							DateUtils.getCurrentToDay("yyyy/MM/dd") + "/"
									+ _snapfileName);
					fileList.add(_fb);
					retMap.put("spath", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _snapfileName);		
					
				} else {
					_fb = new FileBean(_file.getName(), DateUtils
							.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
					fileList.add(_fb);
					retMap.put("spath", "audio");	
				}
				String fileId = IdWorker.getInstance().uuid();
				UploadsDAO uploadDAO = new UploadsDAO();
				uploadDAO.setId(fileId).setName(_file.getName()).setPath(picBaseUrl + _fb.getUrl()).setSpath(picBaseUrl + _fb.getSurl()).set_ext(String.valueOf(_fb.getFileType().ordinal())).setCreate_at(new Date()).add();
				retMap.put("id", fileId);
				//对特殊的标签path图片做覆写，叠加分享二维码
				if(_aiType.equals("face_check")) {
					JSONObject resultJSON = TencentAIService.getInstance().face_check(source.getAbsolutePath(),"1");
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						JSONArray _faceArray = _data.getJSONArray("face_list");
						if(_faceArray!=null && _faceArray.size()==1) {
							_data = _faceArray.getJSONObject(0);
							//int width = _data.get("image_width")
							int expression = _data.getIntValue("expression");
							int age =  _data.getIntValue("age");
							int beauty =  _data.getIntValue("beauty");
							int gender =  _data.getIntValue("gender");
							int glass =  _data.getIntValue("glass");
							
							int roll =  _data.getIntValue("roll");
							int yaw =  _data.getIntValue("yaw");
							
							int pitch =  _data.getIntValue("pitch");
							
							String[] _result = new String[] {
									(glass==0?"不":"") + "带眼镜的您显得更有魅力",
									(expression<10?"平时笑的有点少":(expression>50?"平时多注意保持微笑":"乐观上进阳光")),
									"评测年龄： <b style=\"color:'orange'\">"+age+" 岁</b>",
									"" + (beauty>80?"颜值逆天":"") + " 颜值战胜了全球：  <b style=\"color:'orange'\">"+ beauty + "%</b> 的" + (gender<50?"<b>女性</b>":"<b>男性</b>") 
							}; 
									
							
							String _html = QrCodeTemplateManager.getInstance().genImageStr("年龄检测",_result,uploadUrl + retMap.get("spath"),qrcode);
							Html2Image image = Html2Image.fromHtml(_html);
							image.getImageRenderer().setWidth(420);
							if(!_fileName.toLowerCase().endsWith("png")) {
								image.getImageRenderer().saveImage(_path + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
								retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
							}else {
								image.getImageRenderer().saveImage(_path + "/" + _fileName);
								retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
							}
							
							retMap.put("result","您的评测年龄："+age+"岁，点击上面图片查看更多信息并可进行微信分享");
							
						}else {
							retMap.put("result","没识别到脸或识别到了多张脸，请重新选择照片！");
						}
					}
				}else if(_aiType.equals("ocr_car")) {
					JSONObject resultJSON = TencentAIService.getInstance().ocr_car(source.getAbsolutePath());
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						String _result = "";
						JSONArray tags = _data.getJSONArray("tag_list");
						if(tags!=null && tags.size()>0) {
							for(int i=0;i<tags.size();i++) {
								JSONObject _tag = tags.getJSONObject(i);
								_result += StrUtil.formatNullStr(_tag.get("label_name")) + " 可信度：" + Math.floor(_tag.getFloatValue("label_confd")*100) + "%  ";
							}
						}
						
						String _html = QrCodeTemplateManager.getInstance().genImageStr("汽车识别",new String[] {_result,"拍一拍，识别天下名车~"},uploadUrl + retMap.get("spath"),qrcode);
						Html2Image image = Html2Image.fromHtml(_html);
						image.getImageRenderer().setWidth(420);
						if(!_fileName.toLowerCase().endsWith("png")) {
							image.getImageRenderer().saveImage(_path + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
						}else {
							image.getImageRenderer().saveImage(_path + "/" + _fileName);
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
						}
						
						retMap.put("result",_result +"，点击上面图片进行微信分享");
					}else {
						retMap.put("result","未包含任何汽车！");
					}
				}else if(_aiType.equals("ocr_flower")) {
					JSONObject resultJSON = TencentAIService.getInstance().ocr_flower(source.getAbsolutePath());
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						String _result = "";
						JSONArray tags = _data.getJSONArray("tag_list");
						if(tags!=null && tags.size()>0) {
							for(int i=0;i<tags.size();i++) {
								JSONObject _tag = tags.getJSONObject(i);
								_result += StrUtil.formatNullStr(_tag.get("label_name")) + " 可信度：" + Math.floor(_tag.getFloatValue("label_confd")*100) + "%  ";
							}
						}
						
						String _html = QrCodeTemplateManager.getInstance().genImageStr("花草识别",new String[] {_result,"拍一拍，识别天下名花名草~"},uploadUrl + retMap.get("spath"),qrcode);
						Html2Image image = Html2Image.fromHtml(_html);
						image.getImageRenderer().setWidth(420);
						if(!_fileName.toLowerCase().endsWith("png")) {
							image.getImageRenderer().saveImage(_path + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
						}else {
							image.getImageRenderer().saveImage(_path + "/" + _fileName);
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
						}
						retMap.put("result",_result +"，点击上面图片进行微信分享");
					}else {
						retMap.put("result","没识别到花花草草！");
					}
				}else if(_aiType.equals("ocr_pic_thing")) {
					JSONObject resultJSON = TencentAIService.getInstance().ocr_pic_thing(source.getAbsolutePath(),5);
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						String _result = "";
						JSONArray tags = _data.getJSONArray("object_list");
						if(tags!=null && tags.size()>0) {
							for(int i=0;i<tags.size();i++) {
								JSONObject _tag = tags.getJSONObject(i);
								_result += JobInit.getLibThing(_tag.getString("label_id")) + " 可信度：" + Math.floor(_tag.getFloatValue("label_confd")*100) + "%  ";
							}
						}
						
						String _html = QrCodeTemplateManager.getInstance().genImageStr("物件识别",new String[] {_result,"拍一拍，识别各种物件，多达1000+智能识别~"},uploadUrl + retMap.get("spath"),qrcode);
						Html2Image image = Html2Image.fromHtml(_html);
						image.getImageRenderer().setWidth(420);
						if(!_fileName.toLowerCase().endsWith("png")) {
							image.getImageRenderer().saveImage(_path + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName.substring(0,_fileName.lastIndexOf("."))+".png");
						}else {
							image.getImageRenderer().saveImage(_path + "/" + _fileName);
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
						}
						retMap.put("result",_result +"，点击上面图片进行微信分享");
					}else {
						retMap.put("result","，没识别到任何物件！");
					}
				}else if(_aiType.equals("ptu_datoutie")) {
					JSONObject resultJSON = TencentAIService.getInstance().ptu_datoutie(source.getAbsolutePath(),StrUtil.formatNullStr(param.get("ta"), "3"));
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						ImageUtils.generateImage(StrUtil.formatNullStr(_data.get("image")), _path+"/" + _fileName);
						try {
							String _snapfileName = IdWorker.getInstance().uuid()
									+ "_q"
									+ _file.getName().substring(
											_file.getName().lastIndexOf("."));
							ImageUtils.download(qrcode, _path +"/" + _snapfileName);
							ImageUtils.addImageWeatermark(_path+"/" + _fileName, _path +"/" + _snapfileName, 10, 10, 1f);
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
							retMap.put("result","点击上面图片查看效果");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else {
						retMap.put("result","，没识别到具有颜值的脸！");
					}
				}else if(_aiType.equals("ptu_face")) {
					JSONObject resultJSON = TencentAIService.getInstance().ptu_face(source.getAbsolutePath(),StrUtil.formatNullStr(param.get("ta"), "3"));
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						ImageUtils.generateImage(StrUtil.formatNullStr(_data.get("image")), _path+"/" + _fileName);
						try {
							String _snapfileName = IdWorker.getInstance().uuid()
									+ "_q"
									+ _file.getName().substring(
											_file.getName().lastIndexOf("."));
							ImageUtils.download(qrcode, _path +"/" + _snapfileName);
							ImageUtils.addImageWeatermark(_path+"/" + _fileName, _path +"/" + _snapfileName, 10, 10, 1f);
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
							retMap.put("result","点击上面图片查看效果");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						retMap.put("result","，没识别到具有颜值的脸！");
					}
				}else if(_aiType.equals("ptu_huazhuang")) {
					JSONObject resultJSON = TencentAIService.getInstance().ptu_huazhuang(source.getAbsolutePath(),StrUtil.formatNullStr(param.get("ta"), "3"));
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						ImageUtils.generateImage(StrUtil.formatNullStr(_data.get("image")), _path+"/" + _fileName);
						try {
							String _snapfileName = IdWorker.getInstance().uuid()
									+ "_q"
									+ _file.getName().substring(
											_file.getName().lastIndexOf("."));
							ImageUtils.download(qrcode, _path +"/" + _snapfileName);
							ImageUtils.addImageWeatermark(_path+"/" + _fileName, _path +"/" + _snapfileName, 10, 10, 1f);
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
							retMap.put("result","点击上面图片查看效果");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						retMap.put("result","，没识别到具有颜值的脸！");
					}
				}else if(_aiType.equals("ptu_yanzhi")) {
					JSONObject resultJSON = TencentAIService.getInstance().ptu_yanzhi(source.getAbsolutePath());
					if(resultJSON!=null && resultJSON.getInteger("ret")==0) {
						JSONObject _data = resultJSON.getJSONObject("data");
						ImageUtils.generateImage(StrUtil.formatNullStr(_data.get("image")), _path+"/" + _fileName);
						try {
							String _snapfileName = IdWorker.getInstance().uuid()
									+ "_q"
									+ _file.getName().substring(
											_file.getName().lastIndexOf("."));
							ImageUtils.download(qrcode, _path +"/" + _snapfileName);
							ImageUtils.addImageWeatermark(_path+"/" + _fileName, _path +"/" + _snapfileName, 10, 10, 1f);
							retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
							retMap.put("result","点击上面图片查看效果");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						retMap.put("result","，没识别到具有颜值的脸！");
					}
				}else {
					if(!StrUtil.formatNullStr(retMap.get("result")).startsWith("data:image/jpeg;base64")) {
						retMap.put("result","请重新选择照片！");
					}
					retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
				}
			}else {
				if (_file.getName().toLowerCase().endsWith(".png")
						|| _file.getName().toLowerCase().endsWith(".jpg")
						|| _file.getName().toLowerCase().endsWith(".jpeg")) { // 生成缩略图
					String _snapfileName = IdWorker.getInstance().uuid()
							+ "_s"
							+ _file.getName().substring(
									_file.getName().lastIndexOf("."));
					ImageUtils.createPreviewImage(source.getAbsolutePath(),
							_path + "/" + _snapfileName);
					_fb = new FileBean(_file.getName(), DateUtils
							.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName,
							DateUtils.getCurrentToDay("yyyy/MM/dd") + "/"
									+ _snapfileName);
					fileList.add(_fb);
					retMap.put("spath", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _snapfileName);		
				} else {
					_fb = new FileBean(_file.getName(), DateUtils
							.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
					fileList.add(_fb);
					retMap.put("spath", "audio");	
				}
				String fileId = IdWorker.getInstance().uuid();
				UploadsDAO uploadDAO = new UploadsDAO();
				uploadDAO.setId(fileId).setName(_file.getName()).setPath(picBaseUrl + _fb.getUrl()).setSpath(picBaseUrl + _fb.getSurl()).set_ext(String.valueOf(_fb.getFileType().ordinal())).setCreate_at(new Date()).add();
				retMap.put("id", fileId);
				
				retMap.put("path", DateUtils.getCurrentToDay("yyyy/MM/dd") + "/" + _fileName);
				retMap.put("res", true);
			}
				
				retMap.put("res", true);
			}
		}
		if(retMap!=null)
			printOut2(response, request, JSON.toJSONString(retMap));
	}
	
	private UserBean getWxUser(String token){
		UserBean ub = UserService.getInstance().getUserByToken(token);
		if(ub == null) return null;
		return ub;
	}
	
	
	
	private void printOut2(HttpServletResponse response,HttpServletRequest request,Object object)
	{
		PrintWriter out = null;
		try {
			 out = getPrintWriter(response,ContentType.JSON,Encode.UTF8);
			 String callback = StrUtil.formatNullStr(request.getParameter("callback"));
			 if (!callback.equals(""))
				out.println(callback + "(" + object + ")");
			 else
				out.println(object);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null) {
				out.flush();
			    out.close();
			}
		}
	}
}
