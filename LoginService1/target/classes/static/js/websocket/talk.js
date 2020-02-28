				
$(document).ready(function() {
				//连接聊天服务器
				var talkSocket = new WebSocket("ws://localhost:9998/ws");
				//打开连接
				talkSocket.onopen = function() {
					  console.log("client：打开聊天连接");
				};
				talkSocket.onmessage = function(e) {
					 var msg=e.data;
				     console.log("client：接收到服务端的消息 " + msg);
				     if("success"==msg){//发送消息的响应
				     	 console.log("发送成功");
				     	//清除旋转
				    	$(".contentSpan").find("i").remove();
				    	$(".contentSpan").find("svg").remove();
				     }else{//接收其他人消息
				    	 var nameSpan="<span class='nameSpan'>"+"张三:"+"</span>";
			        	 var contentSpan="<span class='contentSpan'>"+msg+"<i class='fas fa-spinner fa-pulse'></i></span>";
						 var otherscontent="<li id=''>"+nameSpan+contentSpan+"</li>";
				    	 $('#display_talk_ul').append(otherscontent); 
				     }
					 /* setTimeout(() => {
					 talkSocket.close();
					 }, 5000); */
				}; 
				//点击发送按钮
				$('#sendmsg').click(
						function(){
							//获取登录用户名
							var loginName=$('#loginName').text();
							console.log("loginName"+loginName);
							//获取用户输入框文本
							var input_user=$('#input_user').val();
							//组装DIV
			        		var nameSpan="<span class='nameSpan'>"+loginName+":"+"</span>";
			        		var contentSpan="<span class='contentSpan'>"+input_user+"<i class='fas fa-spinner fa-pulse'></i></span>";
							var mycontent="<li id=''>"+nameSpan+contentSpan+"</li>";
			        		//添加到聊天展视DIV
			       			$('#display_talk_ul').append(mycontent);
			        		//发送数据
			       			talkSocket.send(input_user);
				});
				//清理屏幕li
				$('#clear_li').click(
					function(){
						$("#display_talk_ul").find("li").remove();
					}
				);
				
});