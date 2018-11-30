package com.sumslack.web.working.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.LogBean;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_logsDAO;
import com.sumslack.web.working.dao.M_quan_commentsDAO;
import com.sumslack.web.working.dao.M_quan_likeDAO;
import com.sumslack.web.working.dao.UploadsDAO;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.jobs.JobInit;

public class LogService {
	private static int BATCHSIZE = 1;
	private static List<LogBean> logs = new ArrayList();
	
	private static LogService instance = new LogService();
	private LogService(){
		
	}
	public synchronized static LogService getInstance(){
		if(instance == null){
			instance = new LogService();
		}
		return instance;
	}
	
	public void addLog(HttpServletRequest req){
		if(JobInit.getUriTitle(req.getRequestURI()) == null) return;
		
		String token = StrUtil.formatNullStr(req.getParameter("token"));
		UserBean ub = null;
		if(token.equals("")){
			ub = (UserBean)req.getSession().getAttribute("user");
		}else{
			ub = UserService.getInstance().getUserByToken(token);
		}
		if(ub==null) return ;
		
		LogBean lb = new LogBean();
		lb.setId(IdWorker.getInstance().uuid());
		lb.setUid(ub.getUid());
		lb.setTeamid(ub.getTeamId());
		lb.setTitle(JobInit.getUriTitle(req.getRequestURI()));
		lb.setParams(req.getQueryString());
		lb.setDt(new Date());
		logs.add(lb);
		
		if(logs.size()>=BATCHSIZE){
			Object[][] oo = new Object[logs.size()][5];
			for(int i=logs.size()-1;i>=0;i--){
				LogBean _lb = logs.get(i);	
				oo[i][0] = IdWorker.getInstance().uuid();
				oo[i][1] = ub.getUid();
				oo[i][2] = _lb.getTitle();
				oo[i][3] = _lb.getParams();
				oo[i][4] = DateUtils.format(new Date(),DateUtils.fmt_datetimeFormat);
				oo[i][5] = _lb.getTeamid();
				logs.remove(i);
			}
			TagJDBCInstance.getInstance().batch("insert into m_logs(id,uid,title,params,dt,teamid)values(?,?,?,?,?,?)",oo);
		}
	}
	
	public Map getLog(String logId){
		M_logsDAO log = M_logsDAO.dao.findById("*", logId);
		if(log!=null){
			Map retMap = new HashMap();
			retMap.put("id", log.getId());
			retMap.put("uid", log.getUid());
			UsersDAO _user = UserService.getInstance().getUserByUid(log.getUid());
			if(_user!=null){
				retMap.put("openid", _user.getUsername());
				retMap.put("nick", _user.getNick());
				retMap.put("avatar", _user.getAvatar());
			}
			retMap.put("content", log.getTitle());
			retMap.put("dt", log.getDt().getTime());
			retMap.put("addr", log.getAddr());
			retMap.put("scope", log.getScope());
			List<UploadsDAO> imageList = UploadsDAO.dao.find("select id,path,spath from "+ UploadsDAO.dao.getTable().getName() + " where id in (select uploadId from uploads_rel where objType='dyna' and objId=?)", log.getId());
			retMap.put("imageList", imageList);
			
			//获取点赞列表
			List<M_quan_likeDAO> likeList = M_quan_likeDAO.dao.find("select id,uid,nick from " + M_quan_likeDAO.dao.getTable().getName() + " where fid=?", logId);
			if(likeList!=null)
				retMap.put("likes", likeList);
			//获取评论列表
			List<M_quan_commentsDAO> commentList = M_quan_commentsDAO.dao.find("select id,create_uid,create_nick,reply_uid,reply_nick,comment_content from " + 
					M_quan_commentsDAO.dao.getTable().getName() + " where fid=? order by create_time",logId);
			if(commentList!=null)
				retMap.put("comments", commentList);
			return retMap;
		}
		return null;
	}
	
	private static Map<String,String> uri2title = new HashMap();
	
}
