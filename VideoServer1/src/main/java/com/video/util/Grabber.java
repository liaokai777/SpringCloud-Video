package com.video.util;

import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

public class Grabber {
	// 转换为图像
	public static ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
	/**
	 * 创建视频捕获器
	 * @return
	 */
	public OpenCVFrameGrabber createGrabber(String channelId) {
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重
		return grabber;
	}

	public OpenCVFrameGrabber startGrabber(String channelId) {
		OpenCVFrameGrabber grabber=createGrabber(channelId);
		Boolean exceptionSign=false;
		try {
			grabber.start();
		} catch (Exception e) {
			exceptionSign=true;
			e.printStackTrace();
		}finally {
			try {
				if(exceptionSign&&grabber!=null) {
				  grabber.stop();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return grabber;
	}
}
