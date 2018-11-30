
function openDialog(pageId,_title,params){
	var paramStr = "";
	if(typeof(params)!="undefined"){
		paramStr = "";
		var idx = 1;
		$.each(params,function(i,n){
			if(idx == 1){
				paramStr += "?" + i + "=" + n;
			}else{
				paramStr += "&" + i + "=" + n;
			}
			++idx;
		});
		
	}
	layer.open({
		   type: 2, 
		   title:_title||"对话框",
		   skin: 'layui-layer-rim', //加上边框
		   area: ['800px', '600px'], //宽高
		   content: W + "/" + pageId + ".jsp" + paramStr
		});
}
function doPublishAnno(){
	var _html = "<div style='margin:10px;'>"+
	'<p>标题：<input style="margin-left: 8px;width:650px;" class="text-input" name="t" type="text" required></p>'+
	'<p>内容：<br/><textarea rows=3 style="width:200px;height:50px;" class="text-input" name="c"></textarea></p>'+
	"</div>";
	layer.open({
		  type: 1,
		  area: ['800px', '250px'],
		  btn: ['发布'],
		  content: _html,
		  yes:function(index,layero){
			  $.anno.publish($("[name=t]").val(),$("[name=c]").val(),function(json){
				 if(json.ret){
					 layer.alert("发布成功！");
				 }else{
					 layer.alert("发布失败！"); 
				 }
				 layer.close(index);
			  });
		  }
	});
}
function publishAnno(title,content,cb){
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
}
function createTeam(cb){
	layer.prompt({
		title: '输入团队名',
	},function(value,index,ele){
		$.ajax({
			url:W+"/r/user/teamCreate?title="+value,
			success:function(json){
				if(json.ret){
					layer.alert("创建团队成功！");
					layer.close(index);
					if(typeof(cb) === "function"){
						cb();
					}
				}else{
					layer.alert("创建团队失败！");
				}
						
			}
		});
		
	});
}

$(function(){
	   $(".tips").tip_cards({
	       entrance: "bottom", // This option let you determine the direction of the fly in entrance animation when all the cards appears. Available options are "bottom", "left", "right", and "top". The default value is "bottom".
	       column: 4, // The plugin also let you define how the card will be displayed and aligned. You can set the column of cards here. The default value is 4.
	       margin: "1%", // You can define the margins between each cards here. Percentage is currently support at this point. The default is "1%".
	       selector: "> li", // You can define a custom selector if you do not want to use ul and li tags. This option accepts the normal CSS selector. The default value is "> li"
	       hoverTilt: "right", // Define the tilt direction when cards are hovered here. Available options are "right", "left", "up", and "down". The default value is "right".
	       triggerSelector: "> li a", // You can also define a custom selector for the trigger button here. The default value is "> li a" which will use the link inside a list as a trigger to activate the card.
	       cardFlyDirection: "all", // You can define the card fly animation when the modal appears here. Available options are "all", "top", "bottom", "left", and "right". The default value is "all" which will have the cards fly in from all direction and stack up under the opened modal
	       closeButton: "X", // You can define the content of the close button here. Change this to false to prevent the plugin from automatically generating the close button. The default string is "X".
	       flipButton: "Flip", // You can define the content of the flip button here. Change this to false to prevent the plugin from automatically generating the flip button. The default string is "Flip".
	       navigation: true, // Set this to true to allow users to navigate from one card to another when modal is opened. Change it to false to disable it. The default value is true.
	       beforeOpen: null, // A callback function that will be executed before the modal opens.
	       afterOpen: null // A callback function that will be executed after the modal opens.
       }); 
	   
	   var _url = W + "/r/user/teamList";
	   $.ajax({
			url:_url,
			success:function(json){
				if(json && json.length > 0){
					$.each(json,function(i,n){
						if(TEAMID === n.team.id){
							$("#teamList").append("<option selected value=\""+n.team.id+"\">"+n.team.title+"</option>");
						}else{
							$("#teamList").append("<option value=\""+n.team.id+"\">"+n.team.title+"</option>");
						}
					});
				}else{
					$("#teamListSpan").html("当前还没团队！");
				}
			    $("#teamList").chosen({
				    width: '150px',
				    disable_search: true
				});
			    
			    $('#teamList').change(function(evt, data){
			        $.ajax({
						url:W + "/r/user/teamChange",
						data:{teamid:data.selected},
						success:function(json){
							if(json.ret){
								layer.alert("切换团队成功！");
							}else{
								layer.alert("切换团队失败！");
							}
						}
			        });
			    });
			    
			}
	   });
		

});