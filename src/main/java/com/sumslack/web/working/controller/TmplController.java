package com.sumslack.web.working.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_tmplDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fieldsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_recordsDAO;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.dao.Users_data_purviewDAO;
import com.sumslack.web.working.service.PurviewService;
import com.sumslack.web.working.service.TmplService;
import com.sumslack.web.working.service.UserService;

@URIAlias("tmpl")
public class TmplController extends CController{
	
	public Map dataTable() {
		Map retMap = new HashMap();
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		String pid = StrUtil.formatNullStr(request.getParameter("pid"));
		retMap.put("data",TmplService.getInstance().getMyTmplList(ub, pid));
		return retMap;
	}
	public List getTmplUserDataPurviewList(){
		String tmpl = StrUtil.formatNullStr(request.getParameter("tmplId"));
		return PurviewService.getInstance().listTmplUserDataPurview(tmpl);
	}
	public List getTmplUserPurviewList() {
		String tmpl = StrUtil.formatNullStr(request.getParameter("tmplId"));
		return PurviewService.getInstance().listTmplUserPurview(tmpl);
	}
	public List getTmplList() {
		String cateid = param("cateid");
		return M_work_tmplDAO.dao.find("select id,title from " + M_work_tmplDAO.dao.getTable().getName() + " where tmpl_cateid=? and delflag<>'1'",cateid);
	}
	public boolean removeGrant(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return false;
		}
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		return PurviewService.getInstance().removeGrantTmpl(ub.getUid(), id);
	}
	public boolean addGrant(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return false;
		}
		String username = StrUtil.formatNullStr(request.getParameter("username"));
		UsersDAO _user = UserService.getInstance().getUser(username);
		if(_user!=null){
			String tmplId = StrUtil.formatNullStr(request.getParameter("tmplId"));
			String isedit = StrUtil.formatNullStr(request.getParameter("isedit"));
			String isremove = StrUtil.formatNullStr(request.getParameter("isremove"));
			return PurviewService.getInstance().addTmplPurview(_user.getUid(), tmplId, isedit.equals("Y"), true, isremove.equals("Y"), false);
		}
		return false;
	}
	public boolean grantUserData(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return false;
		}
		String tmplId = StrUtil.formatNullStr(request.getParameter("tmplId"));
		String isview = StrUtil.formatNullStr(request.getParameter("isview"));
		String isedit = StrUtil.formatNullStr(request.getParameter("isedit"));
		String isremove = StrUtil.formatNullStr(request.getParameter("isremove"));
		String uid = StrUtil.formatNullStr(request.getParameter("uid"));
		String uids = StrUtil.formatNullStr(request.getParameter("uids"));
		return PurviewService.getInstance().grantUserData(uid, uids, tmplId, isedit.equals("Y"), isremove.equals("Y"), isview.equals("Y"));
	}
	public boolean grantUserDataRemove(){
		final UserBean ub = getSessUser();
		if(ub==null){
			return false;
		}
		return Users_data_purviewDAO.dao.deleteById(StrUtil.formatNullStr(request.getParameter("id")));
	}
	public Map add() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return retMap;
		}
		final String tmplId = IdWorker.getInstance().uuid();
		
		final String cid = StrUtil.formatNullStr(request.getParameter("cid"));
		final String pid = StrUtil.formatNullStr(request.getParameter("pid"));

		final String title = StrUtil.formatNullStr(request.getParameter("t"));
		final String content = StrUtil.formatNullStr(request.getParameter("c"));
		
		final String header = StrUtil.formatNullStr(request.getParameter("header"));
		final String footer = StrUtil.formatNullStr(request.getParameter("footer"));
		final String form_js = StrUtil.formatNullStr(request.getParameter("form_js"));
		final String view_page = StrUtil.formatNullStr(request.getParameter("view_page"));
		
		
		
		final String[] field_titles = request.getParameterValues("f_title");
		final String[] field_suf = request.getParameterValues("f_suf");
		final String[] field_isreq = request.getParameterValues("f_isreq");
		final String[] field_ui_componet = request.getParameterValues("f_ui");
		final String[] field_ui_other = request.getParameterValues("f_ui_other");

		final String[] field_default = request.getParameterValues("f_default");
		final String[] field_min = request.getParameterValues("f_min");
		final String[] field_max = request.getParameterValues("f_max");
		final String[] f_showlist = request.getParameterValues("f_showlist");
		final String[] f_attrs = request.getParameterValues("f_attrs");
		
		
		
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				M_work_tmplDAO tmpl = new M_work_tmplDAO();
				tmpl.setId(tmplId);
				tmpl.setTitle(title);
				tmpl.setTmpl_content(content);
				tmpl.setTmpl_cateid(cid);
				tmpl.setDesc_header(header);
				tmpl.setDesc_footer(footer);
				tmpl.setSts("1");
				tmpl.setPid(pid);
				tmpl.setOrd_(1);
				tmpl.setCreate_uid(ub.getUid());
				tmpl.setDelflag("0");
				tmpl.setView_page(view_page);
				tmpl.setForm_js(form_js);
				res = res && tmpl.add(conn);
				if(field_titles!=null){
					for(int i=0;i<field_titles.length;i++){
						M_work_tmpl_fieldsDAO f = new M_work_tmpl_fieldsDAO();
						f.setId(IdWorker.getInstance().uuid());
						f.setTmpl_id(tmplId);
						f.setLabel_suf(field_suf[i]);
						f.setTitle(field_titles[i]);
						f.setSts("1");
						f.setOrd_(i+1);
						f.setCreate_uid(ub.getUid());
						f.setCreate_time(new Date());
						f.setModify_uid(ub.getUid());
						f.setUi_component(field_ui_componet[i]);
						f.setUi_defaultvalue(field_default[i]);
						f.setUi_maxlen(StrUtil.formatNullStrInt(field_max[i], 512));
						f.setUi_minlen(StrUtil.formatNullStrInt(field_min[i], 0));
						f.setUi_isreq(StrUtil.formatNullStr(field_isreq[i],"N"));
						f.setUi_other(field_ui_other[i]);
						f.setUi_showlist(f_showlist[i]);
						f.setUi_attrs(f_attrs[i]);
						res = res && f.add(conn);
					}
				}
				//创建的模板拥有所有权限
				res = res && PurviewService.getInstance().addTmplPurviewAll(conn, ub.getUid(), tmplId);
				return res;
			}
		});
		retMap.put("ret", res);
		return retMap;
	}
	
	
	public Map save() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return retMap;
		}
		final String tmplId = StrUtil.formatNullStr(request.getParameter("id"));
		
		final String cid = StrUtil.formatNullStr(request.getParameter("cid"));

		final String title = StrUtil.formatNullStr(request.getParameter("t"));
		final String content = StrUtil.formatNullStr(request.getParameter("c"));
		
		final String header = StrUtil.formatNullStr(request.getParameter("header"));
		final String footer = StrUtil.formatNullStr(request.getParameter("footer"));
		final String form_js = StrUtil.formatNullStr(request.getParameter("form_js"));
		final String view_page = StrUtil.formatNullStr(request.getParameter("view_page"));
		
		final String[] fid = request.getParameterValues("f_id");
		final String[] field_titles = request.getParameterValues("f_title");
		final String[] field_suf = request.getParameterValues("f_suf");
		final String[] field_isreq = request.getParameterValues("f_isreq");
		final String[] field_ui_componet = request.getParameterValues("f_ui");
		final String[] field_ui_other = request.getParameterValues("f_ui_other");

		final String[] field_default = request.getParameterValues("f_default");
		final String[] field_min = request.getParameterValues("f_min");
		final String[] field_max = request.getParameterValues("f_max");
		final String[] f_showlist = request.getParameterValues("f_showlist");
		final String[] f_attrs = request.getParameterValues("f_attrs");
		
		
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				M_work_tmplDAO tmpl = M_work_tmplDAO.dao.findById("*", tmplId);
				tmpl.setTitle(title);
				tmpl.setTmpl_content(content);
				tmpl.setDesc_header(header);
				tmpl.setDesc_footer(footer);
				tmpl.setForm_js(form_js);
				tmpl.setView_page(view_page);
				tmpl.setTmpl_cateid(cid);
				res = res && tmpl.save(conn);
				for(int i=0;i<fid.length;i++){
					if(StrUtil.formatNullStr(fid[i]).equals("")){
						M_work_tmpl_fieldsDAO f = new M_work_tmpl_fieldsDAO();
						f.setId(IdWorker.getInstance().uuid());
						f.setTmpl_id(tmplId);
						f.setLabel_suf(field_suf[i]);
						f.setTitle(field_titles[i]);
						f.setSts("1");
						f.setOrd_(i+1);
						f.setCreate_uid(ub.getUid());
						f.setCreate_time(new Date());
						f.setModify_uid(ub.getUid());
						f.setUi_component(field_ui_componet[i]);
						f.setUi_defaultvalue(field_default[i]);
						f.setUi_maxlen(StrUtil.formatNullStrInt(field_max[i], 512));
						f.setUi_minlen(StrUtil.formatNullStrInt(field_min[i], 0));
						f.setUi_isreq(StrUtil.formatNullStr(field_isreq[i],"N"));
						f.setUi_other(field_ui_other[i]);
						f.setUi_showlist(f_showlist[i]);
						f.setUi_attrs(f_attrs[i]);
						res = res && f.add(conn);
					}else{
						M_work_tmpl_fieldsDAO f = M_work_tmpl_fieldsDAO.dao.findById("*", fid[i]);
						f.setLabel_suf(field_suf[i]);
						f.setTitle(field_titles[i]);
						f.setSts("1");
						f.setOrd_(i+1);
						f.setCreate_uid(ub.getUid());
						f.setCreate_time(new Date());
						f.setModify_uid(ub.getUid());
						f.setUi_component(field_ui_componet[i]);
						f.setUi_defaultvalue(field_default[i]);
						f.setUi_maxlen(StrUtil.formatNullStrInt(field_max[i], 512));
						f.setUi_minlen(StrUtil.formatNullStrInt(field_min[i], 0));
						f.setUi_isreq(StrUtil.formatNullStr(field_isreq[i],"N"));
						f.setUi_other(field_ui_other[i]);
						f.setUi_showlist(f_showlist[i]);
						f.setUi_attrs(f_attrs[i]);
						res = res && f.save(conn);
					}
				}
				return res;
			}
		});
		retMap.put("ret", res);
		return retMap;
	}
	
	public Map  data() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return retMap;
		}
		final String tmp_id_id = StrUtil.formatNullStr(request.getParameter("id"));
		
		M_work_tmpl_recordsDAO tmpl = M_work_tmpl_recordsDAO.dao.findById("*", tmp_id_id);
		if(tmpl!=null){
			Map dataMap = TmplService.getInstance().getTmplDdata(tmpl.getTmpl_id(), TmplService.getInstance().getData(tmp_id_id));
			retMap.put("tmp_id_id", tmp_id_id);
			retMap.put("ret", true);
			retMap.put("result", dataMap);
		}else{
			retMap.put("ret", false);
		}
		
		return retMap;
	}
	
	public Map dataAdd(){
		final UserBean ub = getSessUser();
		String tmplId = StrUtil.formatNullStr(request.getParameter("tmplId"));
		String recId = StrUtil.formatNullStr(request.getParameter("recId"));//主表记录ID
		return TmplService.getInstance().dataAdd(request,ub, tmplId, recId);
	}
	
	public Map dataSave(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		final UserBean ub = getSessUser();
		if(ub==null){
			return null;
		}
		String tmplId = StrUtil.formatNullStr(request.getParameter("tmplId"));
		String tmpl_id_id = StrUtil.formatNullStr(request.getParameter("tmplIdId"));

		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				String _tmplId = tmplId;
				M_work_tmpl_recordsDAO d = M_work_tmpl_recordsDAO.dao.findById("*", tmpl_id_id);
				if(d!=null){
					_tmplId = d.getTmpl_id();
				}
				return TmplService.getInstance().setDataSave(conn,request, ub,_tmplId, tmpl_id_id);
			}
		});
		retMap.put("ret", res);
		return retMap;
	}

	public boolean remove() throws Exception{
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		M_work_tmplDAO mwt = M_work_tmplDAO.dao.findById("*", id);
		if(mwt!=null){
			mwt.setDelflag("1");
			return mwt.save();
		}
		return false;
	}
	public Map get() throws Exception{
		final String id = StrUtil.formatNullStr(request.getParameter("id"));
		return TmplService.getInstance().getTmpl(id);
	}
	

}
