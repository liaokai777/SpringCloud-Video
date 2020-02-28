				
$(function(){
					//默认未播放
					var play=false;	
					//非播放状态
					var ready=false;
					//开始直播
					$('#start_zb').click(
							function(){
								//连接直播服务器
								videoSocket = new WebSocket("ws://localhost:9997/ws");
								//打开连接
								videoSocket.onopen = function() {
									  console.log("client：打开视频连接");
									  videoSocket.send("startZB");
								};
								videoSocket.onmessage = function(e) {
									 var msg=e.data;
									 console.log("视频client：接收到服务端的消息 " + msg);
									 if(msg=='OK'){
									 	 //视频内隐藏
										 play=true;	
										 setPlayerSize();
									 }
								};
						    }
					);
					//结束直播
					$("#end_zb").click(function(){
						play=false;
						//置为非播放状态
						ready=false;
						//停止播放
						var video = document.getElementById('videoElement');
						if(video!=null&&flvPlayer!=null){
							console.log("视频停止播放");
							videoSocket.send("endZB");
							flvPlayer.pause();
						    flvPlayer.unload();
						    flvPlayer.detachMediaElement();
						    flvPlayer.destroy();
						    flvPlayer = null;
						}
						//隐藏视频
						$('#mainVideo').hide();
						//展视视频区内饰
						$('#unvideo-container').show();
						//隐藏结束直播按钮
						$('#end_zb').hide();
					});
					//控制播放器大小
					setPlayerSize=function rePlayerSize(){
						if(flag){
							console.log("true展开");
							/*展开*/
							$("#container_centre_left").hide();
							$("#container_centre").attr("class","col-9");
							$("#video-tool").hide();
							if(play){
								console.log("奇数播放");
								//隐藏视频内饰
								$('#unvideo-container').hide();
								var centreHeight=$("#container_centre").height();
							    var centreWidth=$("#container_centre").width();
								console.log("centreHeight"+centreHeight+"视频高"+centreHeight+"视频宽"+centreWidth);
								if (!ready){
									//非播放状态加载视频播放页
									$('#mainVideo').load('video.html');
								}
								$("#mainVideo").height(centreHeight);
								$("#mainVideo").width(centreWidth);
								$('#mainVideo').show();
								/*隐藏结束直播按钮*/
							    $('#end_zb').hide();
							    ready=true;
							}else{
								console.log("未播放");
								var centreHeight=$("#container_centre").height();
								var centreWidth=$("#container_centre").width();
								$("#unvideo-container").height(centreHeight);
								var videoHeight=centreHeight;
								var videoWidth=centreWidth;
								
								$("#mainVideo").height(videoHeight);
								$("#mainVideo").width(videoWidth);
								console.log("centreHeight"+centreHeight+"视频高"+videoHeight+"视频宽"+videoWidth);
							}
						}else{
							console.log("false收起");
							/*收起*/
							$("#container_centre_left").show();
							$("#container_centre").attr("class","col-7");
							if(play){
								console.log("播放");
								//隐藏视频内饰
								$('#unvideo-container').hide();
								var toolHeight=$("#video-tool").height();
								var centreHeight=$("#container_centre").height();
							    var centreWidth=$("#container_centre").width();
								var videoHeight=centreHeight-toolHeight;
								var videoWidth=centreWidth;
								console.log("视频高"+videoHeight+"视频宽"+videoWidth);
								$('#unvideo-container').height(videoHeight);
								if (!ready){
									//非播放状态加载视频播放页
									$('#mainVideo').load('video.html');
								}
								$("#mainVideo").innerHeight(videoHeight);
								$("#mainVideo").width(videoWidth);
								$('#mainVideo').show();
								//展视结束直播按钮
							    $('#end_zb').show();
							    ready=true;
							}else{
								console.log("未播放");
								var centreHeight=$("#container_centre").height();
								var toolHeight=$("#video-tool").height();
								var videoHeight=centreHeight-toolHeight;
								console.log("centreHeight"+centreHeight+"视频高"+videoHeight+"toolHeight"+toolHeight);
								$("#unvideo-container").height(videoHeight);
							}
							$("#video-tool").show();
						}
						
					}
					
});
