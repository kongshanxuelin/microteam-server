<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sn" uri="http://www.sumslack.com/taglib"%>
<html>
<head>
	<link href="js/jsoneditor.min.css" rel="stylesheet" type="text/css">
    <script src="js/jsoneditor.min.js"></script>
    
    <script type="text/javascript" src="js/template-web.js"></script>
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	/*
	$.ajaxSetup({
		data:{
			token:$("#token").val()
		}
	});
	*/
	W = "<sn:webroot />";
	
	Date.prototype.format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	</script>
</head>
<body>
<sn:choose>
	<sn:when test="${empty sessionScope.uid }">
		<input type="text" placeholder="输入用户名" value="test@test.com" id="username" />
		<input type="password" value="123456" placeholder="输入用户名" id="pwd" /><input type="button" value="登录" id="btnLogin" />	
		<input type="text" placeholder="输入Token" value="" id="token" /><input type="button" value="Token方式登录" id="btnLogin2" />		
	</sn:when>
	<sn:else>
		欢迎您：${sessionScope.nick},<input type="button" value="退出" id="btnLogout" />	
	</sn:else>
</sn:choose>

<h1>团队接口</h1>
<ol>
	<li>
		<form id="frm_team">
			<input type="button" value="我的团队" onclick="submitForm('user/teamList','frm_team')" />
			<input type="text" placeholder="团队标题" value="" name="title" />
			<input type="button" value="创建团队" onclick="submitForm('user/teamCreate','frm_team')" />
			<input type="text" placeholder="要切换到的团队ID" value="" name="teamid" />
			<input type="button" value="切换团队" onclick="submitForm('user/teamChange','frm_team')" />
			<input type="button" value="删除团队" onclick="submitForm('user/teamRemove','frm_team')" />
		</form>
	</li>	
</ol>


<h1>项目接口</h1>
<ol>
	<li>
		我的项目：
		<form id="frm_projectlist">
			<input type="button" value="我的项目" onclick="submitForm('project/myProjects','frm_projectlist')" />
			<input type="button" value="我的同事" onclick="submitForm('user/listCompanyUser','frm_projectlist')" />
		</form>
	</li>	
	
	<li>
		搜索项目：
		<form id="frm_projectSearch">
			<input type="text" placeholder="关键字" value="" name="q" />
			<input type="button" value="搜索项目" onclick="submitForm('project/search','frm_projectSearch')" />
		</form>
	</li>	
	
	<li>
		新增项目：
		<form id="frm_addproject">
			<input type="text" placeholder="项目标题" value="" name="t" />
			<input type="text" placeholder="项目描述" value="" name="c" />
			<input type="text" placeholder="负责人uid，单个" value="" name="leader" />
			<input type="text" placeholder="参与者uid，多个用,分开" value="" name="joiners" />
			<br/>
			<input type="text" placeholder="开始时间" value="2017-04-01" name="sdt" />
			<input type="text" placeholder="结束时间" value="2017-08-01" name="edt" />
			<input type="button" value="添加项目" onclick="submitForm('project/add','frm_addproject')" />
		</form>
	</li>
	
	<li>
		删除项目：
		<form id="frm_removeproject">
			<input type="text" placeholder="输入项目ID" name="id" />
			<input type="button" value="删除项目" onclick="submitForm('project/remove','frm_removeproject')" />
		</form>
	</li>	
		
	<li>
		获取/保存项目：
		<form id="frm_getproject">
			<input type="text" placeholder="输入项目ID" name="id" />
			<input type="button" value="获取项目" onclick="submitForm('project/get','frm_getproject',cb_getProject)" />
		</form>
		<form id="frm_saveproject">
			<input type="text" placeholder="项目ID" value="" name="id" />
			<input type="text" placeholder="项目标题" value="" name="t" />
			<input type="text" placeholder="项目描述" value="" name="c" />
			<input type="text" placeholder="负责人uid，单个" value="" name="leader" />
			<input type="text" placeholder="参与者uid，多个用,分开" value="" name="joiners" />
			<br/>
			<input type="text" placeholder="开始时间" value="2017-04-01" name="sdt" />
			<input type="text" placeholder="结束时间" value="2017-08-01" name="edt" />
			<input type="button" value="保存项目" onclick="submitForm('project/save','frm_saveproject')" />
		</form>
	</li>
	
	<li>
		修改项目状态：
		<form id="frm_stsproject">
			<input type="text" placeholder="输入项目ID" name="id" />
			<input type="text" placeholder="项目状态" name="sts" />(0:就绪  1：完成  2：搁置)
			<input type="button" value="修改状态" onclick="submitForm('project/setProjectSts','frm_stsproject')" />
		</form>
	</li>	
	
	<li>
		设置项目成员角色：
		<form id="frm_roleproject">
			<input type="text" placeholder="输入项目ID" name="id" />
			<input type="text" placeholder="输入成员uid" name="uid" />
			<input type="text" placeholder="项目状态" name="role" />(owner：创建者，leader：组长，member：成员)
			<input type="button" value="设置成员角色" onclick="submitForm('project/setLeader','frm_roleproject')" />
		</form>
	</li>	
