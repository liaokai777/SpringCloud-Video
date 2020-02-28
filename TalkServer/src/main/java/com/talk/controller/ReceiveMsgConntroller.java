package com.talk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//相当于@ResponseBody ＋ @Controller合在一起的作用
public class ReceiveMsgConntroller {
	@RequestMapping("/sendmsg")
	public void sendMsg(){
		
	}
}
