package com.sumslack.web.working.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_webstat_projectsDAO;
import com.sumslack.web.working.dao.M_work_project_usersDAO;
import com.sumslack.web.working.dao.M_work_projectsDAO;
import com.sumslack.web.working.dao.TeamsDAO;
import com.sumslack.web.working.dao.Teams_userDAO;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.service.UserService;

@URIAlias("user")
public class UserController extends CController{
	public List<UsersDAO> listCompanyUser() {
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		return UsersDAO.dao.find("select uid,nick,avatar,dept,py "
				+ " from " + UsersDAO.dao.getTable().getName() +" where uid in (select uid from teams_user where team_id=?)", ub.getTeamId());
		
		
	}
	
	public List<M_webstat_projectsDAO> listWebNetStat() {
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		return M_webstat_projectsDAO.dao.find("select prj_id,prj_title,dsname "
				+ " from " + M_webstat_projectsDAO.dao.getTable().getName() +" where teamid=?", ub.getTeamId());
		
		
	}
	
	public Map getUserInfo(){
		Map retMap = new HashMap();
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		UsersDAO _user = UserService.getInstance().getUserByUid(ub.getUid());
		retMap.put("user", _user);
		return retMap;
	}
	public Map saveUserInfor(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		UsersDAO _user = UserService.getInstance().getUserByUid(ub.getUid());
		if(_user!=null){
			String truename = StrUtil.formatNullStr(request.getParameter("truename"));
			String sign = StrUtil.formatNullStr(request.getParameter("sign"));
			String tel = StrUtil.formatNullStr(request.getParameter("tel"));
			
			String dept_id = StrUtil.formatNullStr(request.getParameter("dept_id"));

			String email = StrUtil.formatNullStr(request.getParameter("email"));
			String addr = StrUtil.formatNullStr(request.getParameter("addr"));
			
			if(!truename.equals(""))
				_user.setTruename(truename);
			if(!sign.equals(""))
				_user.setSign(sign);
			if(!tel.equals(""))
				_user.setTel(tel);
			if(!dept_id.equals("")){
				_user.setDept_id(dept_id);
				TeamsDAO _team = TeamsDAO.dao.findById("*", dept_id);
				if(_team!=null){
					_user.setDept_name(_team.getTitle());
				}
			}
			if(!email.equals("")){
				_user.setEmail(email);
			}
			if(!addr.equals("")){
				_user.setAddr(addr);
			}
			retMap.put("ret", _user.save());
		}
		return retMap;
	}
	
	public List teamList(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		return UserService.getInstance().getTeamList(ub.getUid());
	}
	
	public List teamDeptList(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		return UserService.getInstance().getTeamDeptList(ub);
	}
	

	
	public Map teamRemove(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String teamid = param("teamid");
		TeamsDAO team = TeamsDAO.dao.findById("*", teamid);
		if(team!=null){
			team.setDelflag("1");
			retMap.put("ret", team.save());
			
			UsersDAO user = UsersDAO.dao.findById("*", ub.getUid());
			if(user!=null){
				if(user.getCompany_id().equals(teamid)){
					user.setCompany_id(null);
					user.setCompany_name(null);
					user.save();
				}
			}
		}
		return retMap;
	}
	
	public Map teamChange(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String teamid = param("teamid");
		UsersDAO user = UsersDAO.dao.findById("*", ub.getUid());
		if(user!=null){
			user.setCompany_id(teamid);
			TeamsDAO t = TeamsDAO.dao.findById("*", teamid);
			if(t!=null){
				user.setCompany_name(t.getTitle());
				ub.setTeamName(t.getTitle());
			}
			user.setCompany_id(teamid);
			request.getSession().setAttribute("teamId", user.getCompany_id());
			request.getSession().setAttribute("teamName", user.getCompany_name());

			ub.setTeamId(teamid);
			Global.users.put(ub.getUid(),ub);
			
			retMap.put("ret", user.save());
		}
		return retMap;
	}
	public Map teamSave(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String id = param("id");
		final String title = param("title");
		final String pid = param("pid");
		UsersDAO user = UsersDAO.dao.findById("*", ub.getUid());
		if(user!=null){
			TeamsDAO t = TeamsDAO.dao.findById("*", id);
			if(t!=null){
				if(!title.equals("")){
					t.setTitle(title);
				}
				if(!pid.equals("")){
					t.setPid(pid);
				}
			}
			retMap.put("ret", t.save());
		}
		return retMap;
	}
	