</ol>

<h1>任务接口</h1>
<ol>
	<li>项目任务列表：
		<form id="frm_tasklist">
			<input type="text" placeholder="项目ID" name="proid" />
			<input type="text" placeholder="任务状态：1(未完成),2(已完成),3(已归档)" name="sts" />
			<input type="button" value="拉取任务列表" onclick="submitForm('task/list','frm_tasklist')" />
		</form>
	</li>
	<li>
		添加任务：
		<form id="frm_taskadd">
			<input type="text" placeholder="项目ID,可以空" name="proid" />
			<input type="text" placeholder="模板ID" name="tmpl_id" onblur="tmplChange('tmpl_id','taskadd_fields')" /><br/>
			<input type="text" placeholder="任务描述" name="content" />
			<input type="text" placeholder="截止时间" name="end_time" /><br/>
			<input type="text" placeholder="负责人，一个uid" name="fzr" />
			<input type="text" placeholder="参与人，多个uid用,分开" name="joiner" />
			<input type="text" placeholder="审核人，一个uid" name="auditer" /><br/>
			<h4>额外属性字段</h4>
			<div id="taskadd_fields">
			</div>
			<input type="button" value="添加任务" onclick="submitForm('task/add','frm_taskadd')" />
		</form>
	</li>
	<li>删除任务：
		<form id="frm_taskremove">
			<input type="text" placeholder="任务ID" name="id" value="" />
			<input type="button" value="删除任务" onclick="submitForm('task/remove','frm_taskremove')" />
		</form>
	</li>
	<li>保存任务：
		获取任务：
		<form id="frm_taskget">
			<input type="text" placeholder="任务ID" name="id" value="" />
			<input type="button" value="任务详情" onclick="submitForm('task/get','frm_taskget',cb_tasksave)" />
			
		</form>
		
		<form id="frm_tasksave">
			<input type="text" name="id" />
			<input type="text" name="content" />
			<div id="div_tasksave">
			</div>
			<input type="button" value="保存任务" onclick="submitForm('task/save','frm_tasksave')" />
		</form>
	</li>
	<li>任务状态：
		<form id="frm_taskchangests">
			<input type="text" placeholder="任务ID" name="id" value="" />
			<select name="sts">
				<option value="1">未完成</option>
				<option value="2">已完成</option>
				<option value="3">已归档</option>
			</select>
			<input type="button" value="修改" onclick="submitForm('task/changeStatus','frm_taskchangests')" />
		</form>
	</li>
	<li>转交任务：
		<form id="frm_taskchangecreater">
			<input type="text" placeholder="任务ID" name="id" value="" />
			<input type="text" placeholder="转交给别人，输入uid" name="uid" value="" />
			<input type="button" value="修改" onclick="submitForm('task/changeCreater','frm_taskchangecreater')" />
		</form>
	</li>
	<li>移动任务到项目X：
		<form id="frm_taskchangechangeproject">
			<input type="text" placeholder="任务ID" name="id" value="" />
			<input type="text" placeholder="移动项目，输入项目ID" name="proid" value="" />
			<input type="button" value="修改" onclick="submitForm('task/changeProject','frm_taskchangechangeproject')" />
		</form>
	</li>
	<li>添加任务评论：
		<form id="frm_taskcommentadd">
			<input type="text" placeholder="任务ID" name="taskId" value="" />
			<input type="text" placeholder="评论内容" name="content" value="测试评论内容" />
			<input type="text" placeholder="评论类型:t(文本),i(图片),s(语音)" name="stype" value="t" />
			<input type="button" value="添加评论" onclick="submitForm('task/addComment','frm_taskcommentadd')" />
		</form>
	</li>
	<li>删除任务评论：
		<form id="frm_taskcommentremove">
			<input type="text" placeholder="评论ID" name="cid" value="" />
			<input type="button" value="删除评论" onclick="submitForm('task/removeComment','frm_taskcommentremove')" />
		</form>
	</li>
