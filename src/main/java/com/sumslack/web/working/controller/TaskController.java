package com.sumslack.web.working.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.bean.TagPage;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_project_tasksDAO;
import com.sumslack.web.working.dao.M_work_project_tasks_commentsDAO;
import com.sumslack.web.working.dao.M_work_project_usersDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fieldsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.dao.M_work_tmpl_recordsDAO;
import com.sumslack.web.working.service.CommentService;
import com.sumslack.web.working.service.ProjectService;
import com.sumslack.web.working.service.TaskService;
import com.sumslack.web.working.service.TmplService;
import com.sun.jmx.snmp.tasks.TaskServer;

@URIAlias("task")
public class TaskController extends CController{
	public Map list() throws Exception{
		String proId = StrUtil.formatNullStr(request.getParameter("proid"));
		
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		String sts = StrUtil.formatNullStr(request.getParameter("sts"));
		int p = StrUtil.formatNullStrInt(request.getParameter("p"), 1);
		TagPage page = new TagPage();
		TagPage.init(page, p,20);
		
		Map retMap = new HashMap();
		retMap.put("proid", proId);
		
		List retList = new ArrayList();
		if(sts.equals("")){
			if(proId.equals("")){
				M_work_project_tasksDAO.dao.findPage("select * from " + M_work_project_tasksDAO.dao.getTable().getName() + " where delflag<>'1'"
						+ " and (create_uid=? or admin_uid=? or FIND_IN_SET(?,task_joiner) or FIND_IN_SET(?,task_auditer)) order by ord_,create_time", page,ub.getUid(),ub.getUid(),ub.getUid(),ub.getUid());				
			}else{
				M_work_project_tasksDAO.dao.findPage("select * from " + M_work_project_tasksDAO.dao.getTable().getName() + " where project_id=? and delflag<>'1'"
						+ "  and (create_uid=? or admin_uid=? or FIND_IN_SET(?,task_joiner) or FIND_IN_SET(?,task_auditer)) order by ord_,create_time", page,proId,ub.getUid(),ub.getUid(),ub.getUid(),ub.getUid());
			}
		}else{
			if(proId.equals("")){
				M_work_project_tasksDAO.dao.findPage("select * from " + M_work_project_tasksDAO.dao.getTable().getName() + " where sts=? and "
						+ "(create_uid=? or admin_uid=? or FIND_IN_SET(?,task_joiner) or FIND_IN_SET(?,task_auditer)) "
						+ "and delflag<>'1' order by ord_,create_time", page, sts,ub.getUid(),ub.getUid(),ub.getUid(),ub.getUid());
			}else{
				M_work_project_tasksDAO.dao.findPage("select * from " + M_work_project_tasksDAO.dao.getTable().getName() + " where project_id=? and sts=? and "
						+ "(create_uid=? or admin_uid=? or FIND_IN_SET(?,task_joiner) or FIND_IN_SET(?,task_auditer))  and delflag<>'1' order by ord_,create_time", 
						page,proId , sts,ub.getUid(),ub.getUid(),ub.getUid(),ub.getUid());
			}
		}
		if(page.getResult()!=null && page.getResult().size()>0){
			for(Object t2 : page.getResult()){
				M_work_project_tasksDAO t = (M_work_project_tasksDAO)t2;
				retList.add(ProjectService.getInstance().getTask(ub, t.getId()));
			}
		}
		page.setResult(retList);
		retMap.put("page", page);
		//retMap.put("result", retList);
		return retMap;
	}
	
	public Map add() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String taskId = IdWorker.getInstance().uuid();
		final String proId = StrUtil.formatNullStr(request.getParameter("proid"));
		
		final String tmpl_id = StrUtil.formatNullStr(request.getParameter("tmpl_id"));
		final String content = StrUtil.formatNullStr(request.getParameter("content"));
		final String end_time = StrUtil.formatNullStr(request.getParameter("end_time"));
		final String fzr = StrUtil.formatNullStr(request.getParameter("fzr"));
		final String joiner = StrUtil.formatNullStr(request.getParameter("joiner"));
		final String auditer = StrUtil.formatNullStr(request.getParameter("auditer"));
		
