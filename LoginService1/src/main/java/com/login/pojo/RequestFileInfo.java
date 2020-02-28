package com.login.pojo;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

public class RequestFileInfo implements Serializable{
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private File file;// �ļ�
	    private String file_name;// �ļ���
	    private long starPos;// ��ʼλ��
	    private byte[] bytes;// �ļ��ֽ�����
	    private int endPos;// ��βλ��
	    private String file_md5; //�ļ���MD5ֵ
	    private String file_type;  //�ļ�����
	    private long file_size; //�ļ��ܳ���
	    private int time;//����
	    
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
		public String getFile_name() {
			return file_name;
		}
		public void setFile_name(String file_name) {
			this.file_name = file_name;
		}
		public long getStarPos() {
			return starPos;
		}
		public void setStarPos(long starPos) {
			this.starPos = starPos;
		}
		public byte[] getBytes() {
			return bytes;
		}
		public void setBytes(byte[] bytes) {
			this.bytes = bytes;
		}
		public int getEndPos() {
			return endPos;
		}
		public void setEndPos(int endPos) {
			this.endPos = endPos;
		}
		public String getFile_md5() {
			return file_md5;
		}
		public void setFile_md5(String file_md5) {
			this.file_md5 = file_md5;
		}
		public String getFile_type() {
			return file_type;
		}
		public void setFile_type(String file_type) {
			this.file_type = file_type;
		}
		public long getFile_size() {
			return file_size;
		}
		public void setFile_size(long file_size) {
			this.file_size = file_size;
		}
		@Override
		public String toString() {
			return "RequestFileInfo [file=" + file + ", file_name=" + file_name + ", starPos=" + starPos + ", bytes="
					+ Arrays.toString(bytes) + ", endPos=" + endPos + ", file_md5=" + file_md5 + ", file_type="
					+ file_type + ", file_size=" + file_size + ", time=" + time + "]";
		}
		
}