</ol>	


<h1>流程接口</h1>
<ol>
	<li>
		<form id="frm_processlist">
			流程列表：
			<select id="processListSelect">
			</select>
			<input type="button" value="获取流程列表" onclick="submitForm('process/list','frm_processlist',cb_processList)" />
			<input type="button" value="发起流程" onclick="startProcess()" />
			
		</form>
	</li>
	<li>流程定义：
		
		<form id="frm_processdefineA">
			添加流程：
			
			<input type="text" placeholder="公司ID" name="company_id" value="" />
			<input type="text" placeholder="审核级数" name="lvl" value="2" />
			
			<input type="text" placeholder="流程标题" name="t" value="" />
			<input type="text" placeholder="流程描述" name="c" value="" />
			流程模板:<select id="pda_tmplid" name="tmplid"></select>
			<script type="text/javascript">
				$(function(){
					listProcessTmpl(function(json){
						$.each(json,function(i,n){
							$("#pda_tmplid").append("<option value=\""+n.id+"\">"+n.title+"</option>");
						});
					});
				});
			</script>
			<input type="button" value="添加公式流程" onclick="submitForm('process/add1','frm_processdefineA',function(json){$('#pro_define_id').val(json.pro.id);})" />
		</form>
		
		<form id="frm_processdefineB">
			定义流程审核节点：
			<input type="text" placeholder="流程定义ID" id="pro_define_id" name="pro_define_id" value="" />
			<br/>
			<input type="text" placeholder="节点标题" name="t" value="" />
			<input type="text" placeholder="审核级数" name="audit_level" value="" />
			<input type="text" placeholder="审核者uid,多个用,分开" name="audit_uid" value="" />
			审核关系：
			<select name="audit_rel">
				<option value="OR">任意人通过算通过</option>
				<option value="AND">所有人通过才算通过</option>
			</select>
			<input type="button" value="添加审核节点" onclick="submitForm('process/add2','frm_processdefineB',cb_processList)" />
		</form>
	</li>
	<li>流程办理：
		<form id="frm_processlistdone">
			<input type="text" placeholder="流程定义ID"  name="pro_define_id" value="" />
			
			<div id="process_transact_div"></div>
			<input type="button" value="提交流程" onclick="submitForm('process/transact-init','frm_processlistdone')" />
		</form>
		
		<h4>流程审核</h4>
		<form id="frm_processtransact">
			<input type="text" placeholder="流程实例ID"  name="id" value="" />
			<input type="button" value="获取流程实例" onclick="submitForm('process/transact-get','frm_processtransact',cb_processaudit)" />			
			<div id="processauditdiv">
			</div>
		</form>
	
	</li>
	<li>我发起的流程：
		<form id="frm_processMyList">
			<input type="button" value="我的进行中流程" onclick="submitForm('process/myprocess','frm_processMyList')" />
			<input type="text" placeholder="当前页"  name="p" value="1" />
			<input type="button" value="已完成流程（分页）" onclick="submitForm('process/process-his','frm_processMyList')" />
		</form>
	
	</li>
</ol>	


