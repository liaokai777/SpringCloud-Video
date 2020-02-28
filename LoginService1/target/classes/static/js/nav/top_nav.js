					//控制点播展开|收缩
					flag=false;
					//控制直播列表展开|收缩
					var showList=false;
					//点播按钮点击
					$("#playList").click(function(){
						flag=!flag;
						if(flag){
						  $("#playListIco").attr("class","fas fa-angle-down fa-1x");
						  $("#container_centre_left").hide();
						}else{
						  $("#playListIco").attr("class","fas fa-angle-right fa-1x");
						  $("#container_centre_left").show();
						}
						
						if(!showList){//如果直播列表收缩状态
							setPlayerSize();
						}else{//如果直播列表展开状态
							if(flag){
								console.log("直播列表-12");
							    $("#container_liveList").attr("class","col-12");
							}else{
								console.log("直播列表-10");
							    $("#container_liveList").attr("class","col-10");
							}
						}
					});
					
					/*直播按钮点击*/
					$("#liveTV").click(function(){
						showList=true;
						showLiveList(showList);
					});
					
					//展视直播列表
					function showLiveList(showList){
						if(showList){
							//中央视频区与聊天区隐藏 
							$("#container_centre").hide();
							$("#container_centre_right").hide();
							//停止视频
							var video = document.getElementById('videoElement');
							console.log("video"+video);
							if(video!=null&&flvPlayer!=null){
								 console.log("视频停止播放");
								 //video.pause();
								 flvPlayer.pause();
						         flvPlayer.unload();
						         flvPlayer.detachMediaElement();
						         flvPlayer.destroy();
						         flvPlayer = null;
							}
						    //展视直播列表
						    if(flag){//收缩
						    	console.log("直播列表-12");
						    	$("#container_liveList").attr("class","col-12");
						    }
						    $("#container_liveList").show();
						}
					}