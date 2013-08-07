package com.jf.cg.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class PropertiesUtils {
	private static Properties prop;
	
	static {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("generator.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getPackage(){
		String cgpackage = prop.getProperty("package");
		if(cgpackage!=null){
			cgpackage = cgpackage.trim();
		}
		return cgpackage;
	}
	public static String getLocation(){
		String location = prop.getProperty("location");
		if(location!=null){
			location = location.trim();
		}
		String project = getProject();
		if(project!=null && !"".equals(project)){
			location = location + "/" + project;
		}
		return location;
	}
	public static String getProject(){
		String project = prop.getProperty("project");
		if(project!=null){
			project = project.trim();
		}
		return project;
	}
	public static List<String> getTableList(){
		List<String> list = new ArrayList<String>();;
		try {
			String tables = prop.getProperty("tables");
			String[] tableArr = tables.split(",");
			for(String str : tableArr){
				str = str.trim();
				if(!"".equals(str)){
					list.add(str);
				}
			}
		} catch (Exception e) {
			
		}
		return list;
	}
	public static String getTablePrefix(){
		String tablePrefix = prop.getProperty("tablePrefix");
		if(tablePrefix==null){
			tablePrefix = "";
		}
		return tablePrefix.toLowerCase().trim();
	}
	public static String getPrecision(){
		String precision = prop.getProperty("precision");
		if(precision==null){
			precision = "";
		}
		return precision.toLowerCase().trim();
	}
	public static String getLayers(){
		String layers = prop.getProperty("layers");
		if(layers==null||"".equals(layers)){
			layers = "dao,mapper,service,controller,model,jsp,test";
		}
		return layers.toLowerCase().trim();
	}
}