<h1>模板管理接口</h1>
<ol>
	<li>删除模板：
		<form id="frm_tmpllistbycate">
			<input type="text" placeholder="模板分类ID" name="cateid" value="task" />
			<input type="button" value="模板列表" onclick="submitForm('tmpl/getTmplList','frm_tmpllistbycate')" />
		</form>
	</li>
	<li>新增任务模板：
		<form id="frm_tmpladd">
			<select name="cid">
				<option value="task">任务模板</option>
				<option value="process">流程模板</option>
				<option value="crm">CRM模板</option>
				<option value="log">日志模板</option>
			</select>			
			<input type="text" placeholder="模板标题" name="t" />
			<input type="text" placeholder="模板描述" name="c" />
			<input type="button" value="添加模板" onclick="submitForm('tmpl/add','frm_tmpladd')" />
			<h4>字段定义：<input type="button" value="添加项" onclick="addTmplFields()" /></h4>
			<div id="tmpladd_fields">
			</div>
		</form>
	</li>
	<li>删除模板：
		<form id="frm_tmplremove">
			<input type="text" placeholder="模板ID" name="id" />
			<input type="button" value="删除模板" onclick="submitForm('tmpl/remove','frm_tmplremove')" />
		</form>
	</li>
	<li>
	   获取模板：
		<form id="frm_tmplget">
			<input type="text" placeholder="模板ID" name="id" />
			<input type="button" value="获取模板" onclick="submitFormTmplAdd()" />
		</form>
		
	     保存模板：
		<form id="frm_tmplsave">
			<select name="cid">
				<option value="task">任务模板</option>
				<option value="process">流程模板</option>
				<option value="crm">CRM模板</option>
				<option value="log">日志模板</option>
			</select>			
			<input type="text" placeholder="模板标题" name="t" />
			<input type="text" placeholder="模板描述" name="c" />
			<input type="hidden"  name="id" />
			<input type="button" value="保存模板" onclick="submitForm('tmpl/save','frm_tmplsave')" />
			<h4>字段定义：<input type="button" value="添加项" onclick="addTmplFields('tmplsave_fields')" /></h4>
			<div id="tmplsave_fields">
			</div>
		</form>
	</li>
	<li>获取模板列表数据：
		<form id="frm_tmpldataget">
			<input type="text" placeholder="模板ID对应的记录ID" name="id" value="1" />
			<input type="button" value="获取模板" onclick="submitForm('tmpl/data','frm_tmpldataget')" />
		</form>
	</li>
</ol>	



<h1>签到接口</h1>
<ol>
	<li>签到设置：
		<form id="frm_signsettings">
			<input type="text" placeholder="上班时间" name="workon_time" value="09:00" />
			<input type="text" placeholder="下班时间" name="workoff_time" value="18:00" />
			
			<input type="text" placeholder="考勤周一，输入1,2,3,4,5,6,7" name="work_week" value="1,2,3,4,5" />
			<input type="text" placeholder="误差范围：100米" name="scope" value="100" />
			
			<input type="text" placeholder="公司经度" name="let" value="12.3232" />
			<input type="text" placeholder="公司纬度" name="lot" value="15.5252" />
			
			<input type="text" placeholder="生效开始时间" name="sdt" value="2017-01-01" />
			<input type="text" placeholder="生效结束时间" name="edt" value="2017-12-01" />
			
			<input type="button" value="设置考勤" onclick="submitForm('sign/settings','frm_signsettings')" />
		</form>
	</li>
	<li>获取签到信息：
		<form id="frm_signget">
			<input type="button" value="获取签到信息" onclick="submitForm('sign/get','frm_signget')" />
		</form>
		<form id="frm_signstat">
			<input type="button" value="团队签到统计信息" onclick="submitForm('sign/stat','frm_signstat')" />
		</form>
	</li>
	
	<li>签到签出：
		<form id="frm_sign">
			<input type="text" placeholder="公司经度" name="let" value="29.7965692705" />
			<input type="text" placeholder="公司纬度" name="lot" value="121.5551422606" />
			<input type="button" value="签到" onclick="submitForm('sign/signin','frm_sign')" />
			<input type="button" value="签出" onclick="submitForm('sign/signout','frm_sign')" />
		</form>
	</li>
</ol>


<h1>公司公告接口</h1>
<ol>
	<li>发布公告：
		<form id="frm_annopublish">
			<input type="text" placeholder="公告标题" name="t" value="" />
			<input type="text" placeholder="公告内容" name="c" value="" />
			<input type="button" value="发布公告" onclick="submitForm('anno/publish','frm_annopublish')" />
			
			<input type="text" placeholder="公告id" name="id" value="" />
			<input type="button" value="删除公告" onclick="submitForm('anno/remove','frm_annopublish')" />
		</form>
	</li>
	<li>获取公告列表：
		<form id="frm_anno">
			<input type="button" value="公司公告列表" onclick="submitForm('anno/list','frm_annopublish')" />
		</form>
	</li>
	
