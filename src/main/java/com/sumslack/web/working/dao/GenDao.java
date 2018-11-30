package com.sumslack.web.working.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sumslack.jsptagex.AppInit;
import com.sumslack.jsptagex.db.ar.TagARGenCodeManager;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.FileManager;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;

public class GenDao {
	final static Logger logger = LogManager.getLogger(GenDao.class);
	//数据库名
	private static final String schema = "sumslack_work";
	//数据源
	private static final String datasource = "default";
	//DAO所在package
	private static final String pack = "com.sumslack.web.working.dao";
	//生成源码的路径
	private static final String basePath = "F:/workspace_sumslack/sumslack_working/src/main/java/com/sumslack/web/working/dao";
	//仅生成某个表的DAO时用
	private static String[] tablesOnly = new String[]{"m_work_projects_zhang_cate"};
	//是否生成getter和setter方法
	private static final boolean isGenColumn = true;//是否生成getter和setter方法
	
	public static void main(String[] args) throws Exception {
		TagARGenCodeManager.getInstance().run(schema,  pack, basePath, isGenColumn,tablesOnly);
	}
}
