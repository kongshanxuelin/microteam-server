package com.sumslack.web.working.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_project_usersDAO;
import com.sumslack.web.working.dao.M_work_projectsDAO;
import com.sumslack.web.working.service.ProjectService;

@URIAlias("project")
public class ProjectController extends CController{
	public static final int PS = 20;
	public List myProjects() throws Exception {
		UserBean ub = getSessUser();
		String sts = param("sts","0");
		if(ub!=null){
			String sql = "select distinct project_id from m_work_project_users where uid=? order by project_id desc";
			List pList = TagJDBCInstance.getInstance().queryList(sql, new Object[]{ub.getUid()});
			List retList = new ArrayList();
			if(pList!=null && pList.size()>0){
				for(int i=0;i<pList.size();i++){
					Map _map = (Map)pList.get(i);
					Map p = ProjectService.getInstance().getProject(StrUtil.formatNullStr(_map.get("project_id")),sts);
					if(p!=null)
						retList.add(p);
				}
			}
			return retList;
		}else{
			return null;
		}
	}
	
	public List search() throws Exception {
		UserBean ub = getSessUser();
		if(ub!=null){
			final String q = StrUtil.formatNullStr(request.getParameter("q"));
			String sql = "select id from m_work_projects where (title like '%"+q+"%' or content like '%"+q+"%') and id in (select distinct project_id from m_work_project_users where uid=?) order by id desc";
			List pList = TagJDBCInstance.getInstance().queryList(sql, new Object[]{ub.getUid()});
			List retList = new ArrayList();
			if(pList!=null && pList.size()>0){
				for(int i=0;i<pList.size();i++){
					Map _map = (Map)pList.get(i);
					Map p = ProjectService.getInstance().getProject(StrUtil.formatNullStr(_map.get("id")));
					if(p!=null)
						retList.add(p);
				}
			}
			return retList;
		}else{
			return null;
		}
	}
	public Map add() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final String t = StrUtil.formatNullStr(request.getParameter("t"));
		final String c = StrUtil.formatNullStr(request.getParameter("c"));
		final String fzr = StrUtil.formatNullStr(request.getParameter("leader"));
		final String joiners = StrUtil.formatNullStr(request.getParameter("joiners"));
		