</ol>




<!-- 

<h1>工作日志接口</h1>
<ol>
	<li>发布日报
		<form id="frm_notepublish">
			<input type="text" placeholder="日报日期" name="workdate" value="2017-06-01" />
			<input type="text" placeholder="标题" name="t" value="" />
			<input type="text" placeholder="内容" name="c" value="" />
			<input type="text" placeholder="项目名" name="project_name" value="" />

			<input type="text" placeholder="开始时间" name="starttime" value="09:00" />
			<input type="text" placeholder="结束时间" name="endtime" value="12:00" />

			<input type="button" value="发布/保存日报" onclick="submitForm('note/publish','frm_notepublish')" />
			<br/>
			
			<input type="text" placeholder="日报id" name="id" value="" />
			<input type="button" value="获取日报" onclick="submitForm('note/get','frm_notepublish',cb_getnote)" />
			<input type="button" value="删除日报" onclick="submitForm('note/remove','frm_notepublish')" />
		</form>
	</li>
	<li>日报列表：
		<form id="frm_notelist">
			<input type="text" placeholder="当前页" name="p" value="1" />
			<input type="text" placeholder="搜索关键字" name="q" value="" />
			<input type="text" placeholder="开始时间" name="sdt" value="2017-01-01" />
			<input type="text" placeholder="结束时间" name="edt" value="2017-07-01" />
			<input type="button" value="日报列表" onclick="submitForm('note/list','frm_notelist')" />
		</form>
	</li>
	
</ol>

 -->
<div id="json" style="border:1px solid #000;overflow:auto;width:450px;height:450px;position:fixed;top:15px;right:15px;">
</div>



<script id="tpl-tmpl" type="text/html">
				<div>
					<input type="hidden" value="{{id}}" name="f_id" />
					<input type="text" placeholder="Label" name="f_title" value="{{title}}"/>
					<input type="text" placeholder="Laben后缀提示" name="f_suf" value="{{label_suf}}"/>
					<input type="text" placeholder="限制最小字符数" name="f_min" value="{{ui_minlen}}"/>
					<input type="text" placeholder="限制最大字符数" name="f_max" value="{{ui_maxlen}}"/>
					<input type="text" placeholder="表单默认值" name="f_default" value="{{ui_defaultvalue}}"/>
					是否必须：<input type="text" placeholder="N" name="f_isreq" value="{{ui_isreq}}"/>
					控件：<select name="f_ui">
						<option {{ui_component=="text"?"selected":""}} value="text">单行文本</option>
						<option {{ui_component=="number"?"selected":""}} value="number">数字</option>
						<option {{ui_component=="textarea"?"selected":""}} value="textarea">多行文本</option>
						<option {{ui_component=="date"?"selected":""}} value="date">日期</option>
						<option {{ui_component=="datetime"?"selected":""}} value="datetime">日期+时间</option>
						<option {{ui_component=="radio"?"selected":""}} value="radio">单选</option>
						<option {{ui_component=="checkbox"?"selected":""}} value="checkbox">多选</option>

						<option {{ui_component=="select"?"selected":""}} value="select">选择Picker</option>
						<option {{ui_other=="selectuser"?"selected":""}} value="select">单选联系人</option>
						<option {{ui_other=="selectmuser"?"selected":""}} value="select">多选联系人</option>

					</select>
					<input type="text" value="{{ui_other}}" placeholder="填入选项列表，用,分开" name="f_ui_other" />
					<input type="button" value="删除项" onclick="removeTmplFields(this)" />
				</div>
</script>


