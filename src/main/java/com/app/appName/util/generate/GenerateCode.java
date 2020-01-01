package com.app.appName.util.generate;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 基础代码生成
 * @author my
 *
 */
public class GenerateCode {
	
	private Class<?> clazz;
	private String packageName;
	
	public static void main(String[] args) throws Exception {
		GenerateCode.run("com.app.appName.entity");
	}
	
	/**
	 * @param root 同工程下类包名 (如 com.app.appName)
	 */
	public static void run(String root){
		double start = System.nanoTime();
		root = root.replace(".", "/");
		File folder = new File("src/main/java/"+root);
		root = root.replace("/", ".");
		File[] files = folder.listFiles();
		if(files!=null)
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			String fileName = file.getName();
			if(fileName.endsWith(".java")){
				String className = fileName.substring(0,fileName.indexOf(".java"));
				try {
					Class<?> c = Class.forName(root+"."+className);
					GenerateCode.run(c);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		double time = (System.nanoTime()-start)/10000000;
		time = Math.rint(time*100)/100;
		System.out.println("generate complate (use time : "+time+" ms)");
	}
	
	/**
	 * @param bean 类
	 */
	public static void run(Class<?> bean){
		GenerateCode generate = new GenerateCode(bean);
		generate.createAllFile();
	}
	
	private void createAllFile(){
		createDao();
		createDaoImpl();
		createService();
		createServiceImpl();
	}

	private GenerateCode(Class<?> clazz) {
		this.clazz = clazz;
		packageName = clazz.getName().replace("."+clazz.getSimpleName(), "");
		packageName = packageName.substring(0,packageName.lastIndexOf("."));
	}

	private boolean createService(){
		Entity entity = new Entity(clazz);
		String code = process(entity,"Service");
		String location = "src/main/java/"+code.substring(code.indexOf(' ')+1,code.indexOf(';')).replace('.', '/');
		String fileName = clazz.getSimpleName().replace("Entity","")+"Service";
		return createFile(code,location,fileName);
	}
	private boolean createServiceImpl(){
		Entity entity = new Entity(clazz);
		String code = process(entity,"ServiceImpl");
		String location = "src/main/java/"+code.substring(code.indexOf(' ')+1,code.indexOf(';')).replace('.', '/');
		String fileName = clazz.getSimpleName().replace("Entity","")+"ServiceImpl";
		return createFile(code,location,fileName);
	}
	private boolean createDao(){
		Entity entity = new Entity(clazz);
		String code = process(entity,"Dao");
		String location = "src/main/java/"+code.substring(code.indexOf(' ')+1,code.indexOf(';')).replace('.', '/');
		String fileName = clazz.getSimpleName().replace("Entity","")+"Dao";
		return createFile(code,location,fileName);
	}

	private boolean createDaoImpl(){
		Entity entity = new Entity(clazz);
		String code = process(entity,"DaoImpl");
		String location = "src/main/java/"+code.substring(code.indexOf(' ')+1,code.indexOf(';')).replace('.', '/');
		String fileName = clazz.getSimpleName().replace("Entity","")+"DaoImpl";
		return createFile(code,location,fileName);
	}

	private String process(Entity entity,String template){
		Configuration cfg = null;
		try {
			cfg = new Configuration();
		} catch (Exception e) {
		}
		try {
			String path = GenerateCode.class.getName().substring(0,getClass().getName().lastIndexOf(".")).replace(".", "/");
			String dir = GenerateCode.class.getClassLoader().getResource("").getFile();
			dir = dir.substring(0,dir.lastIndexOf("/"));
			dir = dir.substring(0,dir.lastIndexOf("/"));
			//dir += "/src/"+path;
			dir += "/classes/jt";
			File directory = new File(dir);
			cfg.setDirectoryForTemplateLoading(directory);
			Template t = cfg.getTemplate(template+".jt");
			Writer out = new StringWriter();
			t.process(entity, out);
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	private boolean createFile(String code,String location,String fileName){
		return Generate.createFile(code, location, fileName);
	}
}