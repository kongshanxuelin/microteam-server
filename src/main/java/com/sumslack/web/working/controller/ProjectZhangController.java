package com.sumslack.web.working.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.bean.TagPage;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_projects_zhangDAO;
import com.sumslack.web.working.dao.M_work_projects_zhang_cateDAO;
import com.sumslack.web.working.service.ProjectService;
import com.sumslack.web.working.service.UserService;
import com.sun.javafx.util.Utils;

@URIAlias("project/cals")
public class ProjectZhangController extends CController{
	public Map list() throws Exception{
		
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		int p = StrUtil.formatNullStrInt(request.getParameter("p"), 1);
		//int d = StrUtil.formatNullStrInt(request.getParameter("d"), 0); //0:收入 1：支出
		String prjid = StrUtil.formatNullStr(request.getParameter("prjid"));
		
		
		TagPage page = new TagPage();
		TagPage.init(page, p,1000);
		
		Map retMap = new HashMap();
		retMap.put("prjid", prjid);
		retMap.put("pro", ProjectService.getInstance().getProject(prjid));
		//retMap.put("d", d);
		
		List retList = new ArrayList();
		
		M_work_projects_zhangDAO.dao.findPage("select * from " + M_work_projects_zhangDAO.dao.getTable().getName() + " where prj_id=? order by dt", 
				page,prjid);

		
		if(page.getResult()!=null && page.getResult().size()>0){
			for(Object t2 : page.getResult()){
				M_work_projects_zhangDAO t = (M_work_projects_zhangDAO)t2;
				retList.add(getZhang(t.getId()));
			}
		}
		page.setResult(retList);
		retMap.put("page", page);
		
		//总收入，总支出，总结余
		float sr = 0,zc = 0;
		String shouru = "select sum(je) num from " + M_work_projects_zhangDAO.dao.getTable().getName() + " where prj_id=? and d=0";
		Map srMap =TagJDBCInstance.getInstance().queryOne(shouru, new Object[] {prjid});
		if(srMap!=null) {
			sr = Float.parseFloat(StrUtil.formatNullStr(srMap.get("num"), "0"));
		}
		String zhichu = "select sum(je) num from " + M_work_projects_zhangDAO.dao.getTable().getName() + " where prj_id=? and d=1";
		Map zhichuMap =TagJDBCInstance.getInstance().queryOne(zhichu, new Object[] {prjid});
		if(zhichuMap!=null) {
			zc = Float.parseFloat(StrUtil.formatNullStr(zhichuMap.get("num"), "0"));
		}
		retMap.put("zsr", String.format("%.2f", sr));
		retMap.put("zzc", String.format("%.2f", zc));
		retMap.put("zyy", String.format("%.2f", sr-zc));
		return retMap;
	}
	
	public Map cate() throws Exception{
		List listShouru = TagJDBCInstance.getInstance().queryList("select id,title from " + M_work_projects_zhang_cateDAO.dao.getTable().getName() + " where d=0",null);
		List listZhichu = TagJDBCInstance.getInstance().queryList("select id,title from " + M_work_projects_zhang_cateDAO.dao.getTable().getName() + " where d=1",null);
		Map retMap = new HashMap();
		retMap.put("sr", listShouru);
		retMap.put("zc", listZhichu);
		return retMap;
	}
	
	public Map get() {
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		return getZhang(id);
	}
	
	private Map getZhang(String id) {
		M_work_projects_zhangDAO zhang = M_work_projects_zhangDAO.dao.findById("*", id);
		if(zhang!=null) {
			Map retMap = new HashMap();
			retMap.put("id", id);
			retMap.put("prjid", zhang.getPrj_id());
			retMap.put("dd", zhang.getDescription());
			retMap.put("d", zhang.getD());
			retMap.put("cid", zhang.getCate_id());
			retMap.put("cname", M_work_projects_zhang_cateDAO.dao.findById("title", zhang.getCate_id()));
			retMap.put("fzr", UserService.getInstance().getUserByUid(zhang.getFzr()));
			String joiners = StrUtil.formatNullStr(zhang.getJoiner());
			if(!joiners.equals("")) {
				String[] joinArray = Utils.split(joiners, ",");
				List joinList = new ArrayList();
				for(String _jer : joinArray) {
					if(!_jer.equals("")) {
						joinList.add(UserService.getInstance().getUserByUid(_jer));
					}
				}
				retMap.put("joiner", joinList);
			}
			retMap.put("dt", zhang.getDt().getTime());
			retMap.put("je", zhang.getJe().floatValue());
			return retMap;
		}
		return null;
	}
	
	public Map add() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String prjid = StrUtil.formatNullStr(request.getParameter("prjid"));
		final String cid = StrUtil.formatNullStr(request.getParameter("cid"));
		final String dd = StrUtil.formatNullStr(request.getParameter("dd"));
		final int d = StrUtil.formatNullStrInt(request.getParameter("d"), 0);//默认是项目收入
		
		final String je = StrUtil.formatNullStr(request.getParameter("je"));
		
		final String fzr = StrUtil.formatNullStr(request.getParameter("fzr"));
		final String joiners = StrUtil.formatNullStr(request.getParameter("joiners"));
		
		M_work_projects_zhangDAO zhang = new M_work_projects_zhangDAO();
		String id = IdWorker.getInstance().uuid();
		zhang.setId(id);
		zhang.setPrj_id(prjid);
		zhang.setD(d);
		zhang.setCate_id(Integer.parseInt(cid));
		zhang.setDescription(dd);
		zhang.setJe(new BigDecimal(je));
		zhang.setFzr(fzr);
		zhang.setJoiner(joiners);
		zhang.setSts("0");
		boolean ret = zhang.add();
		retMap.put("ret", ret);
		retMap.put("zhang", getZhang(id));
		return retMap;
	}
	
	
	public Map save() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		final String cid = StrUtil.formatNullStr(request.getParameter("cid"));
		final String dd = StrUtil.formatNullStr(request.getParameter("dd"));
		
		final String je = StrUtil.formatNullStr(request.getParameter("je"));
		
		final String fzr = StrUtil.formatNullStr(request.getParameter("fzr"));
		final String joiners = StrUtil.formatNullStr(request.getParameter("joiners"));
		
		M_work_projects_zhangDAO zhang = M_work_projects_zhangDAO.dao.findById("*", id);
		boolean res = false;
		if(zhang!=null) {
			zhang.setCate_id(Integer.parseInt(cid));
			zhang.setDescription(dd);
			zhang.setJe(new BigDecimal(je));
			zhang.setFzr(fzr);
			zhang.setJoiner(joiners);
			res = zhang.save();
		}
		retMap.put("ret", res);
		retMap.put("zhang", getZhang(id));
		return retMap;
	}
	
	public Map remove() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return retMap;
		}
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		boolean res =  M_work_projects_zhangDAO.dao.deleteById(id);
		retMap.put("ret", res);
		return retMap;
	}
}
