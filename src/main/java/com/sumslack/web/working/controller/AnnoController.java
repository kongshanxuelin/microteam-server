package com.sumslack.web.working.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_annoDAO;

@URIAlias("anno")
public class AnnoController extends CController{
	public Map publish() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String title = StrUtil.formatNullStr(request.getParameter("t"));
		final String content = StrUtil.formatNullStr(request.getParameter("c"));
		final String id = IdWorker.getInstance().uuid();
		M_work_annoDAO anno = new M_work_annoDAO();
		anno.setID(id);
		anno.setTitle(title);
		anno.setContent(content);
		anno.setCompany_id(ub.getTeamId());
		anno.setCreate_uid(ub.getUid());
		anno.setModify_uid(ub.getUid());
		anno.setCreate_time(new Date());
		boolean res = anno.add();
		retMap.put("ret", res);
		retMap.put("anno", M_work_annoDAO.dao.findById("*", id));
		return retMap;
	}
	
	public Map remove(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		boolean res = M_work_annoDAO.dao.deleteById(id);
		retMap.put("ret", res);
		return retMap;
	}
	
	public List<M_work_annoDAO> list(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		
		return M_work_annoDAO.dao.find("select ID,title,content,create_time from " + 
				M_work_annoDAO.dao.getTable().getName() + " where company_id=?",ub.getTeamId());
	}
}