<script type="text/javascript">
	//create the editor
	var container = document.getElementById("json");
	var options = {};
	var editor = new JSONEditor(container, options);
	
	function getFieldData(dataArray,fieldId){
		var v = "";
		$.each(dataArray,function(i,n){
			if(n.field_id === fieldId){
				v =  n.field_value;
			}
		});
		return v;
	}
	
	//获取工作日志回调
	function cb_getnote(json){
		json = json.note;
		if(json){
			$("#frm_notepublish [name=workdate]").val(new Date(json.workdate).format("yyyy-MM-dd"));
			$("#frm_notepublish [name=t]").val(json.title);
			$("#frm_notepublish [name=c]").val(json.content);
			$("#frm_notepublish [name=project_name]").val(json.project_name);
			$("#frm_notepublish [name=starttime]").val(new Date(json.starttime).format("hh:mm"));
			$("#frm_notepublish [name=endtime]").val(new Date(json.endtime).format("hh:mm"));
			$("#frm_notepublish [name=id]").val(json.id);
		}	
	}
	
	function cb_processaudit(json){
		var _div = $("#processauditdiv");
		var _html = "";
		if(json && json.tmpl && json.tmpl.fields){
			var fields = json.tmpl.fields;
			if(fields.length>0){
				$.each(fields,function(i,n){
					_html += "<div> "+n.title+" : "+getFieldData(json.data,n.id)+"</div>";
				});
			}
		}
		_html += "当前审核人：" + JSON.stringify(json.check_uid);
		
		_html += "审核理由：<textarea id='checkmemo'></textarea>" ;
		
		_html += "<input type='button' value='同意' onclick='transact(\"1\",\""+json.instid+"\")' />&nbsp;&nbsp;";
		_html += "<input type='button' value='拒绝' onclick='transact(\"0\",\""+json.instid+"\")' />";
		
		_div.html(_html);
	}
	
	function transact(d,instid){
		$.ajax({
			url:W+"/r/process/transact",
			data:{"instid":instid,"d":d,memo:$("#checkmemo").val()},
			success:function(json){
				msg(json);
			}
		});
	}
	//显示所有流程模板
	function listProcessTmpl(cb){
		$.ajax({
			url:W+"/r/process/listProcessTmpl",
			data:{},
			success:function(json){
				cb(json);
			}
		});
	}
	
	function startProcess(){
		var proid = $("#processListSelect").val();
		$("#frm_processlistdone [name=pro_define_id]").val(proid);
		$.ajax({
			url:W+"/r/process/transact-start",
			data:{id:proid},
			success:function(json){
				msg(json);
				var _div = $("#process_transact_div");
				_div.empty();
				//展现流程表单		
				if(json && json.tmpl && json.tmpl.fields){
					var _html = "";
					$.each(json.tmpl.fields,function(i,n){
						_html = "<div> "+ n.title;
						if(n.ui_component === "select"){
							_html += " : <select name='"+n.id+"'>";
							var sss = JSON.parse(n.ui_other);
							
							$.each(sss,function(ii,nn){
								console.log(nn);
								_html += "<option value='"+nn+"' "+ (nn === n.ui_defaultvalue?"selected":"") +">"+nn+"</option>";
							});
							_html += " : </select>";
							
						}else{
							_html += " : <input type='text' name='"+n.id+"' value='"+(typeof n.ui_defaultvalue === "undefined" ? "":n.ui_defaultvalue)+"' />";
						}
						_html += "</div>";
						_div.append(_html);
					});
				}
			}
		});
	}
	function cb_processList(json){
		console.log(json);
		$.each(json,function(i,n){
			$("#processListSelect").append("<option value=\""+n.id+"\">"+n.title+"</option>");
		});
	}
	
	function cb_tasksavefieldtitle(ft,fieldId){
		for(var i in ft){
			var _f = ft[i];
			if(_f.id === fieldId){
				return _f.title;
			}
		}
	}
	
	function cb_tasksave(json){
		$("#frm_tasksave [name=id]").val(json.id);
		$("#frm_tasksave [name=content]").val(json.content);
		var _html = "";
		
		var fvs = json.fv;
		
		if(json && fvs){
			for(var t in fvs){
				var _fv = fvs[t];
				_html += cb_tasksavefieldtitle(json.tmpl.fields,_fv.field_id) + " : <input type='text' name='"+_fv.id+"' value='"+_fv.field_value+"' />";
			}
		}
		$("#div_tasksave").html(_html);
	}
	
	function tmplChange(name,idName){
		var _nv = $("input[name=" + name + "]").val();
		$.ajax({
			url:W+"/r/tmpl/get",
			data:{id:_nv},
			success:function(json){
				if(json.ret && json.fields.length>0){
					$("#"+idName).empty();
					for(var i=0;i<json.fields.length;i++){
						var _id = json.fields[i].id;
						var _label = json.fields[i].title;
						var _default = json.fields[i].ui_defaultvalue;
						var _isreq = json.fields[i].ui_isreq;
						var _suf = json.fields[i].label_suf;
						$("#"+idName).append("<div>"+_label+" : <input type='text' name='"+_id+"' value='"+_default+"'/>"+(_isreq==="Y"?" *":"") +_suf+ "</div>");
					}
				}
			}
		});
	}
	
	//获取模板信息
	function submitFormTmplAdd(){
		submitForm('tmpl/get','frm_tmplget',function(json){
			if(json.ret){
				$('#frm_tmplsave [name="id"]').val(json.id);
				$('#frm_tmplsave [name="cid"]').val(json.cate);
				$('#frm_tmplsave [name="t"]').val(json.title);
				$('#frm_tmplsave [name="c"]').val(json.content);
				if(json.fields && json.fields.length>0){
					for(var f in  json.fields){
						addSaveTmplFields(json.fields[f]);
					}
				}
			}
		});
	}
	
	
	function removeTmplFields(obj){
		$(obj).parent().remove();
	}
	function addTmplFields(id){
		if(typeof id === "undefined") id = "tmpladd_fields";
		var html = template('tpl-tmpl', {});
		$("#"+id).append(html);
	}
	
	function addSaveTmplFields(f){
		var html = template('tpl-tmpl', f);
		$("#tmplsave_fields").append(html);
	}
	
	//回调函数
	function cb_getProject(json){
		$('#frm_saveproject input[name=id]').val(json.id);
		$('#frm_saveproject input[name=t]').val(json.title);
		$('#frm_saveproject input[name=c]').val(json.content);
		$('#frm_saveproject input[name=sdt]').val(json.sdt);
		$('#frm_saveproject input[name=edt]').val(json.edt);
		var _leader = "",_joiners = "";
		if(json.members && json.members.length>0){
			for(var m in json.members){
				if(json.members[m].role === "owner"){
					_leader = json.members[m].uid;
				}else{
					_joiners += "," + json.members[m].uid;
				}
			}
		}
		if(_joiners.length>0) _joiners = _joiners.substring(1);
		$('#frm_saveproject input[name=leader]').val(_leader);
		$('#frm_saveproject input[name=joiners]').val(_joiners);
	}
	
	function submitForm(_url,formId,cb){
		var _token = $("#token").val();
		
		if($("#"+formId+" input[name='token']").size()>0){
			$("#"+formId+" input[name='token']").val(_token);
		}else{
			$("#"+formId).append("<input type='hidden' name='token' value='"+_token+"'/>");
		}
	
		$.ajax({
			url:W+"/r/" + _url,
			data:$("#"+formId).serialize(),
			success:function(json){
				msg(json);
				if(typeof cb === "function"){
					cb(json);
				}
			}
		});
	}
	
	function msg(o){
		if(typeof o === "object"){
			editor.set(o);
		}else{
			editor.set(JSON.parse(o));
		}
	}
	$(function(){
		addTmplFields();
		$("#btnLogin").click(function(){
			$.ajax({
				url:W+"/r/auth/login",
				data:{username:$("#username").val(),pwd:$("#pwd").val()},
				success:function(json){
					console.log(json);
					msg(json);
					if(json.ret){
						location.reload();
					}
				}
			});
		});
		
		$("#btnLogin2").click(function(){
			$.ajax({
				url:W+"/r/auth/loginByToken",
				data:{token:$("#token").val()},
				success:function(json){
					console.log(json);
					msg(json);
					if(json.ret){
						location.reload();
					}
				}
			});
		});
		
		$("#btnLogout").click(function(){
			$.ajax({
				url:W+"/r/auth/logout",
				success:function(json){
					console.log(json);
					msg(json);
					if(json.ret){
						location.reload();
					}
				}
			});
		});
		
		
		
	});
</script>
</body>
</html>
