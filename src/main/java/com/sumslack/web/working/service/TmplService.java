package com.sumslack.web.working.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.util.HttpUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_tmplDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fieldsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.dao.M_work_tmpl_recordsDAO;
import com.sumslack.web.working.dao.Users_tmpl_purviewDAO;

public class TmplService {
	private static TmplService instance = new TmplService();
	public synchronized static TmplService getInstance(){
		if(instance == null){
			instance = new TmplService();
		}
		return instance;
	}
	
	public List getMyTmplList(UserBean ub,String pid){
		List list = null;
		try{
			if(!pid.equals(""))
				list = TagJDBCInstance.getInstance().queryList("select id,title,tmpl_content,tmpl_cateid,(select count(*) from "+M_work_tmplDAO.dao.getTable().getName()+" "
						+ "where pid=a.id) child from " + M_work_tmplDAO.dao.getTable().getName() + " a where delflag<>'1' and  pid=?",new Object[]{pid});
			else{
				String sql = "select id,title,tmpl_content,tmpl_cateid,(select count(*) from "+M_work_tmplDAO.dao.getTable().getName()+" where pid=a.id) child from " + 
			M_work_tmplDAO.dao.getTable().getName() + " a where delflag<>'1' and id in (select tmpl_id from "+Users_tmpl_purviewDAO.dao.getTable().getName()+" where uid=? and (isview='Y' or isedit='Y' or isremove='Y' or isowner='Y')) and (pid is null or pid='')";
				list = TagJDBCInstance.getInstance().queryList(sql,new Object[]{ub.getUid()});
			}
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map m = (Map)list.get(i);
					if(!pid.equals("")){
						m.put("isowner","Y");
						m.put("isedit", "Y");
						m.put("isview", "Y");
						m.put("isremove","Y");
					}else{
						Users_tmpl_purviewDAO ut = PurviewService.getInstance().isOwner(ub.getUid(), StrUtil.formatNullStr(m.get("id")));
						if(ut!=null){
							m.put("isowner", ut.getIsowner());
							m.put("isedit", ut.getIsedit());
							m.put("isview", ut.getIsview());
							m.put("isremove", ut.getIsremove());
						}
					}
				}
			}
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public M_work_tmpl_fieldsDAO getField(String id){
		return M_work_tmpl_fieldsDAO.dao.findFirstByCache("testCache", "select * from " + M_work_tmpl_fieldsDAO.dao.getTable().getName() + " where id=?",id);
	}
	
	public List<M_work_tmpl_fields_valueDAO> getData(String tmp_id_id){
		return M_work_tmpl_fields_valueDAO.dao.find("select tmpl_id_id,id,field_id,field_value,(select title from m_work_tmpl_fields "
				+ "where id=field_id) title,field_value_display from " + M_work_tmpl_fields_valueDAO.dao.getTable().getName() +
				" where tmpl_id_id=?", tmp_id_id);
	}
	
	public Map getTmpl(String id){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		M_work_tmplDAO tmpl = M_work_tmplDAO.dao.findById("*", id);
		if(tmpl != null && tmpl.getDelflag().equals("0")){
			retMap.put("ret", true);
			retMap.put("cate", tmpl.getTmpl_cateid());
			retMap.put("id", tmpl.getId());
			retMap.put("title", tmpl.getTitle());
			retMap.put("content", tmpl.getTmpl_content());
			retMap.put("ord_", tmpl.getOrd_());
			retMap.put("sts", tmpl.getSts());
			retMap.put("pid", tmpl.getPid());
			retMap.put("header", tmpl.getDesc_header());
			retMap.put("footer", tmpl.getDesc_footer());
			retMap.put("creater", tmpl.getCreate_uid());
			retMap.put("form_js", tmpl.getForm_js());
			retMap.put("view_page", tmpl.getView_page());
			List<M_work_tmplDAO> childList = M_work_tmplDAO.dao.find("select id,title from " + M_work_tmplDAO.dao.getTable().getName() + " where delflag<>'1' and  pid=?", tmpl.getId());
			retMap.put("childs", childList);
			List<M_work_tmpl_fieldsDAO> fields = M_work_tmpl_fieldsDAO.dao.find("select * from " + M_work_tmpl_fieldsDAO.dao.getTable().getName() + " where tmpl_id=? order by ord_", tmpl.getId());
			retMap.put("fields", fields);
		}
		return retMap;
	}
	
