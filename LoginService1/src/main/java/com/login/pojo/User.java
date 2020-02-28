package com.login.pojo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 使用构造者模式
 * @author AS US
 *
 */
@ApiModel(value = "com.login.pojo.User", description = "登录账户参数")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "账户")
	@Length(min = 6, max = 10)
	private String loginName;
	@ApiModelProperty(value = "密码")
	@Length(min = 6, max = 10)
	private String loginKey;
	@ApiModelProperty(value = "IP")
	private String ip;
	//构造器尽量缩小范围
	public User() {
		
	}
	//拷贝一份
	public User(User user) {
		this.loginName =user.loginName;
		this.loginKey = user.loginKey;
		this.ip = user.ip;
	}
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginKey() {
		return loginKey;
	}
	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	@Override
	public String toString() {
		return "User [loginName=" + loginName + ", loginKey=" + loginKey + ", ip=" + ip + "]";
	}

	/**
	 * 用方法链的方式创建User对象
	 * @author AS US
	 *
	 */
	public static class Builder{
		private User user;
		public Builder() {
			user=new User();
		}
		public Builder setLoginName(String loginName){
			user.loginName=loginName;
			return this;
		}
		public Builder setLoginKey(String loginKey){
			user.loginKey=loginKey;
			return this;
		}
		public Builder setIP(String ip){
			user.ip=ip;
			return this;
		}
		public User build() {
			return new User(user);
		}
		
	}
	
}