		boolean res = TaskService.getInstance().add(ub, request, taskId, proId, tmpl_id, content, fzr, auditer, joiner, end_time);
		retMap.put("ret", res);
		return retMap;
	}
	
	
	public Map save() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String taskId = StrUtil.formatNullStr(request.getParameter("id"));
		final String content = StrUtil.formatNullStr(request.getParameter("content"));
		final String end_time = StrUtil.formatNullStr(request.getParameter("end_time"));
		final String fzr = StrUtil.formatNullStr(request.getParameter("fzr"));
		final String joiner = StrUtil.formatNullStr(request.getParameter("joiner"));
		final String auditer = StrUtil.formatNullStr(request.getParameter("auditer"));
		
		boolean res = TaskService.getInstance().save(request,taskId, content, fzr, auditer, joiner, end_time);
		retMap.put("ret", res);
		return retMap;
	}
	
	public boolean remove() {
		final UserBean ub = getSessUser();
		if(ub==null){
			return false;
		}
		final String taskId = StrUtil.formatNullStr(request.getParameter("id"));
		M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
		if(task!=null){
			if(task.getCreate_uid().equals(ub.getUid())){
				task.setDelflag("1");
				//sendCardTask(taskId,Global.getNick(ub.getUid())+"删除任务","","");
				return task.save();
			}
		}
		return false;
	}
	
	public Map changeStatus() {
		Map retMap = new HashMap();
		retMap.put("res", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String taskId = StrUtil.formatNullStr(request.getParameter("id"));
		final String sts = StrUtil.formatNullStr(request.getParameter("sts"));
		M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
		task.setSts(sts);
		boolean res = task.save();
//		if(res){
//			sendCardTask(taskId,Global.getNick(ub.getUid())+"修改了任务状态","任务状态被修改为：" + 
//					(sts.equals("1")?"未完成":(sts.equals("2")?"已完成":"已归档")),"weex:taskdetail.weex.js?id="+taskId);
//		}
		retMap.put("res", res);
		retMap.put("task", task);
		return retMap;
	}
	
	
	private void sendCardProject(String projectId,String cardTitle,String cardContent,String cardUrl){
		List<M_work_project_usersDAO> users = M_work_project_usersDAO.dao.find("select uid from " + M_work_project_usersDAO.dao.getTable().getName() + " where project_id=?", projectId);
		for(M_work_project_usersDAO u : users){
			//RPCService.getInstance().sendCard(StrUtil.formatNullStr(TagConst.globalMap.get("app.id.project")), u.getUid(), cardTitle, cardContent, cardUrl);
		}
	}
	
	private void sendCardTask(String taskId,String cardTitle,String cardContent,String cardUrl){
		List<M_work_project_tasksDAO> tasks = M_work_project_tasksDAO.dao.find("select * from " + M_work_project_tasksDAO.dao.getTable().getName() + " where id=?", 
				taskId);
		Set<String> users = new HashSet();
		for(M_work_project_tasksDAO t : tasks){
			users.add(t.getAdmin_uid());
			users.add(t.getTask_auditer());
			String[] ss = StrUtil.split(t.getTask_joiner(), ",");
			for(String s : ss){
				if(!s.equals("")){
					users.add(s);
				}
			}
		}
		
		if(users!=null){
			for(String u : users){
				//RPCService.getInstance().sendCard(StrUtil.formatNullStr(TagConst.globalMap.get("app.id.project")), u, cardTitle, cardContent, cardUrl);
			}
		}
	}
	
	public Map changeCreater() {
		Map retMap = new HashMap();
		retMap.put("res", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String taskId = StrUtil.formatNullStr(request.getParameter("id"));
		final String uid = StrUtil.formatNullStr(request.getParameter("uid"));
		M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
		task.setAdmin_uid(uid);
		boolean res = task.save();
		retMap.put("res", res);
		retMap.put("task", task);
		return retMap;
	}
	
	public Map changeProject() {
		Map retMap = new HashMap();
		retMap.put("res", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String taskId = StrUtil.formatNullStr(request.getParameter("id"));
		final String proid = StrUtil.formatNullStr(request.getParameter("proid"));
		M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
		task.setProject_id(proid);
		boolean res = task.save();
		retMap.put("res", res);
		retMap.put("task", task);
		return retMap;
	}
	
	
	public Map get() {
		Map retMap = new HashMap();
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String taskId = StrUtil.formatNullStr(request.getParameter("id"));
		M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
		if(task!=null){
			retMap.put("id", task.getId());
			retMap.put("tmplIdId", task.getTmpl_id_id());
			retMap.put("project", ProjectService.getInstance().getProject(task.getProject_id()));
			retMap.put("content",task.getContent());
			retMap.put("fzr",task.getAdmin_uid());
			retMap.put("fzr_nick", Global.getNick(task.getAdmin_uid()));
			if(task.getTask_joiner()!=null){
				String[] joiners = StrUtil.split(task.getTask_joiner(), ",");
				List taskMembers = new ArrayList();
				for(String j : joiners){
					Map _m = new HashMap();
					_m.put("uid", j);
					_m.put("nick",Global.getNick(j));
					taskMembers.add(_m);
				}
				retMap.put("joiners", taskMembers);
			}
			retMap.put("auditer",task.getTask_auditer());
			retMap.put("auditer_nick",Global.getNick(task.getTask_auditer()));
			retMap.put("sts",task.getSts());
			retMap.put("endtime", task.getTime_end()!=null?DateUtils.format(task.getTime_end(),"yyyy-MM-dd"):null);
			retMap.put("createtime",DateUtils.format(task.getCreate_time(),"yyyy-MM-dd HH:mm"));
			retMap.put("comments", CommentService.getInstance().list(task.getId()));
			
			List<M_work_tmpl_fields_valueDAO> vvs = TmplService.getInstance().getData(task.getTmpl_id_id());
			retMap.put("fv", vvs);
			retMap.put("tmpl", TmplService.getInstance().getTmpl(task.getTmpl_id()));
		}
		return retMap;
	}
	
	public M_work_project_tasks_commentsDAO addComment() {
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		final String taskId = StrUtil.formatNullStr(request.getParameter("taskId"));
		final String content = StrUtil.formatNullStr(request.getParameter("content"));
		final String stype = StrUtil.formatNullStr(request.getParameter("stype"),"t");
		
		M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
		if(task!=null){
			sendCardTask(taskId,Global.getNick(ub.getUid())+"添加了任务评论", task.getContent() + " 添加了新评论：" + 
					content,"weex:taskdetail.weex.js?id="+taskId);
		}
		
		return CommentService.getInstance().addComment(ub, taskId, content, stype);
	}
	
	public boolean removeComment() {
		final UserBean ub = getSessUser();
		if(ub==null){
			return false;
		}
		
		final String cid = StrUtil.formatNullStr(request.getParameter("cid"));
		return CommentService.getInstance().remove(ub, cid);
	}
}
