package com.video.util;

import javax.swing.WindowConstants;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class RecorderVideoThread implements Runnable {
	public volatile Boolean sign=false;
	private OpenCVFrameGrabber grabber;
	private FFmpegFrameRecorder recorder;
	public RecorderVideoThread(OpenCVFrameGrabber grabber,FFmpegFrameRecorder recorder) {
		this.grabber=grabber;
		this.recorder=recorder;
	}
	public void setSign(Boolean sign) {
		this.sign = sign;
	}
	@Override
	public void run() {
		try {
			IplImage grabbedImage;
			Frame frame;
			//CanvasFrame canvas = createCanvasFrame();
			System.out.println("录制中");
			while ((grabbedImage = Grabber.converter.convert(grabber.grab())) != null) {
				if(sign) {
					break;
				}
				frame = Grabber.converter.convert(grabbedImage);
				if(frame!=null) {
					//canvas.showImage(frame);//获取摄像头图像并放到窗口上显示
					recorder.record(frame);
				}
			}
		} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(grabber!=null) grabber.stop();
				if(recorder!=null) recorder.stop();
				grabber=null;
				recorder=null;
				System.out.println("关闭抓取器和录制器");
			} catch (Exception e) {
				e.printStackTrace();
			} catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static CanvasFrame createCanvasFrame() {
		 CanvasFrame canvas = new CanvasFrame("");
	     canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	     canvas.setAlwaysOnTop(true);
	     return canvas;
	}

}
