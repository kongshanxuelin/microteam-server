package com.sumslack.web.working.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.app.jd.front.tool.ImageUtils;
import com.sumslack.app.jd.front.tool.PayUtil;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.HttpUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class TencentAIService {
	public static TencentAIService instance = new TencentAIService();
	public static TencentAIService getInstance() {
		if(instance == null) {
			instance = new TencentAIService();
		}
		return instance;
	}
	
	private TencentAIService() {}
	
	private static final String appId = StrUtil.formatNullStr(TagConst.globalMap.get("ai.appid"));
	private static final String appKey = StrUtil.formatNullStr(TagConst.globalMap.get("ai.appkey"));
	
	
	/**
	 * @param url 腾讯post API Restful地址
	 * @param params 接口的请求参数列表，只需要填入API参数即可
	 * @return
	 */
	public JSONObject post(String url,Map<String,String> params) {
		if(!params.containsKey("app_id")) {
			params.put("app_id", appId);
		}
		if(!params.containsKey("time_stamp")) {
			long time_stamp = System.currentTimeMillis()/1000;
			params.put("time_stamp", time_stamp+"");
		}
		if(!params.containsKey("nonce_str")) {
			String nonce_str = IdWorker.getInstance().uuid();
			params.put("nonce_str", nonce_str);
		}
		if(!params.containsKey("sign") || StrUtil.formatNullStr(params.get("sign")).equals("")) {
			Map<String, String> sPara = PayUtil.paraFilter(params);
	        String prestr = PayUtil.createLinkString(sPara);
	        String key = "&app_key="+appKey; 
	        String mysign = sign(prestr, key, "utf-8").toUpperCase();
	        params.put("sign", mysign);
		}
		String paramStr = createLinkString(params);
		Map<String,String> headers = new HashMap();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		String json = HttpUtils.post(url,null, paramStr,headers);
		return JSON.parseObject(json);
	}
	

	public JSONObject get(String url,Map<String,String> params) {
		if(!params.containsKey("app_id")) {
			params.put("app_id", appId);
		}
		if(!params.containsKey("time_stamp")) {
			long time_stamp = System.currentTimeMillis()/1000;
			params.put("time_stamp", time_stamp+"");
		}
		if(!params.containsKey("nonce_str")) {
			String nonce_str = IdWorker.getInstance().uuid();
			params.put("nonce_str", nonce_str);
		}
		if(!params.containsKey("sign") || StrUtil.formatNullStr(params.get("sign")).equals("")) {
			Map<String, String> sPara = PayUtil.paraFilter(params);
	        String prestr = PayUtil.createLinkString(sPara);
	        String key = "&app_key="+appKey; 
	        String mysign = sign(prestr, key, "utf-8").toUpperCase();
	        params.put("sign", mysign);
		}
		//String paramStr = createLinkString(params);
		Map<String,String> headers = new HashMap();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		String json = HttpUtils.get(url, params,headers);
		return JSON.parseObject(json);
	}
	/**
	 * 场景识别	对图片进行场景识别，快速找出图片中包含的场景信息
	 * @param path 图片路径
	 * @param topk 置信度物体返回数
	 * @return
	 */
	public JSONObject ocr_pic_scene(String path,int topk){
		String url = "https://api.ai.qq.com/fcgi-bin/vision/vision_scener";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("format", "1");
		params.put("topk", ""+topk);
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 物体识别	对图片进行物体识别，快速找出图片中包含的物体信息
	 * @param path 图片路径
	 * @param topk 置信度物体返回数
	 * @return
	 */
	public JSONObject ocr_pic_thing(String path,int topk){
		String url = "https://api.ai.qq.com/fcgi-bin/vision/vision_objectr";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("format", "1");
		params.put("topk", ""+topk);
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 美食判断
	 * @param path
	 * @return
	 */
	public JSONObject ocr_isfood(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/image/image_food";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 花草识别
	 * @param path
	 * @return
	 */
	public JSONObject ocr_flower(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/vision/vision_imgidentify";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("scene", "2");
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 汽车识别
	 * @param path
	 * @return
	 */
	public JSONObject ocr_car(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/vision/vision_imgidentify";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("scene", "1");
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	
	/**
	 * 人脸融合
	 * @param path
	 * @return
	 */
	public JSONObject ptu_face(String path,String model) {
		String url = "https://api.ai.qq.com/fcgi-bin/ptu/ptu_facemerge";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("model", model);
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 化妆
	 * @param path
	 * @param cosmetic
	 * @return
	 */
	public JSONObject ptu_huazhuang(String path,String cosmetic) {
		String url = "https://api.ai.qq.com/fcgi-bin/ptu/ptu_facecosmetic";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("cosmetic", cosmetic);
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 颜值检测
	 * @param path
	 * @return
	 */
	public JSONObject ptu_yanzhi(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/ptu/ptu_faceage";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 大头贴
	 * @param path
	 * @return
	 */
	public JSONObject ptu_datoutie(String path,String sticker) {
		String url = "https://api.ai.qq.com/fcgi-bin/ptu/ptu_facesticker";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("sticker", sticker);
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 人脸检测
	 * @param path
	 * @param mode 检测模式，0-正常，1-大脸模式（默认1）
	 * @return
	 */
	public JSONObject face_check(String path,String mode) {
		String url = "https://api.ai.qq.com/fcgi-bin/face/face_detectface";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("mode", mode);
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 人脸对比
	 * @return
	 */
	public JSONObject face_compare(String path,String path2) {
		String url = "https://api.ai.qq.com/fcgi-bin/face/face_facecompare";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		String image2 = ImageUtils.encodeImage(new File(path2));
		params.put("image_a", image);
		params.put("image_b", image2);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 跨年龄段相似度对比
	 * @param path
	 * @param path2
	 * @return
	 */
	public JSONObject face_compare_age(String path,String path2) {
		String url = "https://api.ai.qq.com/fcgi-bin/face/face_detectcrossageface";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		String image2 = ImageUtils.encodeImage(new File(path2));
		params.put("source_image", image);
		params.put("target_image", image2);
		JSONObject json = post(url,params);
		return json;
	}
	
	public JSONObject ocr(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_generalocr";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImageNoEncoder(new File(path));
		System.out.println("image:"+image);
		params.put("image", image);
		JSONObject json = null;
		try {
			json = post(url,params);
			params.put("json:", JSON.toJSONString(json));
		}catch(Exception ex) {
			try {
				params.put("image_file", image);
				json = post("https://ai.qq.com/cgi-bin/appdemo_generalocr",params);
			}catch(Exception ex2) {
				ex2.printStackTrace();
			}
		}
		return json;
	}
	
	public JSONObject tts_ai(String speaker,String text) {
		String url = "https://api.ai.qq.com/fcgi-bin/aai/aai_tts";
		Map<String,String> params = new HashMap();
		//普通话男声	1 静琪女声	5 欢馨女声	6 碧萱女声	7
		params.put("speaker", speaker);
		//PCM	1WAV	2 MP3	3
		params.put("format", "3");
		params.put("volume", "0");
		params.put("speed", "100");
		params.put("text", text);
		params.put("aht", "0");
		params.put("apc", "58");
		JSONObject json = post(url,params);
		return json;
	}
	
	public JSONObject tts_yt(String speaker,String text) {
		String url = "https://api.ai.qq.com/fcgi-bin/aai/aai_tta";
		Map<String,String> params = new HashMap();
		//女生	0女生纯英文	1男生	2 喜道公子	6
		params.put("model_type", speaker);
		params.put("speed", "0");
		params.put("text", text);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 身份证识别,card_type，0：正面 1：反面
	 * @param path
	 * @return
	 */
	public JSONObject ocr_id(String path,String card_type) {
		String url = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_idcardocr";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("image", image);
		params.put("card_type", card_type);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 名片识别
	 * @param path
	 * @param card_type
	 * @return
	 */
	public JSONObject ocr_card(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_bcocr";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 行驶证驾驶证OCR识别,0-行驶证识别，1-驾驶证识别
	 * @param path
	 * @param type
	 * @return
	 */
	public JSONObject ocr_car(String path,String type) {
		String url = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_driverlicenseocr";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("image", image);
		params.put("type", type);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 营业执照识别
	 * @param path
	 * @return
	 */
	public JSONObject ocr_biz(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_bizlicenseocr";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 银行卡识别
	 * @param path
	 * @param card_type
	 * @return
	 */
	public JSONObject ocr_bank(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_creditcardocr";
		Map<String,String> params = new HashMap();
		String image = ImageUtils.encodeImage(new File(path));
		params.put("image", image);
		JSONObject json = post(url,params);
		return json;
	}
	
	/**
	 * 语音识别30s内
	 * @param path
	 * @return
	 */
	public JSONObject aai(String path) {
		String url = "https://api.ai.qq.com/fcgi-bin/aai/aai_asr";
		Map<String,String> params = new HashMap();
		String speech = ImageUtils.encodeImage(new File(path));
		//PCM	1 WAV	2 AMR	3 SILK	4
		params.put("format", "2");
		params.put("speech", speech);
		//8KHz	8000 16KHz	16000
		params.put("rate", "16000");
		JSONObject json = post(url,params);
		return json;
	}
	
	
    private String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    private byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        try {
	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i);
	            String value = params.get(key);
	            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
	                prestr = prestr + key + "=" + URLEncoder.encode(value,"UTF-8");
	            } else {
	                prestr = prestr + key + "=" + URLEncoder.encode(value,"UTF-8") + "&";
	            }
	        }
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
        return prestr;
    }
	
    public static void printJSON(JSONObject json,String tag) {
    	if(json!=null) {
    		System.out.println("**"+tag+"**："+json.toJSONString());
    	}else {
    		System.out.println("**"+tag+"**："+"json is null");
    	}
    }
    
    public static List getSelectValue(String action) {
    	List retList = new ArrayList();
    	
		try {
			if(action.equals("ptu_datoutie")) {
				retList = TagJDBCInstance.getInstance().queryList("select id,label,code from ai_tencent_libs where t=?", new Object[] {"ptu.dtt"});
			}else if(action.equals("ptu_huazhuang")) {
				retList = TagJDBCInstance.getInstance().queryList("select id,label,code from ai_tencent_libs where t=?", new Object[] {"ptu.hz"});
			}else if(action.equals("ptu_face")) {
				retList = TagJDBCInstance.getInstance().queryList("select id,label,code from ai_tencent_libs where t=?", new Object[] {"ptu.rh"});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return retList;
    }
    
    
    public Map genImage(File file,String destPath,JSONArray result) {
		String s = file.getAbsolutePath();
		return genImage(s,destPath,result);
		
	}
	public Map genImage(String path,String destPath,JSONArray result)  {
		Map resultMap = new HashMap();
		resultMap.put("ret",false);
		FileOutputStream out = null;
		try {
			StringBuffer sb = new StringBuffer();
			InputStream in = new FileInputStream(path);//图片路径
	        BufferedImage image = ImageIO.read(new File(path));
	        Graphics2D g = (Graphics2D)image.getGraphics();
	        for(int i=0;i<result.size();i++) {
	        	JSONObject o = JSONObject.parseObject(result.get(i).toString());
	        	if(o.containsKey("itemcoord")) {
	        		JSONArray rect = o.getJSONArray("itemcoord");
	        		String _words = StrUtil.formatNullStr(o.get("itemstring"));
		        	sb.append(_words);
		        	for(int j=0;j<rect.size();j++) {
		        		JSONObject _rect = rect.getJSONObject(j);
			        	g.setColor(Color.RED);//画笔颜色
			        	g.setStroke(new BasicStroke(2.0f));
			        	//宽度是识别到的连续数字
			        	//子串在整个字符串中的开始位置
			        	//int startNum = _words.indexOf(bondid);
			        	int left = _rect.getIntValue("x");
			        	int _y = _rect.getIntValue("y"); 
			        	//int w = bondid.length()*16;
			        	int w = _rect.getIntValue("width");
		//	        	if(startNum>0) {
		//	        		left += startNum*3; 
		//	        	}
			            g.drawRect(left, _y, w, _rect.getIntValue("height"));
		        	}
	        	}else {
	        		JSONObject rect  = o.getJSONObject("location");
	        		String _words = StrUtil.formatNullStr(o.get("words"));
		        	sb.append(_words);
		        	
		        	g.setColor(Color.RED);//画笔颜色
		        	g.setStroke(new BasicStroke(2.0f));
		        	int left = rect.getIntValue("left");
		        	int _y = rect.getIntValue("top");
		        	int w = rect.getIntValue("width");
		            g.drawRect(left, _y, w, rect.getIntValue("height"));
	        	}
	        }
	        
	       
	        out = new FileOutputStream(destPath);//输出图片的地址
	        if(path.toLowerCase().endsWith(".png"))
	        	ImageIO.write(image, "png", out);
	        else
	        	ImageIO.write(image, "jpeg", out);
	        resultMap.put("t", sb.toString());
	        resultMap.put("ret",true);
	        return resultMap;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultMap;
	}
    

    public static void main(String[] args) {
		try {
			JSONObject json = null;
//			json = TencentAIService.getInstance().ocr_pic_scene("f:\\tencent-ai\\demo2-436b21999e.jpg",3);
//			printJSON(json,"物体场景识别");
//			json = TencentAIService.getInstance().ocr_pic_thing("f:\\tencent-ai\\demo2-436b21999e.jpg",3);
//			printJSON(json,"物体识别");
//			json = TencentAIService.getInstance().ocr_isfood("f:\\tencent-ai\\demo2-436b21999e.jpg");
//			printJSON(json,"美食识别");

//			json = TencentAIService.getInstance().ocr_flower("f:\\tencent-ai\\plant-7.jpg");
//			printJSON(json,"花草识别");
			
//			json = TencentAIService.getInstance().ocr_car("f:\\tencent-ai\\car-7.jpg");
//			printJSON(json,"汽车识别");
			
//			json = TencentAIService.getInstance().ptu_face("f:\\tencent-ai\\face.jpg","3");
//			printJSON(json,"人脸融合");
			
//			json = TencentAIService.getInstance().ptu_yanzhi("f:\\tencent-ai\\face.jpg");
//			printJSON(json,"颜值检测");
			
//			json = TencentAIService.getInstance().face_check("f:\\tencent-ai\\face.jpg","1");
//			printJSON(json,"人脸检测");

//			json = TencentAIService.getInstance().face_compare_age("f:\\tencent-ai\\face.jpg","f:\\tencent-ai\\face2.jpg");
//			printJSON(json,"人脸检测");

			json = TencentAIService.getInstance().ocr("f:\\tencent-ai\\2222.png");
			printJSON(json,"OCR");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