	public Map projectShare() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String prjid = StrUtil.formatNullStr(request.getParameter("prjid"));
		List<M_work_project_usersDAO> list = M_work_project_usersDAO.dao.find("select * from " + M_work_project_usersDAO.dao.getTable().getName() +
				" where project_id=? and uid=?",prjid,ub.getUid());
		if(list == null) {
			M_work_project_usersDAO u = new M_work_project_usersDAO();
			u.setId(IdWorker.getInstance().uuid());
			u.setProject_id(prjid);
			u.setRole_id("member");
			u.setUid(ub.getUid());
			u.setCreate_uid(ub.getUid());
			u.setModify_uid(ub.getUid());
			u.setModify_time(new Timestamp(new Date().getTime()));
			boolean res = u.add();
			//加入团队
			
			List<M_work_projectsDAO> projectList = M_work_projectsDAO.dao.find("select * from " + M_work_projectsDAO.dao.getTable().getName() +
					" where project_id=?",prjid);
			if(projectList!=null && projectList.size()==1) {
				M_work_projectsDAO _project = projectList.get(0);
				if(_project!=null) {
					String teamId = _project.getCompany_id();
					joinTeam(teamId,ub.getUid());
				}
			}
			retMap.put("ret", res);
		}else {
			retMap.put("msg", "您已加入该项目！");
		}
		return retMap;
	}
	
	public Map teamShare(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		
		Teams_userDAO _teamUser = Teams_userDAO.dao.findFirst("select id from " + Teams_userDAO.dao.getTable().getName() + 
				" where uid=? and team_id=?",ub.getUid(),id);
		if(_teamUser!=null){
			retMap.put("msg", "已加入该团队！");
			return retMap;
		}
		
		Teams_userDAO tu = new Teams_userDAO();
		tu.setId(IdWorker.getInstance().uuid());
		tu.setTeam_id(id);
		tu.setTeam_tid(id);
		tu.setRole("member");
		tu.setUid(ub.getUid());
		boolean res = tu.add();
		UsersDAO _user = UserService.getInstance().getUserByUid(ub.getUid());
		TeamsDAO _team = TeamsDAO.dao.findById("*", id);
		if(_user!=null && _team!=null){
			_user.setCompany_id(_team.getId());
			_user.setCompany_name(_team.getTitle());
			_user.save();
		}
		retMap.put("ret", res);
		retMap.put("team", _team);
		return retMap;
	}
	
	private boolean joinTeam(String teamId,String uid) {
		Teams_userDAO _teamUser = Teams_userDAO.dao.findFirst("select id from " + Teams_userDAO.dao.getTable().getName() + 
				" where uid=? and team_id=?",uid,teamId);
		
		//已存在
		if(_teamUser!=null){
			return false;
		}
		
		Teams_userDAO tu = new Teams_userDAO();
		tu.setId(IdWorker.getInstance().uuid());
		tu.setTeam_id(teamId);
		tu.setTeam_tid(uid);
		tu.setRole("member");
		tu.setUid(uid);
		boolean res = tu.add();
		UsersDAO _user = UserService.getInstance().getUserByUid(uid);
		TeamsDAO _team = TeamsDAO.dao.findById("*", teamId);
		if(_user!=null && _team!=null){
			_user.setCompany_id(_team.getId());
			_user.setCompany_name(_team.getTitle());
			_user.save();
		}
		return res;
	}
	public Map teamCreate(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		TeamsDAO  team = new TeamsDAO();
		final String id = IdWorker.getInstance().uuid();
		final String pid = param("id");
		final String title = param("title");
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				team.setId(id);
				team.setTitle(title);
				team.setCreate_uid(ub.getUid());
				team.setCreate_time(new Date());
				team.setAvatar(null);
				team.setDelflag("0");
				team.setPid(pid.equals("")?null:pid);
				res = res && team.add(conn);
				Teams_userDAO tu = new Teams_userDAO();
				tu.setId(IdWorker.getInstance().uuid());
				tu.setTeam_id(id);
				tu.setRole("owner");
				tu.setUid(ub.getUid());
				res = res && tu.add(conn);
				return res;
			}
		});
		retMap.put("ret", res);
		retMap.put("team", getTeam(id));
		if(res){
			//该用户没有任何团队时，自动切换到当前团队
			List isHaveTeamList = UserService.getInstance().getTeamList(ub.getUid());
			if(isHaveTeamList.size()==1){
				UsersDAO user = UsersDAO.dao.findById("*", ub.getUid());
				if(user!=null){
					user.setCompany_id(id);
					user.setCompany_name(title);
					user.save();
				}
			}
		}
		return retMap;
	}
	
	public Map getTeam(String id){
		return UserService.getInstance().getTeam(id);
	}
	
	
	public Map teamMembers(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		List<UsersDAO> userList = UsersDAO.dao.find("select uid,nick,avatar,dept,py "
				+ " from " + UsersDAO.dao.getTable().getName() +" where uid in (select uid from teams_user where team_id=?)", ub.getTeamId());
		if(userList!=null){
			retMap.put("ret", true);
			Set<String> letters = new LinkedHashSet();
			
			Map<String,List<Map>> userBeanMap = new LinkedHashMap<String, List<Map>>();
			
			String firstLetter = "";
			for(UsersDAO _user : userList){
				firstLetter = (_user.getPy()!=null && _user.getPy().length()>1) ? _user.getPy().substring(0,1) : "#";
				letters.add(firstLetter);
				
				if(userBeanMap.get(firstLetter) == null){
					userBeanMap.put(firstLetter, new ArrayList<Map>());
				}
				Map _userMap = new HashMap();
				_userMap.put("name", _user.getNick());
				_userMap.put("avatar", _user.getAvatar()==null?"../../images/avatar.png":_user.getAvatar());
				userBeanMap.get(firstLetter).add(_userMap);
			}
			retMap.put("letters", firstLetter);
			
			List retList = new ArrayList();
			for(Entry<String,List<Map>> ee : userBeanMap.entrySet()){
				Map _retMap = new HashMap();
				_retMap.put("groupName", ee.getKey());
				_retMap.put("users", ee.getValue());
				retList.add(_retMap);
			}
			
			retMap.put("groups", retList);
			
		}
		return retMap;
		
	}
	
}
