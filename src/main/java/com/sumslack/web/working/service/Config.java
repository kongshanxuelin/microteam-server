package com.sumslack.web.working.service;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {
	private static Logger logger = LogManager.getLogger(Config.class);
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("config");

	public static String Redis_Host = getProp("redis.host");
	public static String Redis_Port = getProp("redis.port");
	public static String Redis_PWD = getProp("redis.password");
	public static String Redis_NAME = getProp("redis.name");
	public static String Redis_MaxActive = getProp("redis.max_active");
	public static String Redis_IDLE;
	public static String Redis_WAIT;
	public static String Redis_Timeout;
	public static String TestOn;
	public static String LoginPageLink;
	public static String SecKey;
	public static String SecMobileKey;

	static {
		Redis_MaxActive = getProp("redis.max_idle");
		Redis_MaxActive = getProp("redis.max_wait");
		Redis_Timeout = getProp("redis.timeout");
		TestOn = getProp("redis.test_on_borrow");

		SecKey = getProp("jwt.security.key");
		LoginPageLink = getProp("url.login");
		
		SecMobileKey = getProp("base64.security.key");
	}

	public static String getProp(String key) {
		try {
			return bundle.getString(key);
		} catch (Exception ex) {
			logger.warn("can't find the prop with key:" + key);
		}
		return null;
	}
}
