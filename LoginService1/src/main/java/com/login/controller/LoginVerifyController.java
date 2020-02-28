package com.login.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.login.pojo.User;
import com.login.service.UserService;
import com.login.util.IPUtil;
import com.login.util.JWTUtils;
import com.login.util.MapUtil;
import com.login.util.NettyUtil;
import com.login.util.RedisUtil;

import io.netty.channel.Channel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;





@Controller//单实例多线程,在controller中不要定义属性,那么单例的controller就是线程安全的
public class LoginVerifyController {
	private static Logger logger = LoggerFactory.getLogger(LoginVerifyController.class);
	@Resource//@Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入
	private RedisUtil redisUtil;//redis
	@Autowired
	private UserService userService;
	 /**
     * 如果前端传过来的值的名称与参数的名称相同，SpringMVC会自动注入
	 * @throws IOException 
     * @ModelAttribute 注入User user
     * @Validated 登录数据校验  BindingResult错误信息   
     * */
	@ApiOperation(value = "检验账户密码")
	@RequestMapping("/check")
    public String toIndex(@ModelAttribute @Validated User user, BindingResult error,Model model,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {	
		
		//获取客户端请求IP
		String ip = IPUtil.getIpAddress(request);
		//登录数据长度校验
		if (error.hasErrors()||user==null) {
			logger.info("登录数据不合法"+error.getFieldError().getDefaultMessage());
			model.addAttribute("msg", error.getFieldError().getDefaultMessage());
			//session.removeAttribute("user");
	    	return "redirect:index.html";
		}
		//不同机子重复登录校验
		String loginName = user.getLoginName();
		String loginKey = user.getLoginKey();
		User u = (User)session.getAttribute(loginName);
		if(u!=null&&loginName.equals(u.getLoginName())
		          &&ip!=null&&!ip.equals(u.getIp())) {
			model.addAttribute("msg", "该用户"+loginName+"已经登录");
			logger.info("该用户"+loginName+"已经登录");
	    	return "redirect:index.html";
		}
		//插redis缓存
		redisUtil.set(loginName, user);
		
		//redis数据库账户密码校验
		User userKey = (User)redisUtil.get(loginName);
		if(loginKey!=null&&loginKey.equals(userKey.getLoginKey())) {
			//登录成功把将用户信息保存到session中
			user.setIp(ip);
			session.setAttribute(loginName, user);
			model.addAttribute("loginName",loginName);
			logger.info("登录成功"+user.toString());
		    return "main";
		}
		//查mysql数据库
		//user=userService.selectUserById(1);
		//System.out.println("查询"+user.toString());
		model.addAttribute("msg", "用户名或密码错误，请重新登录！ ");
    	return "redirect:index.html";
    }
	
}
