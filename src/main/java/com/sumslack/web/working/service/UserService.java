package com.sumslack.web.working.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.TeamsDAO;
import com.sumslack.web.working.dao.Teams_userDAO;
import com.sumslack.web.working.dao.UsersDAO;

public class UserService {
	private static UserService instance = new UserService();
	private UserService(){
		
	}
	public synchronized static UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}
	
	public UsersDAO getUser(String username){
		return UsersDAO.dao.findFirst("select * from " + UsersDAO.dao.getTable().getName() + " where username=? or uid=?",username,username);
	}
	
	public UsersDAO getUserByUid(String uid){
		return UsersDAO.dao.findFirst("select * from " + UsersDAO.dao.getTable().getName() + " where uid=?",uid);
	}
	
	public boolean changeTeam(String uid,String teamId){
		TeamsDAO team = TeamsDAO.dao.findById("*",teamId);
		if(team!=null){
			UsersDAO user = UsersDAO.dao.findById("*", uid);
			if(user!=null){
				user.setCompany_id(team.getId());
				user.setCompany_name(team.getTitle());
				return user.save();
			}
		}
		return false;
	}
	
	public List getTeamList(String uid){
		List<Teams_userDAO> teamUsers = Teams_userDAO.dao.find("select distinct team_id from " + Teams_userDAO.dao.getTable().getName() + " where uid=?", uid);
		if(teamUsers!=null && teamUsers.size()>0){
			List list = new ArrayList();
			for(Teams_userDAO tu : teamUsers){
				Map m = getTeam(tu.getTeam_id());
				if(m!=null)
					list.add(m);
			}
			return list;
		}
		return Collections.EMPTY_LIST;
	}
	
	public List getTeamDeptList(UserBean ub){
		List<TeamsDAO> teams = TeamsDAO.dao.find("select distinct id from " + TeamsDAO.dao.getTable().getName() + " where delflag<>'1' and (id=? or pid=?)", ub.getTeamId(), ub.getTeamId());
		if(teams!=null && teams.size()>0){
			List list = new ArrayList();
			for(TeamsDAO tu : teams){
				Map m = getTeam(tu.getId());
				if(m!=null)
					list.add(m);
			}
			return list;
		}
		return Collections.EMPTY_LIST;
	}
	
	public Map getTeam(String id){
		TeamsDAO team = TeamsDAO.dao.findById("*", id);
		Map retMap = new HashMap();
		if(team!=null){
			if(team.getDelflag().equals("1")) return null;
			retMap.put("team", team);
			List<Teams_userDAO> users = Teams_userDAO.dao.find("select role,uid from " + Teams_userDAO.dao.getTable().getName() + " where team_id=?", id);
			if(users!=null && users.size()>0){
				List memberList = new ArrayList();
				for(Teams_userDAO _u : users){
					Map userMap = new HashMap();
					userMap.put("id", _u.getId());
					userMap.put("role",_u.getRole());
					userMap.put("uid", _u.getUid());
					userMap.put("nick", Global.getNick(_u.getUid()));
					memberList.add(userMap);
				}
				retMap.put("members", memberList);
			}
			return retMap;
		}else{
			return null;
		}
	}
	
	public UserBean getUserByToken(String token){
		try {
			UsersDAO user = UsersDAO.dao.findFirst("select * from  users where token=?",token);
			if(user!=null){
				UserBean ub = new UserBean();
				ub.setUid(StrUtil.formatNullStr(user.getUid()));
				ub.setUsername(user.getUsername());
				ub.setNick(StrUtil.formatNullStr(user.getNick()));
				ub.setAvatar(StrUtil.formatNullStr(user.getAvatar()));
				//ub.setSession_key(StrUtil.formatNullStr(user.getSession_key()));
				ub.setToken(StrUtil.formatNullStr(user.getToken()));
				//团队信息
				ub.setTeamId(StrUtil.formatNullStr(user.getCompany_id()));
				ub.setTeamName(StrUtil.formatNullStr(user.getCompany_name()));
				
				ub.setTeamList(UserService.getInstance().getTeamList(ub.getUid()));
				return ub;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String addWxUser(String openId,String nick,String avator,String session_key,String token){
		UsersDAO user = new UsersDAO();
		//生成用户ID
		String _uid = String.valueOf(new Date().getTime());
		user.setUid(_uid);
		user.setUsername(openId);
		user.setNick(nick);
		user.setAvatar(avator);
		user.setSession_key(session_key);
		user.setAvatar(avator);
		user.setSource("w");
		user.setToken(token);
		if(user.add()){
			return _uid;
		}
		return null;
	}

	public static String filterOffUtf8Mb4(String text,String defaultText) {  
	    
		try {
			String regEx="[a-zA-Z0-9\\u4e00-\\u9fa5]";  
	        Pattern   p   =   Pattern.compile(regEx);     
	        Matcher   m   =   p.matcher(text);
	        StringBuffer sb = new StringBuffer();
	        while(m.find()){
	        	sb.append(m.group());
	        }
	        return sb.toString();
	    
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return defaultText;
	      
	}  
	
	public boolean saveWxUser(String uid,String nick,String avator,String session_key,String token){
		UsersDAO user = UsersDAO.dao.findFirst("select * from " + UsersDAO.dao.getTable().getName() + " where uid=? or username=?",uid,uid);
		if(user!=null){
			user.setNick(nick);
			user.setAvatar(avator);
			user.setSession_key(session_key);
			user.setToken(token);
			return user.save();
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(filterOffUtf8Mb4("火华☺23","xxxxx"));
	}
}
