package com.login.pojo;

import java.io.Serializable;

public class ResponseFileInfo implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	/**
     * ��ʼ ��ȡ��
     */
    private long start;
    /**
     * �ļ��� MD5ֵ
     */
    private String file_md5;
    /**
     * �ļ����ص�ַ
     */
    private String file_url;
    /**
     * �ϴ��Ƿ����
     */
    private boolean end;
    /**
     * ����
     */
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public String getFile_md5() {
		return file_md5;
	}
	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "ResponseFileInfo [start=" + start + ", file_md5=" + file_md5 + ", file_url=" + file_url + ", end=" + end
				+ "]";
	}
    
}
