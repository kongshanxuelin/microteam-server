package com.sumslack.web.working.jobs;

import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sumslack.app.jd.front.tool.WXTemplateMsgManager;
import com.sumslack.jsptagex.job.TagJob;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.controller.ActController;
import com.sumslack.web.working.dao.M_actDAO;
import com.sumslack.web.working.dao.M_act_usersDAO;
import com.sumslack.web.working.dao.UsersDAO;

public class JobActFinish extends TagJob{
	private static List<M_actDAO> acts = null;
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//查看库中所有活动，并计算哪些活动人数已达到上限并发送微信通知
		if(acts == null) {
			refreshActCache();
		}
		
		String sql = "select act_id,UNIX_TIMESTAMP(start_date) sdt,UNIX_TIMESTAMP(end_date) edt,count(*) num from m_act_users where sts='1' group by act_id,start_date,end_date";
		try {
			List statList = TagJDBCInstance.getInstance().queryList(sql, null);
			if(statList!=null && statList.size()>0) {
				for(int i=0;i<statList.size();i++) {
					Map m = (Map)statList.get(i);
					String sdt = StrUtil.formatNullStr(m.get("sdt"));
					String edt = StrUtil.formatNullStr(m.get("edt"));
					String act_id = StrUtil.formatNullStr(m.get("act_id"));
					int num = StrUtil.formatNullStrInt(m.get("num"), 0);
					M_actDAO act = getAct(act_id);
					if(act!=null && num>=act.getMin_num()) {
						M_act_usersDAO actUser = M_act_usersDAO.dao.findFirst("select * from " + M_act_usersDAO.dao.getTable().getName() + " where act_id=? and UNIX_TIMESTAMP(start_date)=? and UNIX_TIMESTAMP(end_date)=?",act_id,sdt,edt);
						if(actUser!=null) {
							UsersDAO user = UsersDAO.dao.findById("*", actUser.getUid());
							if(user!=null) {
								String d = "活动已生效，请准时参加本次活动： " + 
										ActController.unitStr(act.getFreq_unit()) + " " + ActController.unitStr(act.getFreq_unit() , act.getFreq_num()) + " 的 " + act.getTitle() + " 活动.";
								if(actUser.getForm_id()!=null) { //因为只允许提醒一次
									WXTemplateMsgManager.sendAlarm_ActJoin(actUser.getForm_id(), user.getUsername(), act_id,DateUtils.format(act.getAct_time(),"HH:mm") , num, act.getTitle(), d);
									actUser.setForm_id(null);
									actUser.save();
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void refreshActCache() {
		acts = M_actDAO.dao.find("select * from " + M_actDAO.dao.getTable().getName() + " where cur_sts='1'");
	}
	
	private static M_actDAO getAct(String actId) {
		if(acts!=null) {
			for(M_actDAO act : acts) {
				if(act.getId().equals(actId)) {
					return act;
				}
			}
		}
		return null;
	}
}
