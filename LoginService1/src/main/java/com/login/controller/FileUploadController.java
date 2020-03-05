package com.login.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.login.pojo.RequestFileInfo;
import com.login.pojo.User;
import com.login.util.MapUtil;
import com.login.util.NettyUtil;

import io.netty.channel.Channel;
@Controller
public class FileUploadController {
	/**
     * 实现文件上传
     * */
    @RequestMapping("/fileUpload")
    @ResponseBody 
    public String fileUpload(@RequestParam("fileName") MultipartFile file,HttpSession session){
    	System.out.println("文件"+file.toString());
        if(file.isEmpty()){
            return "false";
        }
        User user = (User)session.getAttribute("user");
        String loginName = user.getLoginname();
        Channel channel = MapUtil.getchannlMap().get(loginName);
        if(loginName!=null&&channel==null) {
        	NettyUtil nettyUtil = new NettyUtil();
     		channel = nettyUtil.createChannel();
     		MapUtil.getchannlMap().put(loginName, channel);
        }
        
        try {
        	File f = new File(file.getOriginalFilename());
			file.transferTo(f);
			channel.writeAndFlush(sendFile(f));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);*/
		return "true";
    }
    
    
    public static RequestFileInfo sendFile(File file) {
		RequestFileInfo fileInfo = new RequestFileInfo();
		try {
			//该类是Java语言中功能最为丰富的文件访问类 可以跳转到文件的任意位置处读写数据
			//“r”：以只读的方式打开
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			/*FileChannel fileChannel = randomAccessFile.getChannel();
			long size = fileChannel.size();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			long l = size / 4;//10
	        if (l > 0) {
	            for (int i = 1; i < 5; i++) {
	            	int bytesRead = fileChannel.read(buf);
	            	
	                randomAccessFile.seek(4*l);
	            }
	        }*/
			randomAccessFile.seek(0);
				fileInfo.setEndPos(0);
				fileInfo.setBytes(null);
				fileInfo.setFile_size(randomAccessFile.length());
				fileInfo.setFile(file);
				fileInfo.setFile_name(file.getName());
				fileInfo.setTime(1);
		} catch (FileNotFoundException e) { // =======>>>文件未找着
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
		return fileInfo;
	}
}
