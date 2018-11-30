package com.sumslack.web.working.jobs;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sumslack.jsptagex.job.TagJob;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.controller.ActController;
import com.sumslack.web.working.dao.M_actDAO;

public class JobAct extends TagJob{
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		List<M_actDAO> actList = M_actDAO.dao.find("select * from " + M_actDAO.dao.getTable().getName() + " where sts<>'1'");
		Date cdate = new Date();
		//把所有end_date<今天的记录全部设置为过期
		try {
			TagJDBCInstance.getInstance().update("update m_act_users set sts='0' where end_date < CURRENT_DATE" , null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(actList!=null && actList.size()>0) {
			for(M_actDAO act : actList) {
				Date endDate = act.getEnd_date();
				Date curEndDate = act.getCur_end_date();
				if(cdate.getTime() > endDate.getTime()) {  //当前时间大于活动总结束时间，设为过期
					act.setSts("0");
					act.setCur_sts("0");
					act.save();
				}else if(cdate.getTime() > curEndDate.getTime()) { //当前活动结束时间到了，设置下一个活动周期时间
					String unit = act.getFreq_unit().toLowerCase();
					
					Date startFreqDate = ActController.calDate(act.getStart_date(),act.getEnd_date(),act.getFreq_unit(),act.getFreq_num());
					
					if(cdate.getTime() > act.getCur_end_date().getTime()) {  //需要计算下一轮活动开始和借宿时间
						if(act.getCur_end_date().getTime() < act.getEnd_date().getTime()) { //还没过期
							if(unit.equals("w")) {
								act.setCur_start_date(startFreqDate);
								Date nextDay = DateUtils.addDay(act.getCur_start_date(), 7);
								act.setCur_end_date(nextDay);
								if(cdate.getTime()<act.getEnd_date().getTime()) {
									act.setCur_sts("1"); //生效
								}else {
									act.setCur_sts("0"); //已过期
								}
							}else if(unit.equals("2w")) {
								act.setCur_start_date(startFreqDate);
								Date nextDay = DateUtils.addDay(act.getCur_start_date(), 14);
								act.setCur_end_date(nextDay);
								if(cdate.getTime()<act.getEnd_date().getTime()) {
									act.setCur_sts("1"); //生效
								}else {
									act.setCur_sts("0"); //已过期
								}
							}else if(unit.equals("m")) {
								act.setCur_start_date(startFreqDate);
								Date nextDay = DateUtils.addMonth(act.getCur_start_date(), 1);
								act.setCur_end_date(nextDay);
								if(cdate.getTime()<act.getEnd_date().getTime()) {
									act.setCur_sts("1"); //生效
								}else {
									act.setCur_sts("0"); //已过期
								}
							}
						}else {
							act.setCur_start_date(startFreqDate);
							act.setCur_end_date(act.getEnd_date());//如果越过了总最后时间
							act.setCur_sts("1"); //生效
						}
						act.save();
					}
				}
				
			}
		}
	}
}
