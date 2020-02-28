package com.login.util;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class MapUtil {
	private static ConcurrentHashMap <String,Channel> channlMap = new ConcurrentHashMap<String,Channel>();
	/**
	 * 一个客户对应一个Map
	 * 一个Map对应一个渠道
	 * @return channlMap
	 */
	public static ConcurrentHashMap<String,Channel> getchannlMap() {
		return channlMap;
	}
}
