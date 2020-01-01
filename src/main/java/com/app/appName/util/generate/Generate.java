package com.app.appName.util.generate;

import javax.swing.*;
import java.io.*;

/**
 * 文件生成
 * @author my
 *
 */
public class Generate {
	
	/**
	 * 文件存在时跳过
	 */
	private static final int SKIP_IF_EXIST = 1;
	/**
	 * 文件存在时强制替换
	 */
	private static final int REPLACE_IF_EXIST = 2;
	/**
	 * 文件存在时确认
	 */
	private static final int CONFIRM_IF_EXIST = 3;
	
	private static final int DEFAULT_IF_EXIST = REPLACE_IF_EXIST;
	
	static boolean createFile(String code,String location,String fileName){
		if(location!=null){
			File folder = new File(location);
			if(!folder.exists()){
				folder.mkdirs();
			}
		}
		File file = new File(location+"/"+fileName+".java");
		if(file.exists()){
			switch (DEFAULT_IF_EXIST) {
			case REPLACE_IF_EXIST:
				System.out.println(fileName+".java"+" exist --> replace");
				break;
			case SKIP_IF_EXIST:
				System.out.println(fileName+".java"+" exist --> skip");
				return false;
			case CONFIRM_IF_EXIST:
				System.out.println(fileName+".java"+" exist --> confirm");
				int i = JOptionPane.showConfirmDialog(null, fileName+".java 已经存在 是否覆盖", "请确认", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				switch (i) {
				case JOptionPane.NO_OPTION:
					System.out.println(fileName+".java"+" confirm --> skip");
					return false;
				case JOptionPane.CANCEL_OPTION:
					System.out.println(fileName+".java"+" confirm --> exit");
					System.exit(0);
				case JOptionPane.YES_OPTION:
					System.out.println(fileName+".java"+" confirm --> replace");
					break;
				}
			}
		}
		BufferedReader br = null; 
		BufferedWriter bw = null; 
		try { 
			br = new BufferedReader(new StringReader(code));
			bw = new BufferedWriter(new FileWriter(file));
			char buf[] = new char[1024 * 64];
			int len;
			while ((len = br.read(buf)) != -1) {
				bw.write(buf, 0, len);
			}
			bw.flush();
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("generate "+file.getPath());
		return true;
	}

}