	public String getTmplTitle(String id){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		M_work_tmplDAO tmpl = M_work_tmplDAO.dao.findById("*", id);
		if(tmpl != null && tmpl.getDelflag().equals("0")){
			return  tmpl.getTitle();
		}
		return "";
	}
	
	
	public Map getTmplDdata(String id,List<M_work_tmpl_fields_valueDAO> fieldValues){
		Map fieldValueMap = new HashMap();
		Map fieldValueDisplayMap = new HashMap();
		for(M_work_tmpl_fields_valueDAO mfv : fieldValues){
			fieldValueMap.put(mfv.getField_id(), mfv.getField_value());
			if(!StrUtil.formatNullStr(mfv.getField_value_display()).equals("")){
				fieldValueDisplayMap.put(mfv.getField_id(), mfv.getField_value_display());
			}
		}
		Map retMap = new HashMap();
		retMap.put("ret", false);
		M_work_tmplDAO tmpl = M_work_tmplDAO.dao.findById("*", id);
		if(tmpl != null && tmpl.getDelflag().equals("0")){
			retMap.put("ret", true);
			retMap.put("cate", tmpl.getTmpl_cateid());
			retMap.put("id", tmpl.getId());
			retMap.put("title", tmpl.getTitle());
			retMap.put("content", tmpl.getTmpl_content());
			retMap.put("ord_", tmpl.getOrd_());
			retMap.put("sts", tmpl.getSts());
			retMap.put("creater", tmpl.getCreate_uid());
			List fieldsList = new ArrayList();
			List<M_work_tmpl_fieldsDAO> fields = M_work_tmpl_fieldsDAO.dao.find("select * from " + M_work_tmpl_fieldsDAO.dao.getTable().getName() + " where tmpl_id=?", tmpl.getId());
			for(M_work_tmpl_fieldsDAO f : fields){
				Map m = new HashMap();
				m.put("id", f.getId());
				m.put("title", f.getTitle());
				m.put("suf", f.getLabel_suf());
				m.put("value", fieldValueMap.get(f.getId()));
				m.put("value2", fieldValueDisplayMap.get(f.getId()));
				m.put("ui_component", f.getUi_component());
				m.put("ui_defaultvalue",fieldValueMap.get(f.getId()));
				m.put("ui_other", f.getUi_other());
				fieldsList.add(m);
			}
			retMap.put("fields", fieldsList);
		}
		return retMap;
	}
	
	public List<M_work_tmplDAO> getTmplByCateId(String tmpl_cateid){
		return M_work_tmplDAO.dao.find("select id,title from " + M_work_tmplDAO.dao.getTable().getName()+" where tmpl_cateid=? and sts='1' and delflag='0'", tmpl_cateid);
	}
	
	public boolean setData(HttpServletRequest request,UserBean ub,final String recId,final String tmpl_id,final String tmpl_id_id){
		return setData(null,request,ub,recId,tmpl_id,tmpl_id_id);
	}
	
