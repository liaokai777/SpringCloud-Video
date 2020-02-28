package com.login.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VideoController {
	@GetMapping(value = "/getVideo")
	public String getVideos(HttpServletRequest request, HttpServletResponse response)
	{
		String filename=(String) request.getParameter("filename");
		System.out.println("读取视频数据"+filename);
		if(filename==null) return null;
		
		
		
		
	    try {
	        FileInputStream fis = null;
	        OutputStream os = null ;
	        fis = new FileInputStream(filename);
	        int size = fis.available(); // 得到文件大小
	        byte data[] = new byte[size];
	        fis.read(data); // 读数据
	        fis.close();
	        fis = null;
	        response.setContentType("video/mp4"); // 设置返回的文件类型
	        os = response.getOutputStream();
	        os.write(data);
	        os.flush();
	        os.close();
	        os = null;
	 
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
