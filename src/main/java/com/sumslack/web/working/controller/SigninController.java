package com.sumslack.web.working.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.HttpUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_signinDAO;
import com.sumslack.web.working.dao.M_work_signin_recordDAO;

@URIAlias("sign")
public class SigninController extends CController{
	public Map settings() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		final String workon_time = StrUtil.formatNullStr(request.getParameter("workon_time"));
		final String workoff_time = StrUtil.formatNullStr(request.getParameter("workoff_time"));
		final String work_week= StrUtil.formatNullStr(request.getParameter("work_week"));
		final String scope= StrUtil.formatNullStr(request.getParameter("scope"));
		
		final String let= StrUtil.formatNullStr(request.getParameter("let"));
		final String lot= StrUtil.formatNullStr(request.getParameter("lot"));
		
		final String sdt= StrUtil.formatNullStr(request.getParameter("sdt"));
		final String edt= StrUtil.formatNullStr(request.getParameter("edt"));
		
		M_work_signinDAO ww = M_work_signinDAO.dao.findFirst("select * from  " + M_work_signinDAO.dao.getTable().getName() + " where company_id=?",ub.getTeamId());
		if(ww==null){
			String id = IdWorker.getInstance().uuid();
			M_work_signinDAO ws = new M_work_signinDAO();
			ws.setId(id);
			ws.setCompany_id(ub.getTeamId());
			ws.setWorkon_time(DateUtils.parseDate(workon_time, "HH:mm"));
			ws.setWorkoff_time(DateUtils.parseDate(workoff_time, "HH:mm"));
			ws.setCreate_uid(ub.getUid());
			ws.setModify_uid(ub.getUid());
			ws.setCreate_time(new Date());
			ws.setWork_week(work_week);
			ws.setLet(new BigDecimal(let));
			ws.setLot(new BigDecimal(lot));
			ws.setError_scope(new BigDecimal(scope));
			ws.setStart_date(DateUtils.parseDate(sdt, DateUtils.fmt_dateFormat));
			ws.setEnd_date(DateUtils.parseDate(edt, DateUtils.fmt_dateFormat));
			boolean res =ws.add();
			retMap.put("ret", res);
		}else{
			ww.setWorkon_time(DateUtils.parseDate(workon_time, "HH:mm"));
			ww.setWorkoff_time(DateUtils.parseDate(workoff_time, "HH:mm"));
			ww.setModify_uid(ub.getUid());
			ww.setWork_week(work_week);
			ww.setLet(new BigDecimal(let));
			ww.setLot(new BigDecimal(lot));
			ww.setError_scope(new BigDecimal(scope));
			ww.setStart_date(DateUtils.parseDate(sdt, DateUtils.fmt_dateFormat));
			ww.setEnd_date(DateUtils.parseDate(edt, DateUtils.fmt_dateFormat));
			boolean res =ww.save();
			retMap.put("ret", res);
		}
		return retMap;
	}
	
	public Map stat(){
		Map<String,Map<String,Object>> userMap = new HashMap();
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		String sql = "select uid,create_time,direct_ from m_work_signin_record "+
					 "where company_id=? and DATE_FORMAT(create_time,'%Y-%m')=DATE_FORMAT(CURRENT_TIMESTAMP,'%Y-%m')";
		List<M_work_signin_recordDAO> lists = M_work_signin_recordDAO.dao.find(sql, ub.getTeamId());
		
		M_work_signinDAO ws = M_work_signinDAO.dao.findFirst("select * from " + M_work_signinDAO.dao.getTable().getName()+" where company_id=?",ub.getTeamId());
		long workonTime = ws.getWorkon_time().getTime();
		long workoffTime = ws.getWorkoff_time().getTime();
		if(lists!=null){
			for(M_work_signin_recordDAO r : lists){
				String _uid = r.getUid();
				if(userMap.get(_uid)==null){
					Map _map = new HashMap<String,Object>();
					_map.put("total", 0);
					_map.put("later", 0);
					_map.put("early", 0);
					userMap.put(_uid,_map);
				}
				userMap.get(_uid).put("nick", Global.getNick(_uid));
				userMap.get(_uid).put("total", StrUtil.formatNullStrInt(userMap.get(_uid).get("total"), 0)+1);
				try {
					Date _date = DateUtils.parseDate(DateUtils.format(r.getCreate_time(),"HH:mm:ss"), "HH:mm:ss");
					if(r.getDirect_().equals("0") && _date.getTime()>workonTime){ //迟到
						userMap.get(_uid).put("later", StrUtil.formatNullStrInt(userMap.get(_uid).get("later"), 0)+1);
					}else if(r.getDirect_().equals("1") && _date.getTime()<workoffTime){ //早退
						userMap.get(_uid).put("early", StrUtil.formatNullStrInt(userMap.get(_uid).get("early"), 0)+1);
					}
				} catch (Exception e) {
				}
			}
		}
		return userMap;
	}
	public Map get(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		retMap.put("ret", true);
		M_work_signinDAO d = M_work_signinDAO.dao.findFirst("select * from "+ M_work_signinDAO.dao.getTable().getName()+" where company_id=? " + 
		" and CURRENT_TIMESTAMP() BETWEEN start_date and end_date",ub.getTeamId());
		if(d!=null){
			retMap.put("workon_time", DateUtils.format(d.getWorkon_time(),"HH:mm:ss"));
			retMap.put("workoff_time", DateUtils.format(d.getWorkoff_time(),"HH:mm:ss"));
			retMap.put("let", d.getLet());
			retMap.put("lot", d.getLot());
			retMap.put("week", d.getWork_week());
			retMap.put("scope", d.getError_scope());
			retMap.put("startDate", DateUtils.format(d.getStart_date(),DateUtils.fmt_dateFormat));
			retMap.put("endDate", DateUtils.format(d.getEnd_date(),DateUtils.fmt_dateFormat));
		}
		//当前我的签到情况
		M_work_signinDAO ws = M_work_signinDAO.dao.findFirst("select id from " + M_work_signinDAO.dao.getTable().getName()+" where company_id=?",ub.getTeamId());
		if(ws!=null){
			M_work_signin_recordDAO wr = M_work_signin_recordDAO.dao.findFirst("select id,create_time from " + M_work_signin_recordDAO.dao.getTable().getName() + " where direct_='0' "
					+ "and uid=? and DATE_FORMAT(create_time,'%Y-%m-%d') = DATE_FORMAT(CURRENT_TIMESTAMP,'%Y-%m-%d')",ub.getUid());
			if(wr!=null){
				retMap.put("signin_time", DateUtils.format(wr.getCreate_time(), "HH:mm:ss"));
			}
			M_work_signin_recordDAO wr2 = M_work_signin_recordDAO.dao.findFirst("select id,create_time from " + M_work_signin_recordDAO.dao.getTable().getName() + " where direct_='1' "
					+ "and uid=? and DATE_FORMAT(create_time,'%Y-%m-%d') = DATE_FORMAT(CURRENT_TIMESTAMP,'%Y-%m-%d')",ub.getUid());
			if(wr2!=null){
				retMap.put("signout_time", DateUtils.format(wr2.getCreate_time(), "HH:mm:ss"));
			}
			
		}
		return retMap;
	}
	
	public Map signin() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		final String let = StrUtil.formatNullStr(request.getParameter("let"));
		final String lot = StrUtil.formatNullStr(request.getParameter("lot"));
		
		M_work_signinDAO ww = M_work_signinDAO.dao.findFirst("select * from "+ M_work_signinDAO.dao.getTable().getName()+" where company_id=? " + 
				" and CURRENT_TIMESTAMP() BETWEEN start_date and end_date",ub.getTeamId());
		if(ww!=null){
			//判断是否今天已签到
			M_work_signin_recordDAO rec = M_work_signin_recordDAO.dao.findFirst("select * from " + M_work_signin_recordDAO.dao.getTable().getName() + " where direct_='0' and company_id=? and signin_settings_id=? and uid=? and DATE_FORMAT(create_time,'%Y-%m-%d') = DATE_FORMAT(CURRENT_TIMESTAMP,'%Y-%m-%d')",
					ub.getTeamId(),ww.getId(),ub.getUid());
			if(rec!=null){
				retMap.put("msg", "今日已签到！");
				return retMap;
			}
			double s = getDistance(ww.getLot().floatValue(),ww.getLet().floatValue(), new BigDecimal(lot).floatValue(), new BigDecimal(let).floatValue());
			retMap.put("scope", s);
			if(s < ww.getError_scope().floatValue()){
				retMap.put("ret", true);
				String _id = IdWorker.getInstance().uuid();
				M_work_signin_recordDAO r = new M_work_signin_recordDAO();
				r.setId(_id);
				r.setCompany_id(ub.getTeamId());
				r.setUid(ub.getUid());
				r.setModify_uid(ub.getUid());
				r.setCreate_uid(ub.getUid());
				r.setCreate_time(new Date());
				r.setDirect_("0");
				r.setIp(HttpUtils.getIP(request));
				r.setInfo("");
				r.setSignin_settings_id(ww.getId());
				r.add();
				retMap.put("sign", M_work_signin_recordDAO.dao.findById("*", _id));
				
				//计算签到的时间差
				Date cc = new Date();
				Date dt = DateUtils.parseDate(DateUtils.format(cc,DateUtils.fmt_dateFormat) + " " + ww.getWorkon_time(), DateUtils.fmt_datetimeFormat);
				long sec = (cc.getTime() - dt.getTime())/1000;
				retMap.put("sec", sec);
			}else{
				retMap.put("msg", "距离太远，不允许签到.");
			}
		}
		return retMap;
	}
	
	public Map signout() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		M_work_signinDAO ww = M_work_signinDAO.dao.findFirst("select * from "+ M_work_signinDAO.dao.getTable().getName()+" where company_id=? " + 
				" and CURRENT_TIMESTAMP() BETWEEN start_date and end_date",ub.getTeamId());
		if(ww!=null){
			M_work_signin_recordDAO rec = M_work_signin_recordDAO.dao.findFirst("select * from " + M_work_signin_recordDAO.dao.getTable().getName() + " where direct_='1' and company_id=? and signin_settings_id=? and uid=? and DATE_FORMAT(create_time,'%Y-%m-%d') = DATE_FORMAT(CURRENT_TIMESTAMP,'%Y-%m-%d')",
					ub.getTeamId(),ww.getId(),ub.getUid());
			if(rec!=null){
				retMap.put("msg", "今日已签出！");
				return retMap;
			}
			String _id = IdWorker.getInstance().uuid();
			M_work_signin_recordDAO r = new M_work_signin_recordDAO();
			r.setId(_id);
			r.setCompany_id(ub.getTeamId());
			r.setUid(ub.getUid());
			r.setModify_uid(ub.getUid());
			r.setCreate_uid(ub.getUid());
			r.setCreate_time(new Date());
			r.setDirect_("1");
			r.setIp(HttpUtils.getIP(request));
			r.setInfo("");
			r.setSignin_settings_id(ww.getId());
			boolean res = r.add();
			retMap.put("ret", res);
			
			//计算签到的时间差
			Date cc = new Date();
			Date dt = DateUtils.parseDate(DateUtils.format(cc,DateUtils.fmt_dateFormat) + " " + ww.getWorkoff_time(), DateUtils.fmt_datetimeFormat);
			long sec = (dt.getTime() - cc.getTime())/1000;
			retMap.put("sec", sec);
			
		}
		return retMap;
	}
	
	private static final double PI = 3.14159265358979323; //圆周率
    private static final double R = 6371229;             //地球的半径
    
    private static double getDistance(double longt1, double lat1, double longt2, double lat2){
        double x,y, distance;
        x=(longt2-longt1)*PI*R*Math.cos( ((lat1+lat2)/2)*PI/180)/180;
        y=(lat2-lat1)*PI*R/180;
        distance=Math.hypot(x,y);
        return distance;
    }
	
}
