package com.stone.shop.utils.payorg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.stone.shop.utils.logger.FuwuLogger;

public class TvPayUtils {

	private static Properties properties = new Properties();

	static {
		String tmpPath = TvPayUtils.class.getResource("/").getPath();
		FuwuLogger.info("-------------path="+tmpPath, TvPayUtils.class);
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(tmpPath
					+ "config/tvpay.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null)
					inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getUserNo() {
		return properties.getProperty("userNo");
	}

	public static String getStbId() {
		return properties.getProperty("stbId");
	}

	public static String getUserName() {
		return properties.getProperty("username");
	}

	public static String getPassword() {
		return properties.getProperty("password");
	}

	public static String getTradeappcode() {
		return properties.getProperty("tradeappcode");
	}
	
	public static String getSendPayHost() {
		return properties.getProperty("sendPayHost");
	}

	public static String getSendPayUrl() {
		return getSendPayHost()+properties.getProperty("sendPayUrl");
	}

	public static String getPayReturnUrl() {
		String host = properties.getProperty("payReturnHost");
		return host + properties.getProperty("payReturnUrl");
	}

	public static String getMd5Rs() {
		return properties.getProperty("md5Rs");
	}

	public static String getFtpServer() {
		return properties.getProperty("ftpServer");
	}

	public static String getFtpPort() {
		return properties.getProperty("ftpPort");
	}

	public static String getFtpuser() {
		return properties.getProperty("ftpuser");
	}

	public static String getFtppassword() {
		return properties.getProperty("ftppassword");
	}

}
