package com.sumslack.web.working.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sumslack.web.working.dao.M_work_processDAO;
import com.sumslack.web.working.dao.M_work_process_audit_defineDAO;
import com.sumslack.web.working.dao.M_work_process_audit_define_uidDAO;
import com.sumslack.web.working.dao.M_work_process_instanceDAO;
import com.sumslack.web.working.dao.M_work_process_instance_tasksDAO;
import com.sumslack.web.working.dao.M_work_tmplDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.service.TmplService;
import com.sumslack.web.working.service.WorkFlowService;

@URIAlias("process")
public class ProcessController extends CController{
	public List<M_work_processDAO> list(){
		return M_work_processDAO.dao.find("select id,title,tmpl_id from " + M_work_processDAO.dao.getTable().getName() + " where delflag='0'");
	}
	
	public List<M_work_processDAO> listTeam(){
		final UserBean ub = getSessUser();
		return M_work_processDAO.dao.find("select id,title,tmpl_id from " + M_work_processDAO.dao.getTable().getName() + " where delflag='0' and company_id=?",ub!=null?ub.getTeamId():null);
	}
	
	//显示所有流程模板
	public List<M_work_tmplDAO> listProcessTmpl() {
		return TmplService.getInstance().getTmplByCateId("process");
	}
	//添加流程
	public Map add1(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		M_work_processDAO pro = new M_work_processDAO();
		final String id = IdWorker.getInstance().uuid();
		
		final String company_id = StrUtil.formatNullStr(request.getParameter("company_id"));
		final int lvl = StrUtil.formatNullStrInt(request.getParameter("lvl"),2);
		
		final String t = StrUtil.formatNullStr(request.getParameter("t"));
		final String c = StrUtil.formatNullStr(request.getParameter("c"));
		final String tmplid = StrUtil.formatNullStr(request.getParameter("tmplid"));
		pro.setId(id);
		pro.setTitle(t);
		pro.setContent(c);
		pro.setTmpl_id(tmplid);
		pro.setDelflag("0");
		pro.setCreate_uid(ub.getUid());
		pro.setModify_uid(ub.getUid());
		pro.setCreate_time(new Date());
		pro.setOrd_(1);
		pro.setCompany_id(company_id);
		pro.setAudit_level(lvl);
		boolean res = pro.add();
		retMap.put("ret", res);
		retMap.put("pro", M_work_processDAO.dao.findById("*", id));
		return retMap;
	}
	
