package com.video.service;

import java.util.concurrent.ConcurrentHashMap;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

import com.video.util.Grabber;
import com.video.util.Recorder;
import com.video.util.RecorderAudioThread;
import com.video.util.RecorderVideoThread;

public class VideoService {
	private static ConcurrentHashMap<String, RecorderVideoThread> recorderVideoMap = new ConcurrentHashMap<String,RecorderVideoThread>();
	private static ConcurrentHashMap<String, RecorderAudioThread> recorderAudioMap = new ConcurrentHashMap<String,RecorderAudioThread>();
	public static void doService(String channelId,Boolean flag){
		if(flag) {//开启录制线程
			Frame grabFrame= null;
			IplImage grabImage = null;
			//每个用户一个抓取器
			OpenCVFrameGrabber grabber = new Grabber().startGrabber(channelId);
			try{
				if((grabFrame=grabber.grab()) !=null) {//视频的宽度必须是32的倍数，高度必须是2的倍数
					System.out.println("取到第一帧");
					grabImage = Grabber.converter.convert(grabFrame);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			//每个用户一个录制器
			FFmpegFrameRecorder recorder = new Recorder().startRecorder(grabImage.width(), grabImage.height(), channelId);
			//启动一个线程录制视频
			RecorderVideoThread recorderVideo = new RecorderVideoThread(grabber,recorder);
			recorderVideoMap.put(channelId, recorderVideo);
			new Thread(recorderVideo,"RecorderVideo"+channelId).start();
			//启动一个线程录制音频
			RecorderAudioThread recorderAudio = new RecorderAudioThread(recorder);
			recorderAudioMap.put(channelId, recorderAudio);
			new Thread(recorderAudio,"RecorderAudio"+channelId).start();
		}else {//关闭录制线程
			RecorderVideoThread recorderVideo = recorderVideoMap.get(channelId);
			RecorderAudioThread recorderAudio = recorderAudioMap.get(channelId);
			recorderVideo.sign=true;
			recorderAudio.sign=true;
			recorderVideoMap.remove(channelId);
			recorderAudioMap.remove(channelId);
			recorderVideo=null;
			recorderAudio=null;
		}
	}
}