		final String sdt = StrUtil.formatNullStr(request.getParameter("sdt"));
		final String edt = StrUtil.formatNullStr(request.getParameter("edt"));
		System.out.println(edt);
		final UserBean ub = getSessUser();
		if(ub!=null){
			final String projectId = String.valueOf(new Date().getTime());
			boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
				@Override
				public boolean run(Connection conn) throws SQLException {
					try{
						M_work_projectsDAO projectDAO = new M_work_projectsDAO();
						projectDAO.setId(projectId);
						projectDAO.setTitle(t);
						projectDAO.setContent(c);
						projectDAO.setDelflag("0");
						projectDAO.setCreate_uid(ub.getUid());
						projectDAO.setCreate_time(new Date());
						projectDAO.setModify_uid(ub.getUid());
						projectDAO.setOrd_(1);
						projectDAO.setStart_time(sdt.equals("")?null:DateUtils.parseDate(sdt, DateUtils.fmt_dateFormat));
						projectDAO.setEnd_time(edt.equals("")?null:DateUtils.parseDate(edt, DateUtils.fmt_dateFormat));
						projectDAO.setOwner_uid(ub.getUid());
						projectDAO.setCompany_id(ub.getTeamId());
						projectDAO.setSts("0");
						projectDAO.add(conn);
						
						addProjectUser(conn,projectId,ub,fzr,"owner");

//						getRPCService().sendSysMsgCard(StrUtil.formatNullStr(TagConst.globalMap.get("app.id.project")),0, Integer.parseInt(ub.getUid()), 
//								"新项目提醒", "您创建了新项目：" + t, "weex:list.weex.js", "http://file.sumslack.com/M00/00/0A/ChrN0VjrNl-ATQXZAAAGVVKkvNc229.png");

						if(!joiners.equals("")){
							for(String j : StrUtil.split(joiners, ",")){
								if(!j.equals("") && !j.equals(fzr)){
									addProjectUser(conn,projectId,ub,j,"member");
									//通知项目参与人
//									getRPCService().sendSysMsgCard(StrUtil.formatNullStr(TagConst.globalMap.get("app.id.project")),0, Integer.parseInt(j), 
//											"新项目提醒", "您参与了新项目：" + t + ",该项目由：" + ub.getNick() + "创建", "weex:list.weex.js", "http://file.sumslack.com/M00/00/0A/ChrN0VjrNl-ATQXZAAAGVVKkvNc229.png");
								}
							}
						}
						
						return true;
						
						
					}catch(Exception ex){
						ex.printStackTrace();
					}
					return false;
				}
			});
			
			retMap.put("ret", res);
			retMap.put("project", ProjectService.getInstance().getProject(projectId));
		}
		return retMap;
	}
	
	
	public Map save() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final String projectId = StrUtil.formatNullStr(request.getParameter("id"));
		
		final String title = StrUtil.formatNullStr(request.getParameter("t"));
		final String content = StrUtil.formatNullStr(request.getParameter("c"));
		final String fzr = StrUtil.formatNullStr(request.getParameter("leader"));
		final String joiner = StrUtil.formatNullStr(request.getParameter("joiners"));
		
		final String sdt = StrUtil.formatNullStr(request.getParameter("sdt"));
		final String edt = StrUtil.formatNullStr(request.getParameter("edt"));
		final UserBean ub = getSessUser();
		if(ub!=null){
			boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
				@Override
				public boolean run(Connection conn) throws SQLException {
					try{
						M_work_projectsDAO projectDAO = M_work_projectsDAO.dao.findById("*", projectId);
						projectDAO.setTitle(title);
						projectDAO.setContent(content);
						projectDAO.setModify_uid(ub.getUid());
						projectDAO.setOrd_(1);
						projectDAO.setStart_time(sdt.equals("")?null:DateUtils.parseDate(sdt, DateUtils.fmt_dateFormat));
						projectDAO.setEnd_time(edt.equals("")?null:DateUtils.parseDate(edt, DateUtils.fmt_dateFormat));
						projectDAO.setOwner_uid(ub.getUid());
						projectDAO.setCompany_id(ub.getTeamId());
						projectDAO.setSts("0");
						projectDAO.save(conn);
						
						//清空项目用户关系表
						TagJDBCInstance.getInstance().update(conn, "delete from " + M_work_project_usersDAO.dao.getTable().getName() + " where project_id=?",new Object[]{projectId});
						
						addProjectUser(conn,projectId,ub,fzr,"owner");
						
						if(!joiner.equals("")){
							for(String j : StrUtil.split(joiner, ",")){
								if(!j.equals("") && !j.equals(fzr)){
									addProjectUser(conn,projectId,ub,j,"member");
								}
							}
						}
						return true;
					}catch(Exception ex){
						ex.printStackTrace();
					}
					return false;
				}
			});
			retMap.put("ret", res);
			retMap.put("project", ProjectService.getInstance().getProject(projectId));
		}
		return retMap;
	}
	
	//设置项目组长
	public Map setLeader() throws Exception {
		final UserBean ub = getSessUser();
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final String projectId = StrUtil.formatNullStr(request.getParameter("id"));
		final String uid = StrUtil.formatNullStr(request.getParameter("uid"));
		final String role = StrUtil.formatNullStr(request.getParameter("role"));
		M_work_project_usersDAO userRole = M_work_project_usersDAO.dao.findFirst("select * from " + M_work_project_usersDAO.dao.getTable().getName() + " where project_id=? and uid=?",projectId,uid);
		if(userRole!=null){
			userRole.setRole_id(role);
			boolean res = userRole.save();
			retMap.put("project", ProjectService.getInstance().getProject(projectId));
			retMap.put("ret", res);
		}
		return retMap;
	}
	
	//修改项目状态
	public Map setProjectSts() throws Exception{
		final UserBean ub = getSessUser();
		Map retMap = new HashMap();
		final String projectId = StrUtil.formatNullStr(request.getParameter("id"));
		final String sts = StrUtil.formatNullStr(request.getParameter("sts"));
		M_work_projectsDAO dao = M_work_projectsDAO.dao.findById("*", projectId);
		dao.setSts(sts);
		boolean res = dao.save();
		retMap.put("ret", res);
		retMap.put("project", ProjectService.getInstance().getProject(projectId));
		return retMap;
	}
	
	public boolean remove() throws Exception {
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		M_work_projectsDAO p = M_work_projectsDAO.dao.findById("id,delflag", id);
		p.setDelflag("1");
		return p.save();
	}
	
	public Map get() throws Exception {
		final UserBean ub = getSessUser();
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		return ProjectService.getInstance().getProject(id);
	}
	
	
	private boolean addProjectUser(Connection conn,String projectId,UserBean ub,String uid,String role ){
		M_work_project_usersDAO userDAO = new M_work_project_usersDAO();
		userDAO.setId(IdWorker.getInstance().uuid());
		userDAO.setCreate_uid(ub.getUid());
		userDAO.setUid(uid);
		userDAO.setModify_uid(ub.getUid());
		userDAO.setRole_id(role);
		userDAO.setProject_id(projectId);
		return userDAO.add(conn);
	}
	

}