	public boolean setData(Connection conn,JSONObject params,UserBean ub,final String recId,final String tmpl_id,final String tmpl_id_id){
		final Map tmplMap = TmplService.getInstance().getTmpl(tmpl_id);
		final List<M_work_tmpl_fieldsDAO> fields = (List<M_work_tmpl_fieldsDAO>)tmplMap.get("fields");
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				if(fields!=null){
					M_work_tmpl_recordsDAO wt = new M_work_tmpl_recordsDAO();
					wt.setId(tmpl_id_id);
					wt.setTmpl_id(tmpl_id);
					wt.setDeflag("0");
					wt.setCreate_uid(ub!=null?ub.getUid():null);
					wt.setCreate_time(new Date());
					wt.setModify_uid(ub!=null?ub.getUid():null);
					wt.setIp("");
					wt.setBrowser("wx");
					wt.setRec_id(recId);
					res = res && wt.add(conn);
					for(M_work_tmpl_fieldsDAO f : fields){
						M_work_tmpl_fields_valueDAO v = new M_work_tmpl_fields_valueDAO();
						v.setId(IdWorker.getInstance().uuid());
						v.setTmpl_id(tmpl_id);
						v.setField_id(f.getId());
						String _vv = params.getString(f.getId());
						v.setField_value(_vv);
						v.setDelflag("0");
						v.setTmpl_id_id(tmpl_id_id);
						if(conn == null){
							res = res && v.add();
						}else{
							res = res && v.add(conn);
						}
					}
				}
				return res;
			}
		});
		return res;
	}
	
	public boolean setData(Connection conn,HttpServletRequest request,UserBean ub,final String recId,final String tmpl_id,final String tmpl_id_id){
		final Map tmplMap = TmplService.getInstance().getTmpl(tmpl_id);
		final List<M_work_tmpl_fieldsDAO> fields = (List<M_work_tmpl_fieldsDAO>)tmplMap.get("fields");
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				if(fields!=null){
					M_work_tmpl_recordsDAO wt = new M_work_tmpl_recordsDAO();
					wt.setId(tmpl_id_id);
					wt.setTmpl_id(tmpl_id);
					wt.setDeflag("0");
					wt.setCreate_uid(ub!=null?ub.getUid():null);
					wt.setCreate_time(new Date());
					wt.setModify_uid(ub!=null?ub.getUid():null);
					wt.setIp(HttpUtils.getIP(request));
					wt.setBrowser(getBrowser(request));
					wt.setRec_id(recId);
					res = res && wt.add(conn);
					for(M_work_tmpl_fieldsDAO f : fields){
						M_work_tmpl_fields_valueDAO v = new M_work_tmpl_fields_valueDAO();
						v.setId(IdWorker.getInstance().uuid());
						v.setTmpl_id(tmpl_id);
						v.setField_id(f.getId());
						String[] _vv = request.getParameterValues(f.getId());
						v.setField_value(_vv!=null?StringUtils.join(_vv,','):null);
						if(f.getUi_component().equals("select")){
							if(f.getUi_other().equals("selectmuser")||f.getUi_other().equals("selectuser")){
								Set _vvNick = new HashSet();
								if(_vv!=null){
									String[]  _ssVV = StrUtil.split(v.getField_value(), ",");
									for(String _s:_ssVV){
										String _ss = StrUtil.formatNullStr(_s);
										if(!_ss.equals("")){
										  _vvNick.add(Global.getNick(_ss));
										}
									}
									v.setField_value_display(StringUtils.join(_vvNick,','));
								}
							}
						}
						v.setDelflag("0");
						v.setTmpl_id_id(tmpl_id_id);
						if(conn == null){
							res = res && v.add();
						}else{
							res = res && v.add(conn);
						}
					}
				}
				return res;
			}
		});
		return res;
	}
	
	public boolean setDataSave(Connection conn,HttpServletRequest request,UserBean ub,final String tmpl_id,final String tmpl_id_id){
		final Map tmplMap = TmplService.getInstance().getTmpl(tmpl_id);
		final List<M_work_tmpl_fieldsDAO> fields = (List<M_work_tmpl_fieldsDAO>)tmplMap.get("fields");
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				if(fields!=null){
					M_work_tmpl_recordsDAO wt = M_work_tmpl_recordsDAO.dao.findById("*", tmpl_id_id);
					wt.setDeflag("0");
					wt.setModify_uid(ub.getUid());
					wt.save(conn);
					for(M_work_tmpl_fieldsDAO f : fields){
						M_work_tmpl_fields_valueDAO v = M_work_tmpl_fields_valueDAO.dao.
								findFirst("select * from " + M_work_tmpl_fields_valueDAO.dao.getTable().getName() + 
										" where tmpl_id_id=? and tmpl_id=? and field_id=?",tmpl_id_id,tmpl_id,f.getId());
						System.out.println(String.format("************%s,%s,%s", tmpl_id,tmpl_id_id,f.getId()));
						if(v!=null){
							String[] _vv = request.getParameterValues(f.getId());
							v.setField_value(_vv!=null?StringUtils.join(_vv,','):null);
							if(f.getUi_component().equals("select")){
								if(f.getUi_other().equals("selectmuser")||f.getUi_other().equals("selectuser")){
									Set _vvNick = new HashSet();
									if(_vv!=null){
										String[]  _ssVV = StrUtil.split(v.getField_value(), ",");
										for(String _s:_ssVV){
											String _ss = StrUtil.formatNullStr(_s);
											if(!_ss.equals("")){
											  _vvNick.add(Global.getNick(_ss));
											}
										}
										v.setField_value_display(StringUtils.join(_vvNick,','));
									}
								}
							}
						}
						if(conn == null){
							res = res && v.save();
						}else{
							res = res && v.save(conn);
						}
					}
				}
				return res;
			}
		});
		return res;
	}
	
	
	public boolean setDataSave(Connection conn,JSONObject params,final String tmpl_id,final String tmpl_id_id){
		final Map tmplMap = TmplService.getInstance().getTmpl(tmpl_id);
		final List<M_work_tmpl_fieldsDAO> fields = (List<M_work_tmpl_fieldsDAO>)tmplMap.get("fields");
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				if(fields!=null){
					M_work_tmpl_recordsDAO wt = M_work_tmpl_recordsDAO.dao.findById("*", tmpl_id_id);
					wt.setDeflag("0");
					wt.save(conn);
					for(M_work_tmpl_fieldsDAO f : fields){
						M_work_tmpl_fields_valueDAO v = M_work_tmpl_fields_valueDAO.dao.
								findFirst("select * from " + M_work_tmpl_fields_valueDAO.dao.getTable().getName() + 
										" where tmpl_id_id=? and tmpl_id=? and field_id=?",tmpl_id_id,tmpl_id,f.getId());
						if(v!=null){
							v.setField_value(params.getString(f.getId()));
						}
						if(conn == null){
							res = res && v.save();
						}else{
							res = res && v.save(conn);
						}
					}
				}
				return res;
			}
		});
		return res;
	}
	
	/****
	 * TmplController服务
	 */
	public Map dataAdd(final HttpServletRequest request,final UserBean ub,final String tmplId,final String recId){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		String tmpl_id_id = IdWorker.getInstance().uuid();
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				return TmplService.getInstance().setData(conn,request, ub,recId,tmplId, tmpl_id_id);
			}
		});
		retMap.put("ret", res);
		return retMap;
	}
	
	public Map dataAdd(final JSONObject json,final UserBean ub,final String tmplId,final String recId){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		String tmpl_id_id = IdWorker.getInstance().uuid();
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				return TmplService.getInstance().setData(conn,json, ub,recId,tmplId, tmpl_id_id);
			}
		});
		retMap.put("ret", res);
		return retMap;
	}
	
	public String dataOneRecId(UserBean ub,String tmplId){
		M_work_tmpl_recordsDAO rec = M_work_tmpl_recordsDAO.dao.findFirst("select id from " + M_work_tmpl_recordsDAO.dao.getTable().getName() + " where tmpl_id=? and create_uid=?",tmplId,(ub!=null?ub.getUid():null));
		if(rec!=null){
			return rec.getId();
		}
		return null;
	}
	
	private String getBrowser(HttpServletRequest request){
		String ua = request.getHeader("User-Agent");
		UserAgent userAgent = UserAgent.parseUserAgentString(ua); 
		Browser browser = userAgent.getBrowser();  
		OperatingSystem os = userAgent.getOperatingSystem();
		Map retMap = new HashMap();
		retMap.put("ua",ua);
		retMap.put("agent",userAgent);
		retMap.put("browser", browser);
		retMap.put("os", os);
		return JSON.toJSONString(retMap);
		
	}
}
