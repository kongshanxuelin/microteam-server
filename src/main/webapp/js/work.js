W = "";
(function( $ ) {
    /*
     * 公告相关的业务 
     */
	$.anno = {
    		publish:function(title,content,cb){
    			$.ajax({
    				url:W+"/r/anno/publish",
    				data:{
    					t:title,
    					c:content
    				},
    				success:function(json){
    					if(typeof(cb) === "function"){
    						cb(json);
    					}
    				}
    			});
    		},
    		remove:function(id,cb){
    			var _url = W + "/r/anno/remove";
				$.ajax({
					url:_url,
					data:{"id":id},
					success:function(json){
						if(typeof(cb) === "function"){
    						cb(json);
    					}
					}
				});
    		}
    };
	
	/*****
	 * 
	 */
    $.project = {
    		add:function(options){
    			//title,content,fzr,joiners,startDate,endDate,cb
    			options = $.extend( {
				"t":options.title,
				"c":options.content,
				"leader":options.fzr || "",
				"joiners":options.joiners || "",
				"sdt":options.startDate||"1990-01-01",
				"edt":options.endDate||"2050-01-01",
				token:options.token||""
			    }, options);
    			var _url = W + "/r/project/add";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		mylist:function(options){
    			options = $.extend( {
    				token:options.token||""
    			}, options);
    			var _url = W + "/r/project/myProjects";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		remove:function(options){
    			options = $.extend({
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/project/remove";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		get:function(options){
    			options = $.extend({
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/project/get";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		save:function(options){
    			options = $.extend( {
    				"id":options.id,
    				"t":options.title,
    				"c":options.content,
    				"leader":options.fzr,
    				"joiners":options.joiners,
    				"sdt":options.startDate||"1990-01-01",
    				"edt":options.endDate||"2050-01-01",
    				token:options.token||""
    			    }, options);
        			var _url = W + "/r/project/save";
    				$.ajax({
    					url:_url,
    					data:options,
    					success:function(json){
    						if(typeof(options.success) === "function"){
    							options.success(json);
        					}
    					}
    				});
    		},
    		//0:就绪  1：完成  2：搁置
    		changeStatus:function(options){
    			options = $.extend({
    				id:options.id,
    				sts:options.sts,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/project/setProjectSts";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		//role:owner：创建者，leader：组长，member：成员
    		changeUserRole:function(options){
    			options = $.extend({
    				id:options.id,
    				uid:options.uid,
    				role:options.role,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/project/setLeader";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		}
    };
    
    $.task = {
    		list:function(options){
    			options = $.extend({
    				proid:options.proid,
    				//1(未完成),2(已完成),3(已归档)
    				sts:options.sts,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/list";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		add:function(options){
    			options = $.extend( {
    				"proid":options.proid,
    				"tmpl_id":options.tmpl_id,
    				"content":options.content,
    				"end_time":options.end_time,
    				"joiners":options.joiners,
    				"fzr":options.fzr,
    				"joiner":options.joiner,
    				"auditer":options.auditer,
    				token:options.token||""
    			    }, options);
        			var _url = W + "/r/task/add";
    				$.ajax({
    					url:_url,
    					data:options,
    					success:function(json){
    						if(typeof(options.success) === "function"){
    							options.success(json);
        					}
    					}
    				});
    		},
    		remove:function(options){
    			options = $.extend({
    				id:options.id,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/remove";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		save:function(options){
    			options = $.extend({
    				id:options.id,
    				content:options.content,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/save";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		changeStatus:function(options){
    			options = $.extend({
    				id:options.id,
    				//1(未完成),2(已完成),3(已归档)
    				sts:options.sts,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/changeStatus";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		transmitTask:function(options){
    			options = $.extend({
    				id:options.id,
    				uid:options.uid,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/changeCreater";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		moveTask:function(options){
    			options = $.extend({
    				id:options.id,
    				proid:options.proid,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/changeProject";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		addComment:function(options){
    			options = $.extend({
    				taskId:options.taskId,
    				content:options.content,
    				stype:"t",
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/addComment";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		removeComment:function(options){
    			options = $.extend({
    				cid:options.cid,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/task/removeComment";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		}
    };
    
    $.tmpl = {
    		list:function(options){
    			options = $.extend({
    				cateid:options.cateid,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/tmpl/getTmplList";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		remove:function(options){
    			options = $.extend({
    				id:options.id,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/tmpl/remove";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		get:function(options){
    			options = $.extend({
    				id:options.id,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/tmpl/get";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		},
    		data:function(options){
    			options = $.extend({
    				id:options.id,
    				token:options.token||""
			    }, options);
    			var _url = W + "/r/tmpl/data";
				$.ajax({
					url:_url,
					data:options,
					success:function(json){
						if(typeof(options.success) === "function"){
							options.success(json);
    					}
					}
				});
    		}
    };
    
    
})( jQuery );