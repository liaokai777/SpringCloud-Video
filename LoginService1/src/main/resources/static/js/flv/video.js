			var videoContainer = document.getElementById('videoContainer');
			var video = document.getElementById('videoElement');
			//隐藏控件
			video.controls = false;
			 //播放flv流
			 if (flvjs.isSupported()) {
		        var videoElement = document.getElementById('videoElement');
		        flvPlayer = flvjs.createPlayer({
		        	enableWorker: true,
		            type: 'flv',
		            isLive:true,
		            withCredentials:false,
		            hasAudio:true,
		            hasVideo:true,
		            lazyLoadMaxDuration: 3 * 60,
                    seekType: 'range',
		            url: 'http://49.235.177.235:80/live?port=1935&app=myapp&stream=test'
		        });
		        flvPlayer.attachMediaElement(videoElement);
		        flvPlayer.load();
		        //flvPlayer.play();
		    }
			
			//全屏按钮单击
			$("#fullScreen").click(function(){
				//是否支持全屏
				var fullScreenEnabled = !!(document.fullscreenEnabled || document.mozFullScreenEnabled || document.msFullscreenEnabled || document.webkitSupportsFullscreen || document.webkitFullscreenEnabled || document.createElement('video').webkitRequestFullScreen);
				if (fullScreenEnabled) {
					handleFullscreen();
				}else{
					console.log("不支持全屏");
				}
			});
			
			//控制全屏
			var handleFullscreen = function() {
			   if (isFullScreen()) {
			      if (document.exitFullscreen) document.exitFullscreen();
			      else if (document.mozCancelFullScreen) document.mozCancelFullScreen();
			      else if (document.webkitCancelFullScreen) document.webkitCancelFullScreen();
			      else if (document.msExitFullscreen) document.msExitFullscreen();
			      setFullscreenData(false);
			   }else {
			      if (videoContainer.requestFullscreen) videoContainer.requestFullscreen();
			      else if (videoContainer.mozRequestFullScreen) videoContainer.mozRequestFullScreen();
			      else if (videoContainer.webkitRequestFullScreen) videoContainer.webkitRequestFullScreen();
			      else if (videoContainer.msRequestFullscreen) videoContainer.msRequestFullscreen();
			      setFullscreenData(true);
			   }
			}
			//是否全屏
			var isFullScreen = function() {
			   return !!(document.fullScreen || document.webkitIsFullScreen || document.mozFullScreen || document.msFullscreenElement || document.fullscreenElement);
			}
			//设置全屏数据
			var setFullscreenData = function(state) {
			   videoContainer.setAttribute('data-fullscreen', !!state);
			}
			   document.addEventListener('fullscreenchange', function(e) {
			   setFullscreenData(!!(document.fullScreen || document.fullscreenElement));
			});
			document.addEventListener('webkitfullscreenchange', function() {
			   setFullscreenData(!!document.webkitIsFullScreen);
			});
			document.addEventListener('mozfullscreenchange', function() {
			   setFullscreenData(!!document.mozFullScreen);
			});
			document.addEventListener('msfullscreenchange', function() {
			   setFullscreenData(!!document.msFullscreenElement);
			});
			