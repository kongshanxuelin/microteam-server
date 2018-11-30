package com.sumslack.web.working.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_project_tasksDAO;
import com.sumslack.web.working.dao.M_work_project_usersDAO;
import com.sumslack.web.working.dao.M_work_projectsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;

public class ProjectService {
	private static ProjectService instance = new ProjectService();
	private ProjectService(){
		
	}
	public synchronized static ProjectService getInstance(){
		if(instance == null){
			instance = new ProjectService();
		}
		return instance;
	}
	public Map getProject(String projectId){
		return getProject(projectId,"0");
	}
	
	public String getProjectName(String projectId){
		M_work_projectsDAO project = M_work_projectsDAO.dao.findById("*", projectId);
		if(project!=null){
			return project.getTitle();
		}
		return "";
	}
	
	public List<M_work_projectsDAO> getTeamProject(String teamId){
		return M_work_projectsDAO.dao.find("select id,title from " + M_work_projectsDAO.dao.getTable().getName() + " where company_id=?", teamId);
	}
	
	public Map getProject(String projectId,final String sts){
		M_work_projectsDAO project = M_work_projectsDAO.dao.findById("*", projectId);
		if(project == null) return null;
		if(!project.getSts().equals(sts)) return null;
		if(project.getDelflag().equals("1")) return null;
		List<M_work_project_usersDAO> usersDAO = M_work_project_usersDAO.dao.find("select uid,role_id from " + M_work_project_usersDAO.dao.getTable().getName()+ " where project_id=?", projectId);
		Map retMap = new HashMap();
		retMap.put("id", project.getId());
		retMap.put("sts", project.getSts());
		retMap.put("title", project.getTitle());
		retMap.put("content", project.getContent());
		retMap.put("creator", project.getCreate_uid());
		retMap.put("createNick", Global.getNick(project.getCreate_uid()));
		retMap.put("sdt", project.getStart_time() == null ? null : DateUtils.format(project.getStart_time(), DateUtils.fmt_dateFormat));
		retMap.put("edt", project.getEnd_time() == null ? null : DateUtils.format(project.getEnd_time(), DateUtils.fmt_dateFormat));
		JSONArray users = new JSONArray();
		if(usersDAO!=null){
			for(M_work_project_usersDAO user : usersDAO){
				JSONObject _user = new JSONObject();
				_user.put("uid", user.getUid());
				_user.put("nick", Global.getNick(user.getUid()));
				_user.put("role", user.getRole_id());
				users.add(_user);
			}
		}
		retMap.put("members", users);
		return retMap;
	}
	
	public Map getTask(UserBean ub, String id){
		final String taskId = id;
		M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
		Map retMap = new HashMap();
		if(task!=null){
			retMap.put("id", task.getId());
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
}
