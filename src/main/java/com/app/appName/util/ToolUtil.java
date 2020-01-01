package com.app.appName.util;

import java.io.*;

public class ToolUtil {
	public static long getFileTime(String filePath) {
		long l = 0;

		File file = new File("/"+filePath);
		if (file != null)
			l = file.lastModified();
		return l;
	}

	/**
	 * 生成四位数的随机码
	 * @return
	 */
	public static  String getRandCode(int length){
		String s = "1";
		for (int j = 0; j < length - 1; j++) {
			s += "0";
		}
		int i = Integer.parseInt(s);
		int code = (int)(Math.random() * 9 * i + i);
		return code + "";
	}
}
