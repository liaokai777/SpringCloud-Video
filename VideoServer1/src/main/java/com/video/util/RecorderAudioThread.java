package com.video.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;

public class RecorderAudioThread implements Runnable {
	private volatile FFmpegFrameRecorder recorder = null;
	public volatile Boolean sign=false;
	public RecorderAudioThread(FFmpegFrameRecorder recorder) {
		this.recorder = recorder;
	}
	public void setSign(Boolean sign) {
		this.sign = sign;
	}

	@Override
	public void run() {
		/**
		 * 设置音频编码器 最好是系统支持的格式，否则getLine() 会发生错误
		 * 采样率:44.1k;采样率位数:16位;立体声(stereo);是否签名;true:
		 * big-endian字节顺序,false:little-endian字节顺序(详见:ByteOrder类)
		 */
		AudioFormat audioFormat = new AudioFormat(44100.0F, 16, 2, true, false);
		
		// 通过AudioSystem获取本地音频混合器信息
		Mixer.Info[] minfoSet = AudioSystem.getMixerInfo();
		// 通过AudioSystem获取本地音频混合器
		Mixer mixer = AudioSystem.getMixer(minfoSet[4]);
		// 通过设置好的音频编解码器获取数据线信息
		DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
		// 打开并开始捕获音频
		// 通过line可以获得更多控制权
		// 获取设备：TargetDataLine line
		TargetDataLine line = null;
		try {
			line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
			line.open(audioFormat);
			line.start();
			// 获得当前音频采样率
			int sampleRate = (int) audioFormat.getSampleRate();
			// 获取当前音频通道数量
			int numChannels = audioFormat.getChannels();
			// 初始化音频缓冲区(size是音频采样率*通道数)
			int audioBufferSize = sampleRate * numChannels;
			byte[] audioBytes = new byte[audioBufferSize];
			int nBytesRead=0;
			int nSamplesRead=0;
			short[] samples;
			ShortBuffer sBuff;
			while((nBytesRead=line.read(audioBytes, 0, line.available()))!=-1) {// 非阻塞方式读取
					if(sign) {
						break;
					}
					// 因为我们设置的是16位音频格式,所以需要将byte[]转成short[]
					nSamplesRead = nBytesRead / 2;
					samples = new short[nSamplesRead];
					/**
					 * ByteBuffer.wrap(audioBytes)-将byte[]数组包装到缓冲区
					 * ByteBuffer.order(ByteOrder)-按little-endian修改字节顺序，解码器定义的
					 * ByteBuffer.asShortBuffer()-创建一个新的short[]缓冲区
					 * ShortBuffer.get(samples)-将缓冲区里short数据传输到short[]
					 */
					ByteBuffer.wrap(audioBytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(samples);
					// 将short[]包装到ShortBuffer
					sBuff = ShortBuffer.wrap(samples, 0, nSamplesRead);
					// 按通道录制shortBuffer
					if(recorder!=null) {
						recorder.recordSamples(sampleRate, numChannels, sBuff);
					}
			}
		}catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			if(line!=null) {
				line.close();
				line=null;
				System.out.println("关闭读取音频");
			}
		}
	}

}
