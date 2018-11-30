package com.sumslack.web.working.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.util.DateUtils;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_project_tasks_commentsDAO;

public class CommentService {
	private static CommentService instance = new CommentService();
	public synchronized static CommentService getInstance(){
		if(instance == null){
			instance = new CommentService();
		}
		return instance;
	}
	
	/**
	 * @param ub
	 * @param taskId
	 * @param content
	 * @param stype：i：图片，t：文本，s:语音
	 * @return
	 */
	public M_work_project_tasks_commentsDAO addComment(UserBean ub,String taskId,String content,String stype){
		String cid = IdWorker.getInstance().uuid();
		M_work_project_tasks_commentsDAO c = new M_work_project_tasks_commentsDAO();
		c.setId(cid);
		c.setTask_id(taskId);
		c.setContent(content);
		c.setCreate_uid(ub.getUid());
		c.setCreate_time(new Date());
		c.setModify_uid(ub.getUid());
		c.setStype(stype);
		c.add();
		return M_work_project_tasks_commentsDAO.dao.findById("*", cid);
	}
	
	public boolean remove(UserBean ub,String cid){
		M_work_project_tasks_commentsDAO c = M_work_project_tasks_commentsDAO.dao.findById("*", cid);
		if(c.getCreate_uid().equals(ub.getUid())){
			return c.delete();
		}
		return false;
	}
	
	public List list(String taskId){
		List<M_work_project_tasks_commentsDAO> comments = M_work_project_tasks_commentsDAO.dao.find("select * from " + 
				M_work_project_tasks_commentsDAO.dao.getTable().getName() + " where task_id=? order by create_time", taskId);
		List cs = new ArrayList();
		for(M_work_project_tasks_commentsDAO c : comments){
			Map _m = new HashMap();
			_m.put("time", DateUtils.format(c.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
			_m.put("nick", Global.getNick(c.getCreate_uid()));
			_m.put("c", c.getContent());
			cs.add(_m);
		}
		return cs;
	}
}
