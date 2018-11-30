package com.sumslack.web.working.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_processDAO;
import com.sumslack.web.working.dao.M_work_process_audit_defineDAO;
import com.sumslack.web.working.dao.M_work_process_audit_define_uidDAO;
import com.sumslack.web.working.dao.M_work_process_instanceDAO;

public class WorkFlowService {
	
	private  static WorkFlowService instance = new WorkFlowService();
	private WorkFlowService(){
		
	}
	
	public synchronized static WorkFlowService getIntance(){
		if(instance == null){
			instance = new WorkFlowService();
		}
		return instance;
	}
	
	public List myProcess(UserBean ub){
		List retList = new ArrayList();
		List<M_work_process_instanceDAO> wpiList = M_work_process_instanceDAO.dao
				.find("select * from " +  M_work_process_instanceDAO.dao.getTable().getName() + " where (create_uid=? or id in (select process_id from m_work_process_audit_define where id in (select audit_id from m_work_process_audit_define_uid where audit_uid=?))) and delflag='0' and sts<>'F' order by create_time desc", ub.getUid(), ub.getUid());
		for(M_work_process_instanceDAO w : wpiList){
			Map _m = new HashMap();
			_m.put("instid", w.getId());
			_m.put("sts", w.getSts());
			_m.put("lvl", w.getAudit_level());
			_m.put("uid", w.getCreate_uid());
			_m.put("nick", Global.getNick(w.getCreate_uid()));
			_m.put("time", DateUtils.format(w.getCreate_time(),DateUtils.fmt_datetimeFormat));
			_m.put("pd", getProcessAuditInfor(w.getProcess_define_id()));
			retList.add(_m);
		}
		return retList;
	}
	
	public Map getProcessAuditInfor(String id){
		Map retMap = new HashMap();
		M_work_processDAO pro = M_work_processDAO.dao.findById("*", id);
		retMap.put("pro", pro);
		//获取每级审核人员名单信息
		if(pro!=null){
			List<M_work_process_audit_defineDAO> defineList = M_work_process_audit_defineDAO.dao.find("select * from " + M_work_process_audit_defineDAO.dao.getTable().getName() + ""
				+ " where process_id=? order by audit_level", pro.getId());
			List auditLevelList = new ArrayList();
			for(M_work_process_audit_defineDAO d : defineList){
				Map _map = new HashMap();
				_map.put("id", d.getId());
				_map.put("audit_level", d.getAudit_level());
				_map.put("title", d.getTitle());
				_map.put("rel", d.getAudit_level_rel());
				List checkers = new ArrayList();
				List<M_work_process_audit_define_uidDAO> users =  M_work_process_audit_define_uidDAO.dao.find("select * from " + M_work_process_audit_define_uidDAO.dao.getTable().getName()+
						" where audit_id=?" , d.getId());
				for(M_work_process_audit_define_uidDAO u : users){
					Map _m2 = new HashMap();
					_m2.put("uid", u.getAudit_uid());
					_m2.put("nick",Global.getNick(u.getAudit_uid()));
					checkers.add(_m2);
				}
				_map.put("checkers", checkers);
				auditLevelList.add(_map);
			}
			retMap.put("audit", auditLevelList);
		}
		return retMap;
	}
}
