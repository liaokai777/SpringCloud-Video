package com.login.pojo;

import java.io.Serializable;
import java.net.SocketAddress;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private String remoteAddress;
	private String conent;
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	public String getConent() {
		return conent;
	}
	public void setConent(String conent) {
		this.conent = conent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Message [from=" + from + ", to=" + to + ", remoteAddress=" + remoteAddress + ", conent=" + conent + "]";
	}
	
}
