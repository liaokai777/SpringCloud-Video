package com.login.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	/**
	 * 生成TOKEN
	 * @return
	 */
	public static String setToken(String id) {
		JwtBuilder builder= Jwts.builder()
				.setId(id)   //设置唯一编号
				.setSubject("topic")//设置主题  可以是JSON数据
				.setIssuedAt(new Date())//设置签发日期
				.signWith(SignatureAlgorithm.HS256,"aaaaaa");//设置签名 使用HS256算法，并设置SecretKey(字符串)
		//构建 并返回一个字符串 
		return builder.compact();
	}
	/**
	 * 解析TOKEN
	 * @return
	 */
	public static Boolean getToken(String compact) {
		Boolean flag=true;
		if(compact==null) {
			return false;
		}
		try {
			Claims claims = Jwts.parser().setSigningKey("aaaaaa").parseClaimsJws(compact).getBody();
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

}
