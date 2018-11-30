package com.sumslack.web.working.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.bean.TagPage;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.dao.M_work_tmpl_recordsDAO;
import com.sumslack.web.working.service.TmplService;

@URIAlias("note")
public class NoteController extends CController{
	//显示我的团队下所有成员的工作日志
	public Map list(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		String sdt = StrUtil.formatNullStr(request.getParameter("sdt"));
		if(sdt.equals("")) sdt = "2000-01-01 00:00:00";
		String edt = StrUtil.formatNullStr(request.getParameter("edt"));
		if(edt.equals("")) edt = "2100-01-01 00:00:00";
		int p = StrUtil.formatNullStrInt(request.getParameter("p"), 1);
		TagPage page = new TagPage();
		page.init(page, p, 20);
		M_work_tmpl_recordsDAO.dao.findPage("select * from " + M_work_tmpl_recordsDAO.dao.getTable().getName()+ " where tmpl_id in (select id from m_work_tmpl where tmpl_cateid='log') and (create_time between ? and ?) "
				+ "and create_uid in (select uid from users where company_id=?)", 
				page, sdt,edt,ub.getTeamId());
		
		List<M_work_tmpl_recordsDAO> result = page.getResult();
		List resultList = new ArrayList();
		
		for(M_work_tmpl_recordsDAO r : result){
			Map _map = new HashMap();
			_map.put("uid", r.getCreate_uid());
			_map.put("time", DateUtils.format(r.getCreate_time(),"MM-dd HH:mm"));
			_map.put("nick", Global.getNick(r.getCreate_uid()));
			_map.put("id", r.getId());
			_map.put("tmpl", TmplService.getInstance().getTmplTitle(r.getTmpl_id()));
			List<M_work_tmpl_fields_valueDAO> vvList = M_work_tmpl_fields_valueDAO.dao.find("select * from " + M_work_tmpl_fields_valueDAO.dao.getTable().getName() + " where tmpl_id_id=?", r.getId());
			for(M_work_tmpl_fields_valueDAO vv : vvList){
				_map.put(vv.getField_id(), vv.getField_value());
			}
			resultList.add(_map);
		}
		page.setResult(resultList);
		retMap.put("ret", true);
		retMap.put("page", page);
		return retMap;
	}
}
