package com.jf.cg.generator;

import com.jf.cg.util.FileUtils;
import com.jf.cg.util.StringUtils;

public class ServiceGenerator extends BaseGenerator{

	public static void generateServiceAndImpl(String tableName){
		generateService(tableName);
		generateServiceImpl(tableName);
	}
	private static void generateService(String tableName) {
		String template = "service.t";
		String content = generate(template,tableName);
		FileUtils.write(content, FileUtils.getPackageDirectory("service")+StringUtils.firstUpperAndNoPrefix(tableName)+"Service.java");
	}
	private static void generateServiceImpl(String tableName) {
		String template = "service-impl.t";
		String content = generate(template,tableName);
		FileUtils.write(content, FileUtils.getPackageDirectory("service/impl")+StringUtils.firstUpperAndNoPrefix(tableName)+"ServiceImpl.java");
	}
}
