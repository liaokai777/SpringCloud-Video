package com.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * 验证Session 
 * @author AS US
 */
public class BaseController {
	@ModelAttribute
    public String checkSession(HttpSession session) throws Exception {
    	System.out.println("登录权限控制");
        if (session.getAttribute("user") == null) {
            return "redirect:index.html";
        }
        return null;
    }
}
