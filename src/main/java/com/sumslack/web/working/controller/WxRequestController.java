package com.sumslack.web.working.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.jsptagex.anno.rest.ContentTypeJSON;
import com.sumslack.jsptagex.anno.rest.Post;
import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.HttpUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.jsptagex.web.servlet.BaseController;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_tmpl_recordsDAO;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.service.TmplService;
import com.sumslack.web.working.servlet.QrCodeLoginServlet;
//处理微信过来的数据请求
@URIAlias("wx")
public class WxRequestController extends BaseController{
	private static String ACTION = "act";
	
	public JSONObject net(){
		Map paramMap = new HashMap();
		String url = StrUtil.formatNullStr(TagConst.globalMap.get("net.url"));
		paramMap.put("ds-name", StrUtil.formatNullStr(request.getParameter("ds-name")));
		paramMap.put("ps", "20");
		paramMap.put("id",StrUtil.formatNullStr(request.getParameter("id")));
		paramMap.put("type", "json");
		paramMap.put("start", StrUtil.formatNullStr(request.getParameter("start")));
		String retStr = HttpUtils.get(url, paramMap);
		return JSON.parseObject(retStr);
	}
	
	@Post
	@ContentTypeJSON(type=JSONObject.class)
	public Map request(JSONObject arg){
		Map retMap = new HashMap();
		Map argMap = getParamMap();
		if(argMap!=null){
			String argStr = StrUtil.formatNullStr(argMap.get("arg"));
			System.out.println("******argStr:"+argStr);
			if(!argStr.equals("")){
				JSONObject argJson = JSON.parseObject(argStr);
				if(argJson!=null){
					if(StrUtil.formatNullStr(argJson.getString(ACTION)).equals("tmpl.get")){
						return getTmplGet(argJson);
					}else if(StrUtil.formatNullStr(argJson.getString(ACTION)).equals("tmpl.data.add")){
						return addTmplData(argJson);
					}else if(StrUtil.formatNullStr(argJson.getString(ACTION)).equals("tmpl.mylist")){
						String pid = StrUtil.formatNullStr(argJson.getString("pid"));
						List list = TmplService.getInstance().getMyTmplList(getUserBean(argJson), pid);
						retMap.put("list",list);
						return retMap;
					}else if(StrUtil.formatNullStr(argJson.getString(ACTION)).equals("login")){
						UserBean ub = getUserBean(argJson);
						if(ub!=null){
							UsersDAO user = UsersDAO.dao.findFirst("select * from " + UsersDAO.dao.getTable().getName() + " where username=?",ub.getUid());
							user.setToken(IdWorker.getInstance().uuid());
							user.save();
							String loginId = argJson.getString("id");
							QrCodeLoginServlet.qrcodeMap.put(loginId, ub);
							retMap.put("ret", true);
							return retMap; 
						}else{
							retMap.put("ret", false);
							return retMap; 
						}
					}else if(StrUtil.formatNullStr(argJson.getString(ACTION)).equals("tmpl.myform.data")){
						String tmp_id_id = StrUtil.formatNullStr(argJson.getString("id"));
						M_work_tmpl_recordsDAO tmpl = M_work_tmpl_recordsDAO.dao.findById("*", tmp_id_id);
						if(tmpl!=null){
							Map dataMap = TmplService.getInstance().getTmplDdata(tmpl.getTmpl_id(), TmplService.getInstance().getData(tmp_id_id));
							retMap.put("tmp_id_id", tmp_id_id);
							retMap.put("ret", true);
							retMap.put("result", dataMap);
						}else{
							retMap.put("ret", false);
						}
						return retMap;
					}else if(StrUtil.formatNullStr(argJson.getString(ACTION)).equals("tmpl.myform.data.exist")){
						UserBean ub = getUserBean(argJson);
						String tmplId = StrUtil.formatNullStr(argJson.getString("tmplid"));
						String recId = TmplService.getInstance().dataOneRecId(ub, tmplId);
						if(!StrUtil.formatNullStr(recId).equals("")){
							retMap.put("ret", true);
							retMap.put("id", recId);
						}else{
							retMap.put("ret", false);
						}
						return retMap;
					}else if(StrUtil.formatNullStr(argJson.getString(ACTION)).equals("tmpl.myform.data.save")){
						String tmplId = StrUtil.formatNullStr(argJson.getString("tmplId"));
						String tmpl_id_id = StrUtil.formatNullStr(argJson.getString("tmplIdId"));
						boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
							@Override
							public boolean run(Connection conn) throws SQLException {
								String _tmplId = tmplId;
								M_work_tmpl_recordsDAO d = M_work_tmpl_recordsDAO.dao.findById("*", tmpl_id_id);
								if(d!=null){
									_tmplId = d.getTmpl_id();
								}
								return TmplService.getInstance().setDataSave(conn,argJson,_tmplId, tmpl_id_id);
							}
						});
						retMap.put("ret", res);
						return retMap;
					}
				}
			}
		}
		return retMap;
	}
	
	
	private Map getTmplGet(JSONObject argJson){
		return TmplService.getInstance().getTmpl(StrUtil.formatNullStr(argJson.getString("tmplid")));
	}
	
	private Map addTmplData(JSONObject argJson){
		return TmplService.getInstance().dataAdd(argJson, getUserBean(argJson), StrUtil.formatNullStr(argJson.getString("tmplid")), StrUtil.formatNullStr(argJson.getString("recid")));
	}
	
	private UserBean getUserBean(JSONObject argJson){
		JSONObject user = argJson.getJSONObject("user");
		if(user!=null){
			UserBean ub = new UserBean();
			ub.setUid(user.getString("uid"));
			ub.setNick(user.getString("nick"));
			return ub;
		}
		return null;
	}
}
