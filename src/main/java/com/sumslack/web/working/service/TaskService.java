package com.sumslack.web.working.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sumslack.jsptagex.db.ITx;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_project_tasksDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fieldsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.dao.M_work_tmpl_recordsDAO;

public class TaskService {
	private static TaskService instance = new TaskService();
	private TaskService(){
		
	}
	public synchronized static TaskService getInstance(){
		if(instance == null){
			instance = new TaskService();
		}
		return instance;
	}
	
	public boolean add(final UserBean ub,HttpServletRequest request,final String taskId,final String proId,final String tmpl_id,final String content,final String fzr,final String auditer,final String joiner,final String end_time){
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				try{
					M_work_project_tasksDAO task = new M_work_project_tasksDAO();
					task.setId(taskId);
					task.setProject_id(proId);
					task.setTmpl_id(tmpl_id);
					
					//生成模板ID的ID
					String tmpl_id_id = IdWorker.getInstance().uuid();
					task.setTmpl_id_id(tmpl_id_id);
					task.setSts("1");
					task.setDelflag("0");
					task.setOrd_(1);
					task.setContent(content);
					task.setAdmin_uid(fzr);
					task.setTask_auditer(auditer);
					task.setTask_joiner(joiner);
					task.setCreate_uid(ub.getUid());
					task.setCreate_time(new Date());
					task.setModify_uid(ub.getUid());
					task.setTime_end(end_time.equals("")?null:DateUtils.parseDate(end_time, DateUtils.fmt_dateFormat));
					res = res && task.add(conn);

					//自定义属性赋值
					Map tmplMap = TmplService.getInstance().getTmpl(tmpl_id);
					List<M_work_tmpl_fieldsDAO> fields = (List<M_work_tmpl_fieldsDAO>)tmplMap.get("fields");
					if(fields!=null){
						M_work_tmpl_recordsDAO wt = new M_work_tmpl_recordsDAO();
						wt.setId(tmpl_id_id);
						wt.setTmpl_id(tmpl_id);
						wt.setDeflag("0");
						wt.setCreate_uid(ub.getUid());
						wt.setCreate_time(new Date());
						wt.setModify_uid(ub.getUid());
						res = res && wt.add(conn);
						for(M_work_tmpl_fieldsDAO f : fields){
							M_work_tmpl_fields_valueDAO v = new M_work_tmpl_fields_valueDAO();
							v.setId(IdWorker.getInstance().uuid());
							v.setTmpl_id(tmpl_id);
							v.setField_id(f.getId());
							v.setField_value(request.getParameter(f.getId()));
							v.setDelflag("0");
							v.setTmpl_id_id(tmpl_id_id);
							res = res && v.add(conn);
						}
					}
					return res;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return res;
			}
		});
		return res;
	}
	
	public boolean save(final HttpServletRequest request, final String taskId,final String content,final String fzr,final String auditer,final String joiner,final String end_time){
		boolean res = TagJDBCInstance.getInstance().tx(new ITx() {
			@Override
			public boolean run(Connection conn) throws SQLException {
				boolean res = true;
				try{
					M_work_project_tasksDAO task = M_work_project_tasksDAO.dao.findById("*", taskId);
					task.setContent(content);
					task.setAdmin_uid(fzr);
					task.setTask_auditer(auditer);
					task.setTime_end(end_time.equals("")?null:DateUtils.parseDate(end_time, DateUtils.fmt_dateFormat));
					task.setTask_joiner(joiner);
					res = res && task.save(conn);
					

					String tmpl_id_id = task.getTmpl_id_id();
					//自定义属性赋值
					List<M_work_tmpl_fields_valueDAO> wfvs = TmplService.getInstance().getData(tmpl_id_id);
					if(wfvs!=null){
						for(M_work_tmpl_fields_valueDAO v : wfvs){
							v.setField_value(StrUtil.formatNullStr(request.getParameter(v.getField_id())));
							res = res && v.save(conn);
						}
					}
					return res;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				return res;
			}
		});
		return res;
	}
	
	public Map getMyTaskNum(String uid){
		Map retMap = new HashMap();
		List<M_work_project_tasksDAO> list =M_work_project_tasksDAO.dao.find("select * from " + M_work_project_tasksDAO.dao.getTable().getName() 
				+ " where sts='1' and delflag<>'1' and (create_uid=? or admin_uid=? or FIND_IN_SET(?,task_joiner) or FIND_IN_SET(?,task_auditer)) order by modify_time desc", uid,uid,uid,uid);
		retMap.put("taskNum", list!=null?list.size():0);
		if(list!=null && list.size()>0){
			retMap.put("task", list.get(0));
		}
		return retMap;
	}
}