	public Map add2(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		final String pro_define_id = StrUtil.formatNullStr(request.getParameter("pro_define_id"));
		
		final String t = StrUtil.formatNullStr(request.getParameter("t"));
		final int auditLevel = StrUtil.formatNullStrInt(request.getParameter("audit_level"),2);
		final String audit_uid = StrUtil.formatNullStr(request.getParameter("audit_uid"));
		final String audit_rel = StrUtil.formatNullStr(request.getParameter("audit_rel"));
		
		final String[] auditUidArr = StrUtil.split(audit_uid, ",");
		
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				
				String _auditId = IdWorker.getInstance().uuid();
				M_work_process_audit_defineDAO ad = new M_work_process_audit_defineDAO();
				ad.setId(_auditId);
				ad.setTitle(t);
				ad.setDelflag("0");
				ad.setAudit_level(auditLevel);
				ad.setCreate_uid(ub.getUid());
				ad.setModify_uid(ub.getUid());
				ad.setCreate_time(new Date());
				ad.setAudit_level_rel(audit_rel);
				ad.setProcess_id(pro_define_id);
				res = res && ad.add(conn);
				
				if(auditUidArr!=null && auditUidArr.length>0){
					for(String a:auditUidArr){
						M_work_process_audit_define_uidDAO newUid = new M_work_process_audit_define_uidDAO();
						newUid.setId(IdWorker.getInstance().uuid());
						newUid.setAudit_id(_auditId);
						newUid.setAudit_uid(a);
						newUid.setCreate_time(new Date());
						newUid.setCreate_uid(ub.getUid());
						newUid.setModify_uid(ub.getUid());
						newUid.setCompany_id(ub.getTeamId());
						res = res && newUid.add(conn);
					}
				}
				return res;
			}
		});
		
		retMap.put("ret", res);
		return retMap;
	}
	
	//初始启动表单，获取表单字段
	@URIAlias("transact-start")
	public Map transactStart(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		retMap.put("ret", true);
		M_work_processDAO process = getProcess(StrUtil.formatNullStr(request.getParameter("id")));
		if(process!=null){
			retMap.put("process", process);
			Map tmpl = TmplService.getInstance().getTmpl(process.getTmpl_id());
			retMap.put("tmpl", tmpl);
		}
		return retMap;
	}
	
	private void sendCardNotification(String instId){
		M_work_process_instanceDAO inst = M_work_process_instanceDAO.dao.findById("*", instId);
		if(inst!=null){
			M_work_processDAO process = M_work_processDAO.dao.findById("*", inst.getProcess_define_id());
			String proTitle = (process!=null)?process.getTitle():"未知流程";
			//获取当前审核级别的人，提醒他们去审批
			List<M_work_process_audit_define_uidDAO> uids = M_work_process_audit_define_uidDAO.dao.find("select * from " +
					M_work_process_audit_define_uidDAO.dao.getTable().getName() + " where audit_id = (select id from "+M_work_process_audit_defineDAO.dao.getTable().getName()+" where audit_level=? and process_id=?)",
					inst.getAudit_level(),inst.getProcess_define_id());			
		}
		
	}
	
	@URIAlias("transact-init")
	public Map transactInit(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		String proid = StrUtil.formatNullStr(request.getParameter("pro_define_id"));
		String instId = IdWorker.getInstance().uuid();
		String tmpl_id_id = IdWorker.getInstance().uuid();
		//启动流程
		M_work_processDAO process = getProcess(proid);
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = startProcess(conn,ub, proid, instId,tmpl_id_id);
				res = res && TmplService.getInstance().setData(conn,request, ub,null,process.getTmpl_id(), tmpl_id_id);
				return res;
			}
		});
		retMap.put("ret", res);
		retMap.put("instId", instId);
		sendCardNotification(instId);
		return retMap;
	}
	
	@URIAlias("transact-get")
	public Map transactGet(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		retMap.put("ret", true);
		String instid = StrUtil.formatNullStr(request.getParameter("id"));
		retMap.put("instid", instid);
		M_work_process_instanceDAO inst = getProcessInst(instid);
		
		if(inst!=null){
			String proid = inst.getProcess_define_id();
			Map pro = WorkFlowService.getIntance().getProcessAuditInfor(proid);
			retMap.put("pro", pro);
			retMap.put("cur_lvl", inst.getAudit_level());
		}
		
		if(inst!=null){
			List<M_work_tmpl_fields_valueDAO> vv = TmplService.getInstance().getData(inst.getTmpl_id_id());
			retMap.put("data", vv);
		}
		
		//当前审核级数
		int lvl = inst.getAudit_level();
		M_work_process_audit_defineDAO mpad = getAuditTask(lvl,inst.getProcess_define_id());
		if(mpad!=null){
			String mpadId = mpad.getId();
			retMap.put("taskName", mpad.getTitle());
			//当前谁审核
			List<M_work_process_audit_define_uidDAO> checkUid = M_work_process_audit_define_uidDAO.dao
					.find("select audit_uid from " + M_work_process_audit_define_uidDAO.dao.getTable().getName() + " where audit_id=?",mpadId);
			retMap.put("check_uid", checkUid);
		}
		
		retMap.put("tasks", getProcessTasks(inst.getId()));
		return retMap;
	}
	//流程审核
	public Map transact(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String instid = StrUtil.formatNullStr(request.getParameter("instid"));
		
		final String memo = StrUtil.formatNullStr(request.getParameter("memo"));
		final String d = StrUtil.formatNullStr(request.getParameter("d"));
		
		M_work_process_instanceDAO inst = getProcessInst(instid);
		if(inst.getSts().equals("F")){
			retMap.put("msg", "流程已结束！");
			return retMap;
		}
		//当前审核级数
		int lvl = inst.getAudit_level();
		//获取当前流程
		M_work_process_audit_defineDAO mpad = getAuditTask(lvl,inst.getProcess_define_id());
		String mpadId = mpad.getId();
		//获取当前流程的审批者列表
		List<M_work_process_audit_define_uidDAO> checkUidList = M_work_process_audit_define_uidDAO.dao
				.find("select audit_id,audit_uid from " + M_work_process_audit_define_uidDAO.dao.getTable().getName() + " where audit_id=?",mpadId);
		M_work_process_audit_define_uidDAO checkUid = inCheckUserList(checkUidList,ub);
		if(checkUid != null){
			String taskId = IdWorker.getInstance().uuid();
			M_work_process_instance_tasksDAO task = new M_work_process_instance_tasksDAO();
			task.setId(taskId);
			task.setProcess_define_id(inst.getProcess_define_id());
			task.setDelflag("0");
			task.setCreate_uid(ub.getUid());
			task.setModify_uid(ub.getUid());
			task.setCreate_time(new Date());
			task.setProcess_instance_id(instid);
			task.setAudit_uid(ub.getUid());
			task.setAudit_sts(d);
			task.setAudit_memo(memo);
			task.setAudit_define_id(checkUid.getAudit_id());
			task.setAudit_level(lvl);
			task.add();
			
			boolean res  = execTask(lvl,inst);
			retMap.put("ret", res);
		}
		sendCardNotification(instid);
		return retMap;
	}
	
	private boolean execTask(final int level,final M_work_process_instanceDAO inst){
		boolean res = false;
		M_work_process_audit_defineDAO auditTask = getAuditTask(level,inst.getProcess_define_id());
		String rel = auditTask.getAudit_level_rel();
		
		List<M_work_process_audit_define_uidDAO> uids = M_work_process_audit_define_uidDAO.dao.find("select * from " +
				M_work_process_audit_define_uidDAO.dao.getTable().getName() + " where audit_id=?",auditTask.getId());
		int checkUserLen = uids!=null ? uids.size():0;
				
		int taskLen = getCurrentLevelTaskSize(level,inst.getProcess_define_id(),auditTask.getId(),inst.getId());
		if((taskLen>0 && rel.equals("OR"))||(rel.equals("AND") && (checkUserLen == taskLen))){
			inst.setAudit_level(inst.getAudit_level()+1);
			M_work_processDAO process = M_work_processDAO.dao.findById("*", inst.getProcess_define_id());
			int totalLevel = process.getAudit_level();
			if(level>=totalLevel){
				//流程审核完毕
				inst.setSts("F");
			}
			res = inst.save();
		}
		
				
		return res;
	}
	
	private List getProcessTasks(String instId){
		List retList = new ArrayList();
		List<M_work_process_instance_tasksDAO> list = M_work_process_instance_tasksDAO.dao.find("select create_time,audit_sts,audit_uid,audit_memo from " +
				M_work_process_instance_tasksDAO.dao.getTable().getName() + " where process_instance_id=? order by create_time",instId);
		for(M_work_process_instance_tasksDAO t : list){
			Map _m = new HashMap();
			_m.put("uid", t.getAudit_uid());
			_m.put("nick", Global.getNick(t.getAudit_uid()));
			_m.put("time", DateUtils.format(t.getCreate_time(),DateUtils.fmt_datetimeFormat));
			_m.put("memo", StrUtil.formatNullStr(t.getAudit_memo()).equals("")?"无":t.getAudit_memo());
			_m.put("sts", t.getAudit_sts().equals("1")?"同意":"拒绝");
			retList.add(_m);
		}
		return retList;
	}
	
	private int getCurrentLevelTaskSize(int level,String process_id,String audit_define_id,String instId){
		List<M_work_process_instance_tasksDAO> list = M_work_process_instance_tasksDAO.dao.find("select * from " + 
				M_work_process_instance_tasksDAO.dao.getTable().getName() + " where process_instance_id=? and audit_level=? and audit_define_id=? and process_define_id=? and audit_sts='1'",
				instId,level,audit_define_id,process_id);
		return list!=null ? list.size() : 0 ;
	}
	
	private M_work_process_audit_defineDAO getAuditTask(int lvl,String process_id){
		return  M_work_process_audit_defineDAO.dao.findFirst("select * from " + M_work_process_audit_defineDAO.dao.getTable().getName() 
				+ " where audit_level=? and process_id=?",lvl,process_id);
	}
	
	private M_work_process_audit_define_uidDAO inCheckUserList(List<M_work_process_audit_define_uidDAO> uids,UserBean ub){
		if(ub!=null && uids!=null){
			for(M_work_process_audit_define_uidDAO uid : uids){
				if(uid.getAudit_uid().equals(ub.getUid())){
					return uid;
				}
			}
		}
		return null;
	}
	
	public List myprocess(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		List retList = WorkFlowService.getIntance().myProcess(ub);	
		return retList;
	}
	
	public List myprocessfinished(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		List retList = new ArrayList();
		List<M_work_process_instanceDAO> wpiList = M_work_process_instanceDAO.dao
				.find("select * from " +  M_work_process_instanceDAO.dao.getTable().getName() + " where create_uid=? and delflag='0' and sts='F' order by create_time", ub.getUid());
		for(M_work_process_instanceDAO w : wpiList){
			Map _m = new HashMap();
			_m.put("instid", w.getId());
			_m.put("sts", w.getSts());
			_m.put("lvl", w.getAudit_level());
			_m.put("uid", w.getCreate_uid());
			_m.put("nick", Global.getNick(w.getCreate_uid()));
			_m.put("time", DateUtils.format(w.getCreate_time(),DateUtils.fmt_datetimeFormat));
			_m.put("pd", WorkFlowService.getIntance().getProcessAuditInfor(w.getProcess_define_id()));
			retList.add(_m);
		}
		return retList;
	}
	
	@URIAlias("process-his")
	public Map myProcessHistory(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		int p = StrUtil.formatNullStrInt(request.getParameter("p"), 1);
		TagPage page = new TagPage();
		page.init(page, p, 20);
		M_work_process_instance_tasksDAO.dao.findPage("select distinct process_instance_id from " + M_work_process_instance_tasksDAO.dao.getTable().getName() 
				+ " where create_uid = ? or audit_uid = ?",page, ub.getUid(),ub.getUid());
		List<M_work_process_instance_tasksDAO> resultList = page.getResult();
		if(resultList!=null && resultList.size()>0){
			List retList = new ArrayList();
			for(M_work_process_instance_tasksDAO t : resultList){
				String instId = t.getProcess_instance_id();
				M_work_process_instanceDAO w = getProcessInst(instId);
				Map _m = new HashMap();
				_m.put("instid", w.getId());
				_m.put("sts", w.getSts());
				_m.put("lvl", w.getAudit_level());
				_m.put("uid", w.getCreate_uid());
				_m.put("nick", Global.getNick(w.getCreate_uid()));
				_m.put("timeStart", DateUtils.format(w.getCreate_time(),DateUtils.fmt_datetimeFormat));
				_m.put("timeEnd", DateUtils.format(w.getModify_time(),DateUtils.fmt_datetimeFormat));
				_m.put("pd", WorkFlowService.getIntance().getProcessAuditInfor(w.getProcess_define_id()));
				
				List<M_work_tmpl_fields_valueDAO> vv = TmplService.getInstance().getData(w.getTmpl_id_id());
				_m.put("data", vv);
				
				retList.add(_m);
			}
			page.setResult(retList);
		}
		retMap.put("ret", true);
		retMap.put("page", page);
		return retMap;
	}
	
	public Map process(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String proId = StrUtil.formatNullStr(request.getParameter("proid"));
		return null;
	}
	
	private boolean startProcess(Connection conn,UserBean ub,final String proid,final String instId,final String tmpl_id_id){
		M_work_process_instanceDAO instance = new M_work_process_instanceDAO();
		instance.setId(instId);
		instance.setProcess_define_id(proid);
		instance.setDelflag("0");
		instance.setCreate_uid(ub.getUid());
		instance.setModify_uid(ub.getUid());
		instance.setCreate_time(new Date());
		instance.setSts("I");
		instance.setAudit_level(1);
		instance.setTmpl_id_id(tmpl_id_id);
		return instance.add(conn);
	}
	
	private M_work_processDAO getProcess(String id){
		return M_work_processDAO.dao.findById("*", id);
	}
	

	
	private M_work_process_instanceDAO getProcessInst(String instid){
		return M_work_process_instanceDAO.dao.findById("*", instid);
	}
	
	private Map getProcessInstDetail(String instid){
		Map retMap = new HashMap();
		M_work_process_instanceDAO inst = getProcessInst(instid);
		M_work_processDAO pro = getProcess(inst.getProcess_define_id());
		retMap.put("inst", inst);
		retMap.put("process", pro);
		retMap.put("tmpl", TmplService.getInstance().getTmpl(pro.getTmpl_id()));
		List<M_work_tmpl_fields_valueDAO> vv = TmplService.getInstance().getData(inst.getTmpl_id_id());
		retMap.put("vv", vv);
		return retMap;
	}
}
