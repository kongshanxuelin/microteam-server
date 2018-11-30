package com.sumslack.web.working.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.bean.TagPage;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_tmpl_fieldsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.dao.M_work_tmpl_recordsDAO;
import com.sumslack.web.working.dao.Users_data_purviewDAO;
import com.sumslack.web.working.handler.AbsFieldValueHandler;
import com.sumslack.web.working.handler.ExpressionFieldValueHandler;
import com.sumslack.web.working.handler.SQLFieldValueHandler;
import com.sumslack.web.working.service.TmplService;
@URIAlias("page")
public class PageRouteController extends CController{
	
	public String home(){
		return "/home.jsp";		
	}

	@URIAlias("eform-add/{id}")
	public String eformEdit(String id){
		request.setAttribute("tmpl",TmplService.getInstance().getTmpl(id));
		String recId = StrUtil.formatNullStr(request.getParameter("recId"));//主表记录ID
		request.setAttribute("recId", recId);
		request.setAttribute("inc", StrUtil.formatNullStr(request.getParameter("inc")));
		return "/view/add.jsp";
	}
	
	@URIAlias("eform-get/{id}")
	public String eformGet(String id){
		String recId = StrUtil.formatNullStr(request.getParameter("recId"));//主表记录ID
		request.setAttribute("recId", recId);
		request.setAttribute("inc", StrUtil.formatNullStr(request.getParameter("inc")));
		List<M_work_tmpl_fields_valueDAO> vs = TmplService.getInstance().getData(id);
		M_work_tmpl_recordsDAO tmpl = M_work_tmpl_recordsDAO.dao.findById("*", id);
		if(tmpl!=null){
			request.setAttribute("tmpl", TmplService.getInstance().getTmpl(tmpl.getTmpl_id()));
			Map retMap = TmplService.getInstance().getTmplDdata(tmpl.getTmpl_id(), vs);
			request.setAttribute("data",retMap);
		}
		request.setAttribute("id", id);
		return "/view/edit.jsp";
	}
	
	//跳转到详情页面
	@URIAlias("eform-view/{id}")
	public String eformView(String id){
		Map viewMap = TmplService.getInstance().getTmpl(id);
		if(viewMap!=null){
			request.setAttribute("id", id);
			return "/view/vpages/"+StrUtil.formatNullStr(viewMap.get("view_page"));
		}else{
			return "arg is error!";
		}
	}
	@URIAlias("eform-remove/{id}")
	public boolean eformRemove(String id){
		return M_work_tmpl_recordsDAO.dao.deleteById(id);
	}
	
	@URIAlias("eform/one")
	public Map eformOne(){
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		List<M_work_tmpl_fields_valueDAO> vs = TmplService.getInstance().getData(id);
		M_work_tmpl_recordsDAO tmpl = M_work_tmpl_recordsDAO.dao.findById("*", id);
		if(tmpl!=null){
			request.setAttribute("tmpl", TmplService.getInstance().getTmpl(tmpl.getTmpl_id()));
			Map retMap = TmplService.getInstance().getTmplDdata(tmpl.getTmpl_id(), vs);
			return retMap;
		}
		return null;
	}
	
	@URIAlias("eform/list/{tmplId}")
	public String eformList(String tmplId){
		request.setAttribute("tmplId", tmplId);
		String recId = StrUtil.formatNullStr(request.getParameter("recId"));//主表记录ID
		request.setAttribute("recId", recId);
		Map tmplMap = TmplService.getInstance().getTmpl(tmplId);
		if(tmplMap!=null){
			request.setAttribute("title", StrUtil.formatNullStr(tmplMap.get("title")));
			request.setAttribute("childs", JSON.toJSONString(tmplMap.get("childs")));
			request.setAttribute("view_page",StrUtil.formatNullStr(tmplMap.get("view_page")));
			request.setAttribute("pTmplId", StrUtil.formatNullStr(tmplMap.get("pid")));
		}
		return "/view/list.jsp";
	}
	
