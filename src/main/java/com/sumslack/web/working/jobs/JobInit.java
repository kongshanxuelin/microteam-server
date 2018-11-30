package com.sumslack.web.working.jobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sumslack.jsptagex.job.TagJob;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.dao.M_logs_mappingDAO;
import com.sumslack.web.working.dao.UsersDAO;

public class JobInit extends TagJob{
	public static Map<String,String> mLogsMapping = new HashMap();
	public static Map<String,String> aiLibs = new HashMap();
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		List<UsersDAO> allUsers = UsersDAO.dao.find("select uid,nick from " + UsersDAO.dao.getTable().getName());
		
		try {
			List aiList = TagJDBCInstance.getInstance().queryList("select * from ai_tencent_libs where t like '%ocr%'", null);
			if(aiList!=null) {
				for(int i=0;i<aiList.size();i++) {
					Map _m = (Map)aiList.get(i);
					aiLibs.put(StrUtil.formatNullStr(_m.get("t"))+StrUtil.formatNullStr(_m.get("code")),StrUtil.formatNullStr(_m.get("label")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("===========cache all user :" + allUsers.size());
		initLogsMapping();
	}
	
	private static void initLogsMapping(){
		List<M_logs_mappingDAO> mLogsMappingList = M_logs_mappingDAO.dao.find("select * from " + M_logs_mappingDAO.dao.getTable().getName());
		if(mLogsMapping!=null){
			for(M_logs_mappingDAO _m : mLogsMappingList){
				mLogsMapping.put(_m.getUri(), _m.getTitle());
			}
		}
	}
	public static String getUriTitle(String uri){
		initLogsMapping();
		if(mLogsMapping.get(uri)!=null){
			return StrUtil.formatNullStr(mLogsMapping.get(uri));
		}
		return null;
	}
	
	public static String getLibThing(String code) {
		if(code!=null)
			return StrUtil.formatNullStr(aiLibs.get("ocr.thing"+code),"未知物件");
		else
			return "未知物件";
	}
}
