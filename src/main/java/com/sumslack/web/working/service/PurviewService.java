package com.sumslack.web.working.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.dao.M_work_tmplDAO;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.dao.Users_data_purviewDAO;
import com.sumslack.web.working.dao.Users_tmpl_purviewDAO;

public class PurviewService {
	private static PurviewService instance = new PurviewService();
	private PurviewService(){
		
	}
	public synchronized static PurviewService getInstance(){
		if(instance == null){
			instance = new PurviewService();
		}
		return instance;
	}
	
	public boolean addTmplPurview(Connection conn,String uid,String tmplId,boolean isedit,boolean isview,boolean isremove,boolean isowner){
		Users_tmpl_purviewDAO ut = new Users_tmpl_purviewDAO();
		String id = IdWorker.getInstance().uuid();
		ut.setId(id);
		ut.setUid(uid);
		ut.setTmpl_id(tmplId);
		ut.setIsedit(isedit ? "Y" : "N");
		ut.setIsview(isview ? "Y" : "N");
		ut.setIsremove(isremove ?"Y" : "N");
		ut.setIsowner(isowner ?"Y" : "N");
		
		return  ut.add(conn);
	}
	
	public boolean addTmplPurview(String uid,String tmplId,boolean isedit,boolean isview,boolean isremove,boolean isowner){
		Users_tmpl_purviewDAO ut = new Users_tmpl_purviewDAO();
		Users_tmpl_purviewDAO userDt = Users_tmpl_purviewDAO.dao.findFirst("select * from " +Users_tmpl_purviewDAO.dao.getTable().getName() + " where tmpl_id=? and uid=?",tmplId,uid);
		if(userDt == null){
			String id = IdWorker.getInstance().uuid();
			ut.setId(id);
			ut.setUid(uid);
			ut.setTmpl_id(tmplId);
			ut.setIsedit(isedit ? "Y" : "N");
			ut.setIsview(isview ? "Y" : "N");
			ut.setIsremove(isremove ?"Y" : "N");
			ut.setIsowner(isowner ?"Y" : "N");
			return  ut.add();
		}else{
			userDt.setIsedit(isedit ? "Y" : "N");
			userDt.setIsview(isview ? "Y" : "N");
			userDt.setIsremove(isremove ?"Y" : "N");
			return userDt.save();
		}
	}
	
	public boolean addTmplPurviewAll(Connection conn,String uid,String tmplId){
		return addTmplPurview(conn,uid,tmplId,true,true,true,true);
	}	
	
	public boolean removeGrantTmpl(String uid,String id){
		Users_tmpl_purviewDAO ut = Users_tmpl_purviewDAO.dao.findById("*",id);
		if(ut!=null){
			M_work_tmplDAO m = M_work_tmplDAO.dao.findFirst("select * from " + M_work_tmplDAO.dao.getTable().getName() + " where id=? and create_uid=?",ut.getTmpl_id(),uid);
			if(m!=null){  //创建者才可以解除模板授权
				return ut.delete();
			}
		}
		return false;
	}
	public Users_tmpl_purviewDAO isOwner(String uid,String tmplId){
		Users_tmpl_purviewDAO ut = Users_tmpl_purviewDAO.dao.findFirst("select isedit,isview,isremove,isowner from " + 
	Users_tmpl_purviewDAO.dao.getTable().getName() + " where uid=? and tmpl_id=? ",uid,tmplId);
		return ut;
	}
	
	public List listTmplUserPurview(String tmpl){
		List<Users_tmpl_purviewDAO> utList = Users_tmpl_purviewDAO.dao.find("select * from "+Users_tmpl_purviewDAO.dao.getTable().getName() + " where tmpl_id=?",tmpl);
		List retList = new ArrayList();
		for(Users_tmpl_purviewDAO ut : utList){
			Map m = new HashMap();
			m.put("id", ut.getId());
			m.put("nick",Global.getNick(ut.getUid()));
			m.put("isedit",ut.getIsedit());
			m.put("isview",ut.getIsview());
			m.put("isremove",ut.getIsremove());
			m.put("isowner",ut.getIsowner());
			retList.add(m);
		}
		return retList;
	}
	
	public List listTmplUserDataPurview(String tmpl){
		List<Users_data_purviewDAO> utList = Users_data_purviewDAO.dao.find("select * from "+Users_data_purviewDAO.dao.getTable().getName() + " where tmplid=?",tmpl);
		List retList = new ArrayList();
		for(Users_data_purviewDAO ut : utList){
			Map m = new HashMap();
			m.put("id", ut.getId());
			m.put("nick",Global.getNick(ut.getUid()));
			m.put("nick2",Global.getNick(ut.getTouid()));
			m.put("isedit",ut.getIsedit());
			m.put("isview",ut.getIsview());
			m.put("isremove",ut.getIsremove());
			retList.add(m);
		}
		return retList;
	}
	
	public boolean grantUserData(final String uid,final String uids,final String tmplId,final boolean isedit,final boolean isremove,final boolean isview){
		final String[] uidArray = StrUtil.split(uids, ",");
		final UsersDAO ud = UserService.getInstance().getUser(uid);
		if(ud!=null){
			return TagJDBCInstance.getInstance().tx(new ITx() {
				@Override
				public boolean run(Connection conn) throws SQLException {
					boolean res = true;
					for(String _uid : uidArray){
						if(_uid.trim().equals("")) continue;
						UsersDAO _toUser = UserService.getInstance().getUser(_uid);
						if(_toUser == null) continue;
						
						Users_data_purviewDAO _thisP = Users_data_purviewDAO.dao.findFirst("select * from " + Users_data_purviewDAO.dao.getTable().getName() + " where uid=? and touid=? and tmplid=?",ud.getUid(),_toUser.getUid(),tmplId);
						if(_thisP == null){
							Users_data_purviewDAO p = new Users_data_purviewDAO();
							p.setId(IdWorker.getInstance().uuid());
							p.setUid(ud.getUid());
							p.setTouid(_toUser.getUid());
							p.setIsview(isview?"Y":"N");
							p.setIsedit(isedit?"Y":"N");
							p.setIsremove(isremove?"Y":"N");
							p.setTmplid(tmplId);
							res = res && p.add(conn);
						}else{
							_thisP.setIsview(isview?"Y":"N");
							_thisP.setIsedit(isedit?"Y":"N");
							_thisP.setIsremove(isremove?"Y":"N");
							res = res && _thisP.save(conn);
						}
					}
					return res;
				}
			});
		}
		return false;
	}
	
}
