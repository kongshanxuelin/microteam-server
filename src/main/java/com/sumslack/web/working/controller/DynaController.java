package com.sumslack.web.working.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.ContentTypeJSON;
import com.sumslack.jsptagex.anno.rest.Post;
import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.bean.TagPage;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.util.HttpUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.bean.request.DynaPubRequest;
import com.sumslack.web.working.dao.M_logsDAO;
import com.sumslack.web.working.dao.M_quan_commentsDAO;
import com.sumslack.web.working.dao.M_quan_likeDAO;
import com.sumslack.web.working.dao.Uploads_relDAO;
import com.sumslack.web.working.service.LogService;

@URIAlias("wx/dyna")
public class DynaController extends CController{
	@Post
	@ContentTypeJSON(type=DynaPubRequest.class)
	public Map publish(final DynaPubRequest req){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser(req.getToken());
		if(ub == null) return retMap;
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				M_logsDAO dyna = new M_logsDAO();
				String _id = IdWorker.getInstance().uuid();
				dyna.setId(_id);
				dyna.setUid(ub.getUid());
				dyna.setTitle(req.getContent());
				dyna.setDt(new Date());
				dyna.setTeamid(ub.getTeamId());
				dyna.setAddr(req.getAddr());
				dyna.setScope(req.getScope());
				dyna.setStype("q");
				res = res && dyna.add(conn);
				String fileIdStr = StrUtil.formatNullStr(req.getFileIds());
				if(!fileIdStr.equals("")){
					String[] fileIdArray = StrUtil.split(fileIdStr, ",");
					for(String _fileId : fileIdArray){
						Uploads_relDAO u = new Uploads_relDAO();
						u.setId(IdWorker.getInstance().uuid());
						u.setObjType("dyna");
						u.setObjId(_id);
						u.setUploadId(_fileId);
						u.setDt(new Date());
						res = res && u.add(conn);
					}
				}
				return res;
			}
		});
		retMap.put("ret", res);
		return retMap;
	}
	
	public Map remove(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		M_logsDAO log = M_logsDAO.dao.findById("*", id);
		if(log!=null){
			boolean res = log.delete();
			retMap.put("ret", res);
		}
		return retMap;
	}
	public Map mylist(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		int p = StrUtil.formatNullStrInt(request.getParameter("p"), 1);
		TagPage page = new TagPage();
		TagPage.init(page, p , 10);
		
		M_logsDAO.dao.findPage("select id from " + M_logsDAO.dao.getTable().getName() + 
				" where teamid=? order by dt desc", page, ub.getTeamId());
		List<M_logsDAO> logList = page.getResult();
		if(logList!=null){
			List resultList = new ArrayList();
			for(M_logsDAO l : logList){
				resultList.add(LogService.getInstance().getLog(l.getId()));
			}
			page.setResult(resultList);
		}
		retMap.put("ret", true);
		retMap.put("page", page);
		return retMap;
	}
	
	public Map like(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		String sts = StrUtil.formatNullStr(request.getParameter("sts"));
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		if(sts.equals("like")){
			M_quan_likeDAO quanLike = new M_quan_likeDAO();
			quanLike.setId(IdWorker.getInstance().uuid());
			quanLike.setUid(ub.getUid());
			quanLike.setFid(id);
			quanLike.setCreate_time(new Date());
			quanLike.setCreate_uid(ub.getUid());
			quanLike.setIp(HttpUtils.getIP(request));
			quanLike.setPlatform_id("w");
			quanLike.setDelflag("0");
			quanLike.setNick(ub.getNick());
			boolean res = quanLike.add();
			retMap.put("ret", res);
			retMap.put("like", quanLike);
		}else if(sts.equals("unlike")){
			M_quan_likeDAO quanLike = M_quan_likeDAO.dao.findById("*", id);
			if(quanLike!=null){
				boolean res = quanLike.delete();
				retMap.put("ret", res);
			}
		}
		return retMap;
	}
	
	public Map addComment(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		String content = StrUtil.formatNullStr(request.getParameter("content"));
		
		String isReply = StrUtil.formatNullStr(request.getParameter("isReply"));
		
		M_quan_commentsDAO qc = new M_quan_commentsDAO();
		qc.setId(IdWorker.getInstance().uuid());
		qc.setFid(id);
		qc.setComment_content(content);
		qc.setCreate_time(new Date());
		qc.setIp(HttpUtils.getIP(request));
		if(isReply.equals("1")){
			qc.setReply_uid(ub.getUid());
			qc.setReply_nick(ub.getNick());
		}else{
			qc.setCreate_uid(ub.getUid());
			qc.setCreate_nick(ub.getNick());
		}
		qc.setDelflag("0");
		boolean res = qc.add();
		retMap.put("ret", res);
		if(res){
			retMap.put("comment", qc);
		}
		return retMap;
	}
	
	public Map removeComment(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		M_quan_commentsDAO c = M_quan_commentsDAO.dao.findById("*", id);
		boolean res = c.delete();
		retMap.put("ret", res);
		return retMap;
	}
}
