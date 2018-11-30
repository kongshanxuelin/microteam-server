package com.sumslack.web.working.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.app.jd.front.tool.WXTemplateMsgManager;
import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.bean.TagPage;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_actDAO;
import com.sumslack.web.working.dao.M_act_usersDAO;
import com.sumslack.web.working.jobs.JobActFinish;

@URIAlias("wx/act")
public class ActController extends CController{
	public Map myact() {
		Map retMap = new HashMap();
		UserBean ub = getWxUser();
		if(ub == null) return null;
		//可参加的活动
		List<M_actDAO> actCanJoinedList = M_actDAO.dao.find("select id,title,min_num,cur_start_date,cur_end_date,freq_unit,freq_num from " + M_actDAO.dao.getTable().getName() + " where teamid=? and id not in (select act_id from m_act_users where uid=? and sts='1')", ub.getTeamId(),ub.getUid());
		//已参加活动
		List<M_actDAO> actJoinedList = M_actDAO.dao.find("select id,title,min_num,cur_start_date,cur_end_date,freq_unit,freq_num from " + M_actDAO.dao.getTable().getName() + " where teamid=? and id in (select act_id from m_act_users where uid=? and sts='1' and CURRENT_TIMESTAMP < end_date)", ub.getTeamId(),ub.getUid());
		
		retMap.put("canjoin", actCanJoinedList);
		
		List actJoinedResultList = new ArrayList();
		if(actJoinedList!=null && actJoinedList.size()>0) {
			for(M_actDAO actUser:actJoinedList) {
				Map _m = new HashMap();
				_m.put("id",actUser.getId() );
				_m.put("title",actUser.getTitle() );
				_m.put("min_num",actUser.getMin_num() );
				_m.put("cur_start_date",actUser.getCur_start_date().getTime() );
				_m.put("cur_end_date",actUser.getCur_end_date().getTime() );
				_m.put("freq_unit", actUser.getFreq_unit());
				_m.put("freq_num", actUser.getFreq_num());
				//计算已参加人数
				List _list = M_act_usersDAO.dao.find("select id from " + M_act_usersDAO.dao.getTable().getName() + " where act_id=? and start_date=? and end_date=?", actUser.getId(),
						DateUtils.format(actUser.getCur_start_date(),"yyyy-MM-dd 00:00:00"),DateUtils.format(actUser.getCur_end_date(),"yyyy-MM-dd 00:00:00"));
				_m.put("rep_num", _list!=null?_list.size():0);
				_m.put("num_percent", Math.floor((_list!=null?_list.size():0)*100.0f/actUser.getMin_num()) + "%");
				actJoinedResultList.add(_m);
			}
		}
		retMap.put("joined", actJoinedResultList);
		return retMap;
	}
	
