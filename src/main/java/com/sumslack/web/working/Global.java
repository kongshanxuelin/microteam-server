package com.sumslack.web.working;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.UsersDAO;

public final class Global {
	public static final Map<String,String> allUsers = new ConcurrentHashMap<String, String>();
	
	public static Map<String,UserBean> users = new ConcurrentHashMap<String, UserBean>();
	public static Map<String,String> usersToken = new ConcurrentHashMap<String, String>();
	
	public static String getNick(Object uid){
		if(StrUtil.formatNullStr(uid).equals("")) return "";
		String nick = StrUtil.formatNullStr(allUsers.get(StrUtil.formatNullStr(uid)));
		if(!nick.equals("")){
			return nick;
		}else{
			UsersDAO _user = UsersDAO.dao.findById("*", uid);
			if(_user!=null){
				allUsers.put(StrUtil.formatNullStr(uid),_user.getNick());
				return _user.getNick();
			}
		}
		return "";
	}
	
	public static String getUidByToken(String token){
		if(!token.equals("")){
			if(usersToken.get(token)!=null && !usersToken.get(token).equals("")){
				return StrUtil.formatNullStr(usersToken.get(token));
			}else{
				//从Redis中读取
				String uid = "";
				UsersDAO user = UsersDAO.dao.findFirst("select uid from " + UsersDAO.dao.getTable().getName() + " where token=?",token);
				if(user!=null){
					uid = user.getUid();
					usersToken.put(token, uid);
					return uid;
				}
			}
		}
		return "";
	}
}
