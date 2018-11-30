package com.sumslack.web.working.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_docsDAO;
import com.sumslack.web.working.dao.M_docs_share_relDAO;
import com.sumslack.web.working.dao.UploadsDAO;
import com.sumslack.web.working.dao.Uploads_relDAO;

@URIAlias("wx/doc")
public class DocShareController extends CController{
	public List listAllShare() {
		//获取所有用户的演示目录
		List resultList = new ArrayList();
		List<M_docsDAO> docList =  M_docsDAO.dao.find("select id from " + M_docsDAO.dao.getTable().getName() + " where cate='9'");
		if(docList!=null && docList.size()>0){
			for(M_docsDAO doc:docList){
				resultList.add(getDoc(doc.getId()));
			}
		}
		return resultList;
	}
	public List list(){
		UserBean ub = getWxUser();
		if(ub == null) return null;
		//1:内部文件夹 2：共享文件夹 3：我的文件夹
		String cate = StrUtil.formatNullStr(request.getParameter("cate"));
		String pid = StrUtil.formatNullStr(request.getParameter("pid"));
		List resultList = new ArrayList();
		List<M_docsDAO> docList =  M_docsDAO.dao.find("select id from " + M_docsDAO.dao.getTable().getName() + " where cate=? and teamid=? and pid=?", cate,ub.getTeamId(),pid);
		if(docList!=null && docList.size()>0){
			for(M_docsDAO doc:docList){
				resultList.add(getDoc(doc.getId()));
			}
		}
		return resultList;
	}
	public Map addFolder() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		
		String title = StrUtil.formatNullStr(request.getParameter("title"));
		String parentId = StrUtil.formatNullStr(request.getParameter("pid"));
		String cate = StrUtil.formatNullStr(request.getParameter("cate"));
		String scope = StrUtil.formatNullStr(request.getParameter("scope"));
		
		String docId = IdWorker.getInstance().uuid();
		M_docsDAO doc = new M_docsDAO();
		doc.setId(docId);
		doc.setTitle(title);
		doc.setIsfile("0");
		doc.setPid(parentId);
		doc.set_ord(1);
		doc.setIcon(null);
		doc.setTeamid(ub.getTeamId());
		doc.setCreate_uid(ub.getUid());
		doc.setScope(scope);
		doc.setFileid(null);
		doc.setContent(null);
		doc.setCate(cate);
		boolean res = doc.add();
		retMap.put("ret", res);
		return retMap;
	}
	public Map addFile(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;

		String fileId = StrUtil.formatNullStr(request.getParameter("fid"));
		String parentId = StrUtil.formatNullStr(request.getParameter("pid"));
		String cate = StrUtil.formatNullStr(request.getParameter("cate"));
		String scope = StrUtil.formatNullStr(request.getParameter("scope"));
		
		UploadsDAO file = UploadsDAO.dao.findById("*", fileId);
		String docId = IdWorker.getInstance().uuid();
		if(file!=null){
			M_docsDAO doc = new M_docsDAO();
			doc.setId(docId);
			doc.setTitle(file.getName());
			doc.setIsfile("1");
			doc.setPid(parentId);
			doc.set_ord(1);
			doc.setIcon(null);
			doc.setTeamid(ub.getTeamId());
			doc.setCreate_uid(ub.getUid());
			doc.setScope(scope);
			doc.setFileid(fileId);
			doc.setContent(null);
			doc.setCate(cate);
			boolean res = doc.add();
			Uploads_relDAO urDAO = new Uploads_relDAO();
			urDAO.setId(IdWorker.getInstance().uuid());
			urDAO.setObjType("doc");
			urDAO.setObjId(docId);
			urDAO.setUploadId(fileId);
			urDAO.setDt(new Date());
			res = res && urDAO.add();
			retMap.put("ret", res);
			retMap.put("doc", doc);
		}
		return retMap;
	}
	
	public Map move(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		String parentId = StrUtil.formatNullStr(request.getParameter("pid"));
		M_docsDAO doc = M_docsDAO.dao.findById("*", id);
		if(doc!=null){
			doc.setPid(parentId);
			boolean res = doc.save();
			retMap.put("ret", res);
		}
		return retMap;
	}
	
	public Map remove(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		
		M_docsDAO doc = M_docsDAO.dao.findById("*", id);
		if(doc!=null){
			boolean res = doc.delete();
			retMap.put("ret", res);
		}
		return retMap;
	}
	
	public Map rename(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		String title = StrUtil.formatNullStr(request.getParameter("title"));
		
		M_docsDAO doc = M_docsDAO.dao.findById("*", id);
		if(doc!=null){
			doc.setTitle(title);
			boolean res = doc.save();
			UploadsDAO upload = UploadsDAO.dao.findById("*", doc.getFileid());
			if(upload!=null){
				upload.setName(title);
				upload.save();
			}
			retMap.put("ret", res);
		}
		return retMap;
	}
	
	public Map share(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		int ops = StrUtil.formatNullStrInt(request.getParameter("ops"),0);
		
		M_docsDAO doc = M_docsDAO.dao.findById("*", id);
		if(doc!=null){
			boolean res = true;
			M_docs_share_relDAO share = M_docs_share_relDAO.dao.findFirst("select * from " + M_docs_share_relDAO.dao.getTable().getName() + " where doc_id=? and share_scope='u' and share_scope_id=?",id,ub.getUid());
			if(share == null){
				share = new M_docs_share_relDAO();
				share.setId(IdWorker.getInstance().uuid());
				share.setDoc_id(id);
				share.setShare_scope("u");
				share.setShare_scope_id(ub.getUid());
				share.set_ops(0);
				res = res && share.add();
			}
			share.set_ops(ops);
			share.save();
			retMap.put("ret", res);
		}
		return retMap;
	}
	
	public Map get(){
		String id = StrUtil.formatNullStr(request.getParameter("id"));
		return getDoc(id);
	}
	
	private Map getDoc(String id){
		Map docMap = new HashMap();
		M_docsDAO doc = M_docsDAO.dao.findById("*", id);
		if(doc!=null){
			docMap.put("doc", doc);
			UploadsDAO file = UploadsDAO.dao.findById("spath,path,_ext", doc.getFileid());
			if(file!=null){
				docMap.put("file", file);
			}
		}
		return docMap;
	}
	
}