	public Map list() {
		Map retMap = new HashMap();
		UserBean ub = getWxUser();
		if(ub == null) return null;
		TagPage page = new TagPage();
		TagPage.init(page, StrUtil.formatNullStrInt(request.getParameter("p"), 1), 20);
		M_actDAO.dao.findPage("select * from " + M_actDAO.dao.getTable().getName() + " where teamid=?", page, ub.getTeamId());
		retMap.put("data",page.getResult());
		retMap.put("total", page.getTotalCount());
		retMap.put("per_page", 20);
		retMap.put("current_page", page.getPageNo());
		retMap.put("last_page", page.getTotalPages());
		return retMap;
	}
	public Map add() {
		Map retMap = new HashMap();
		
		UserBean ub = getWxUser();
		if(ub == null) return null;
		
		final String title = StrUtil.formatNullStr(request.getParameter("title"));
		final String d = StrUtil.formatNullStr(request.getParameter("d"));
		
		final String sdt = StrUtil.formatNullStr(request.getParameter("sdt"));
		final String edt = StrUtil.formatNullStr(request.getParameter("edt"));
		
		final String time = StrUtil.formatNullStr(request.getParameter("time"));
		final String addr = StrUtil.formatNullStr(request.getParameter("addr"));
		
		final int freq_num = StrUtil.formatNullStrInt(request.getParameter("freqNum"), 0);
		final String freq_unit = StrUtil.formatNullStr(request.getParameter("freqUnit"),"none");
		
		final int minNum = StrUtil.formatNullStrInt(request.getParameter("minNum"), 0);
		
		
		String _actId = IdWorker.getInstance().uuid();
		boolean ret = TagJDBCInstance.getInstance().tx(new ITx() {
			
			@Override
			public boolean run(Connection conn) throws SQLException {
				try {
					boolean ret = true;
					M_actDAO act = new M_actDAO();
					act.setId(_actId);
					act.setTitle(title);
					act.setD(d);
					act.setStart_date(DateUtils.parseDate(sdt, "yyyy-MM-dd"));
					act.setEnd_date(DateUtils.parseDate(edt, "yyyy-MM-dd"));
					act.setFreq_num(freq_num);
					act.setFreq_unit(freq_unit);
					act.setTeamid(ub.getTeamId());
					act.setUid(ub.getUid());
					act.setAct_addr(addr);
					if(!time.equals(""))
						act.setAct_time(DateUtils.parseDate(time, "HH:mm"));
					act.setSts("1"); // 1：启用，有效状态  0：无效
					act.setMin_num(minNum);
					calCurrentAct(act);
					ret = ret && act.add(conn);
					if(ret) {
						JobActFinish.refreshActCache();
					}
					return ret;
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				return false;
			}
		});
		
		retMap.put("ret", ret);
		return retMap;
	}
	
	public Map sts() {
		UserBean ub = getWxUser();
		if(ub == null) return null;
		Map retMap = new HashMap();
		final String _actId = StrUtil.formatNullStr(request.getParameter("actId"));
		final String _actSts = StrUtil.formatNullStr(request.getParameter("actSts"));
		M_actDAO act = M_actDAO.dao.findById("*", _actId);
		act.setSts(_actSts);
		boolean ret = act.save();
		retMap.put("ret", ret);
		return retMap;
	}
	
	public Map remove() {
		UserBean ub = getWxUser();
		if(ub == null) return null;
		Map retMap = new HashMap();
		final String _actId = StrUtil.formatNullStr(request.getParameter("actId"));
		M_actDAO act = M_actDAO.dao.findById("*", _actId);
		if(act.getUid().equals(ub.getUid())) {
			boolean ret = act.delete();
			retMap.put("ret", ret);
		}else {
			retMap.put("ret", false);
		}
		return retMap;
	}
	
	public Map get() {
//		UserBean ub = getWxUser();
//		if(ub == null) return null;
		Map retMap = new HashMap();
		final String _actId = StrUtil.formatNullStr(request.getParameter("actId"));
		M_actDAO act = M_actDAO.dao.findById("*", _actId);
		retMap.put("act", act);
		return retMap;
	}
	
	public Map save() {
		UserBean ub = getWxUser();
		if(ub == null) return null;
		Map retMap = new HashMap();
		try {
			final String _actId = StrUtil.formatNullStr(request.getParameter("actId"));
			M_actDAO act = M_actDAO.dao.findById("*", _actId);
			if(!act.getUid().equals(ub.getUid())) {
				retMap.put("ret",false);
				return retMap;
			}
			act.setTitle(StrUtil.formatNullStr(request.getParameter("title")));
			act.setD(StrUtil.formatNullStr(request.getParameter("d")));
			act.setStart_date(DateUtils.parseDate(StrUtil.formatNullStr(request.getParameter("sdt")), "yyyy-MM-dd"));
			act.setEnd_date(DateUtils.parseDate(StrUtil.formatNullStr(request.getParameter("edt")), "yyyy-MM-dd"));
			act.setFreq_num(StrUtil.formatNullStrInt(request.getParameter("freqNum"),0));
			act.setFreq_unit(StrUtil.formatNullStr(request.getParameter("freqUnit")));
			//act.setTeamid(ub.getTeamId());
			//act.setUid(ub.getUid());
			act.setAct_addr(StrUtil.formatNullStr(request.getParameter("addr")));
			if(!StrUtil.formatNullStr(request.getParameter("time")).equals(""))
				act.setAct_time(DateUtils.parseDate(StrUtil.formatNullStr(request.getParameter("time")), "HH:mm"));
			act.setSts("1"); // 1：启用，有效状态  0：无效
			act.setMin_num(StrUtil.formatNullStrInt(request.getParameter("minNum"),0));
			calCurrentAct(act);
			boolean ret = act.save();
			if(ret) {
				JobActFinish.refreshActCache();
			}
			retMap.put("ret", ret);
		}catch(Exception ex) {
			ex.printStackTrace();
			retMap.put("ret",false);
		}
		return retMap;
	}
	
	public Map join() {
		UserBean ub = getWxUser();
		if(ub == null) return null;
		Map retMap = new HashMap();
		final String _actId = StrUtil.formatNullStr(request.getParameter("actId"));
		final String formId = StrUtil.formatNullStr(request.getParameter("formId"));
		M_actDAO act = M_actDAO.dao.findById("*", _actId);
		//把当前用户加入到活动表
		M_act_usersDAO user = new M_act_usersDAO();
		user.setId(IdWorker.getInstance().uuid());
		user.setUid(ub.getUid());
		user.setAct_id(_actId);
		user.setStart_date(act.getCur_start_date());
		user.setEnd_date(act.getCur_end_date());
		user.setSts("1");
		user.setForm_id(formId);
		boolean ret = user.add();
		retMap.put("ret", ret);
//		if(ret) {
//			String d = "报名参加 " + unitStr(act.getFreq_unit()) + " " + unitStr(act.getFreq_unit() , act.getFreq_num()) + " 的 " + act.getTitle() + " 活动成功，本活动需凑足 " + act.getMin_num() + " 人才生效，请等待通知.";
//			WXTemplateMsgManager.sendAlarm_ActStart(formId, ub.getUsername(), _actId, DateUtils.format(act.getAct_time(), "HH:mm"), act.getTitle(), d);
//		}
		return retMap;
	}
	
	public Map unjoin() {
		UserBean ub = getWxUser();
		if(ub == null) return null;
		Map retMap = new HashMap();
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		M_act_usersDAO actUser = M_act_usersDAO.dao.findById("*", id);
		boolean ret = actUser.delete();
		retMap.put("ret", ret);
		return retMap;
	}
	
	public static void calCurrentAct(M_actDAO act) {
		String unit = StrUtil.formatNullStr(act.getFreq_unit());
		Date today = new Date();
		
		Date startFreqDate = calDate(act.getStart_date(),act.getEnd_date(),act.getFreq_unit(),act.getFreq_num());
		if(unit.equals("w")) {
			act.setCur_start_date(startFreqDate);
			Date nextDay = DateUtils.addDay(act.getStart_date(), 7);
			act.setCur_end_date(nextDay);
			if(today.getTime()<act.getEnd_date().getTime()) {
				act.setCur_sts("1"); //生效
			}else {
				act.setCur_sts("0"); //已过期
			}
		}else if(unit.equals("2w")) {
			act.setCur_start_date(startFreqDate);
			Date nextDay = DateUtils.addDay(act.getStart_date(), 14);
			act.setCur_end_date(nextDay);
			if(today.getTime()<act.getEnd_date().getTime()) {
				act.setCur_sts("1"); //生效
			}else {
				act.setCur_sts("0"); //已过期
			}
		}else if(unit.equals("m")) {
			act.setCur_start_date(startFreqDate);
			Date nextDay = DateUtils.addMonth(act.getStart_date(), 1);
			act.setCur_end_date(nextDay);
			if(today.getTime()<act.getEnd_date().getTime()) {
				act.setCur_sts("1"); //生效
			}else {
				act.setCur_sts("0"); //已过期
			}
		}else {
			act.setCur_start_date(act.getStart_date());
			act.setCur_end_date(act.getEnd_date());
			if(today.getTime()<act.getEnd_date().getTime()) {
				act.setCur_sts("1"); //生效
			}else {
				act.setCur_sts("0"); //已过期
			}
		}
	}
	
	public static Date calDate(Date startDate,Date endDate,String unit,int freqNum) {
		Date d = new Date();
		long time = startDate.getTime();
		Calendar cal = Calendar.getInstance();
        while(time < endDate.getTime()) {
        	cal.setTime(new Date(time));
			if(unit.indexOf("w")>=0 && freqNum == cal.get(Calendar.DAY_OF_WEEK)-1) {
				return new Date(time);
			}else if(unit.equals("m") && freqNum == cal.get(Calendar.DAY_OF_MONTH)) {
				return new Date(time);
			}
			time = DateUtils.addDay(new Date(time), 1).getTime();
		}
        
		return null;
	}
	
	public static void main(String[] args) {
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(d);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	}
	
	public static String unitStr(String unit) {
		unit = StrUtil.formatNullStr(unit);
		if(unit.equals("w")) {
			return "本周";
		}else if(unit.equals("2w")) {
			return "本双周";
		}else if(unit.equals("m")){
			return "本月";
		}
		return "";
	}
	public static String unitStr(String unit,int num) {
		String[] week = new String[] {"周日","周一","周二","周三","周四","周五","周六","周日"};
		if(unit.indexOf("w")>=0) {
			return week[num];
		}else if(unit.equals("m")) {
			return num + "号";
		}
		return "";
	}
}
