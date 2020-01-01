package com.app.appName.util.generate;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板实体类
 * @author boruonuo
 *
 */
public class Entity {

	private Class<?> clazz;
	private String className;
	private String classSmallName;
	private String variableName;
	private String packageName;
	private String parentPackage;
	private String modulename;
	private String moduleName;

	private List<String> importPackages;

	public Entity(Class<?> clazz) {
		this.clazz = clazz;
		init();
	}

	private void init() {
		className = clazz.getSimpleName().replace("Entity","");
		classSmallName = className;

		char[]  chars=new char[1];
		chars[0]=classSmallName.charAt(0);
		String temp=new String(chars);
		if(chars[0]>='A' && chars[0]<='Z') classSmallName = classSmallName.replaceFirst(temp,temp.toLowerCase());

		variableName = className.substring(0, 1).toLowerCase() + className.substring(1);
		packageName = clazz.getName().replaceAll("." + className, "");
		parentPackage = packageName.substring(0, packageName.lastIndexOf("."));
		importPackages = new ArrayList<String>();
		importPackages.add(clazz.getName());
		modulename = clazz.getName().replace("com.app.", "");
		modulename = modulename.substring(0,modulename.indexOf("."));
		moduleName = modulename.substring(0,1).toUpperCase()+modulename.substring(1);
	}
	
	public void importPackage(String importPackage){
		importPackages.add(importPackage);
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getClassName() {
		return className;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getVariableName() {
		return variableName;
	}

	public List<String> getImportPackages() {
		return importPackages;
	}

	public String getParentPackage() {
		return parentPackage;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getModulename() {
		return modulename;
	}

	public String getClassSmallName(){
		return classSmallName;
	}

}