	@URIAlias("eform/listChild/{tmplId}")
	public String eformListChild(String tmplId){
		request.setAttribute("tmplId", tmplId);
		String recId = StrUtil.formatNullStr(request.getParameter("recId"));//主表记录ID
		request.setAttribute("recId", recId);
		Map tmplMap = TmplService.getInstance().getTmpl(tmplId);
		if(tmplMap!=null){
			request.setAttribute("title", StrUtil.formatNullStr(tmplMap.get("title")));
			request.setAttribute("childs", JSON.toJSONString(tmplMap.get("childs")));
			request.setAttribute("pTmplId", StrUtil.formatNullStr(tmplMap.get("pid")));
		}
		return "/view/list-child.jsp";
	}
	/*
	 * 加入谁录谁看
	 */
	@URIAlias("eform/datatable")
	public Map dataList() throws Exception{
		final UserBean ub = getSessUser();
		
		Map retMap = new HashMap();
		int p = StrUtil.formatNullStrInt(request.getParameter("p"),1);
		String tmplId = StrUtil.formatNullStr(request.getParameter("tmplId"));
		String recId = StrUtil.formatNullStr(request.getParameter("recId"));
		boolean isChildForm = (!recId.equals(""));
		TagPage page = new TagPage();
		page.init(page, p, 20);
		if(isChildForm){
			M_work_tmpl_recordsDAO.dao.findPage("select * from " + M_work_tmpl_recordsDAO.dao.getTable().getName() + " where tmpl_id=? and rec_id=?", 
					page,tmplId,recId);
		}else{
			//如果是表单的拥有者，则默认能查看所有用户数据
			Map _tmplMap = TmplService.getInstance().getTmpl(tmplId);
			if(_tmplMap!=null && StrUtil.formatNullStr(_tmplMap.get("creater")).equals(ub.getUid())){
				M_work_tmpl_recordsDAO.dao.findPage("select * from " + M_work_tmpl_recordsDAO.dao.getTable().getName() + " where tmpl_id=?", 
						page,tmplId);
			}else{
				M_work_tmpl_recordsDAO.dao.findPage("select * from " + M_work_tmpl_recordsDAO.dao.getTable().getName() + " where tmpl_id=? and (create_uid=? or create_uid "
						+ "in (select touid from "+Users_data_purviewDAO.dao.getTable().getName()+" where uid=?))", 
						page,tmplId,(ub!=null?ub.getUid():null),(ub!=null?ub.getUid():null));
			}
			
		}
		Map tmplMap = TmplService.getInstance().getTmpl(tmplId);
		if(page.getResult()!=null){
			List retList = new ArrayList();
			for(Object rec : page.getResult()){
				M_work_tmpl_recordsDAO r = (M_work_tmpl_recordsDAO)rec;
				if(r!=null){
					Map m = new HashMap();
					String _id = r.getId();
					m.put("id", _id);
					List<M_work_tmpl_fields_valueDAO> fv = TmplService.getInstance().getData(_id);
					if(fv!=null){
						for(M_work_tmpl_fields_valueDAO wt : fv){
							AbsFieldValueHandler handler = new SQLFieldValueHandler();
							AbsFieldValueHandler exprHandler = new ExpressionFieldValueHandler();
							handler.setSuccessor(exprHandler);
							m.put(wt.getField_id(), handler.handlerValue(wt));
						}
					}
					//创建者姓名
					m.put("nick", Global.getNick(r.getCreate_uid()));
					m.put("time", DateUtils.format(r.getCreate_time(),DateUtils.fmt_datetimeFormat));
					//判断当前用户对于数据的权限
					m.put("purview", getRecordPurview(ub,_id));
					m.put("view_page", tmplMap!=null?StrUtil.formatNullStr(tmplMap.get("view_page")):"");
					retList.add(m);
				}
			}
			page.setResult(retList);
		}
		retMap.put("recordsFiltered", page.getTotalCount());
		retMap.put("recordsTotal", page.getTotalCount());
		retMap.put("draw", "1");
		retMap.put("data", page.getResult());
		
		
		return retMap;
	}
	
	private Map getRecordPurview(UserBean ub,String recId){
		Map retMap = new HashMap();
		if(ub == null) return null;
		M_work_tmpl_recordsDAO r = M_work_tmpl_recordsDAO.dao.findById("*", recId);
		if(r!=null){
			if(r.getCreate_uid()!=null && r.getCreate_uid().equals(ub.getUid())){
				retMap.put("isedit", true);
				retMap.put("isview", true);
				retMap.put("isremove", true);
			}else{
				Users_data_purviewDAO data = Users_data_purviewDAO.dao.findFirst("select * from " + Users_data_purviewDAO.dao.getTable().getName() + " where tmplid=?"
						+ " and touid=? and uid=?",r.getTmpl_id(),r.getCreate_uid(),ub.getUid());
				if(data!=null){
					retMap.put("isedit", StrUtil.formatNullStr(data.getIsedit()).equals("Y"));
					retMap.put("isview", StrUtil.formatNullStr(data.getIsview()).equals("Y"));
					retMap.put("isremove", StrUtil.formatNullStr(data.getIsremove()).equals("Y"));
				}else{
					retMap.put("isedit", false);
					retMap.put("isview", false);
					retMap.put("isremove", false);
				}
			}
			return retMap;
		}
		return null;
	}
	
	@URIAlias("eform/tmpl")
	public String templManager(){
		String pid = StrUtil.formatNullStr(request.getParameter("pid"));
		if(!pid.equals("")){
			Map tmplMap = TmplService.getInstance().getTmpl(pid);
			request.setAttribute("tmpl", tmplMap);
		}
		request.setAttribute("pid", pid);
		return "/view/tmpl.jsp";
	}
	@URIAlias("eform/tmplEdit/{id}")
	public String templEdit(String id){
		String pid = StrUtil.formatNullStr(request.getParameter("pid"));
		request.setAttribute("pid", pid);
		request.setAttribute("id", id);
		if(!id.equals("")){
			Map tmplMap = TmplService.getInstance().getTmpl(id);
			request.setAttribute("tmpl", tmplMap);
		}
		return "/view/tmpl-edit.jsp";
	}
}
