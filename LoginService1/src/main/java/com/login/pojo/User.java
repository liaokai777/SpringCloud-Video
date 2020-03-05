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
   @ApiModelProperty(value = "ID")
   private Integer id;
   @ApiModelProperty(value = "登录名")
   @Length(min = 6, max = 10)
   private String loginname;
   @ApiModelProperty(value = "密码")
   @Length(min = 6, max = 10)
   private String loginkey;
   @ApiModelProperty(value = "IP")
   private String ip;
   @ApiModelProperty(value = "角色ID")
   private Integer roleid;
   public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
       this.id = id;
   }

   public String getLoginname() {
       return loginname;
   }
   public void setLoginname(String loginname) {
       this.loginname = loginname == null ? null : loginname.trim();
   }

   public String getLoginkey() {
       return loginkey;
   }

   public void setLoginkey(String loginkey) {
       this.loginkey = loginkey == null ? null : loginkey.trim();
   }

   public String getIp() {
       return ip;
   }

   public void setIp(String ip) {
       this.ip = ip == null ? null : ip.trim();
   }

   public Integer getRoleid() {
       return roleid;
   }

   public void setRoleid(Integer roleid) {
       this.roleid = roleid;
   }

	@Override
	public String toString() {
		return "User [id=" + id + ", loginname=" + loginname + ", loginkey=" + loginkey + ", ip=" + ip + ", roleid="
				+ roleid + "]";
	}
	
}
