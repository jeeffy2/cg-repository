package com.jf.cg.generator;

import com.jf.cg.util.FileUtils;
import com.jf.cg.util.PropertiesUtils;
import com.jf.cg.util.StringUtils;


public class JunitGenerator extends BaseGenerator{

	public static void generateJunit(String tableName) {
		generateBaseJunit(tableName);
		generateDaoTest(tableName);
	}
	private static void generateDaoTest(String tableName) {
		String template = "dao-test.t";
		String content = generate(template,tableName);
		FileUtils.write(content, FileUtils.getPackageDirectory("test")+StringUtils.firstUpperAndNoPrefix(tableName)+"DaoTest.java");
	}
	private static void generateBaseJunit(String tableName) {
		String template = "base-junit.t";
		String content = generate(template,tableName);
		String project = PropertiesUtils.getProject();
		if(project!=null && !"".equals(project)){
			FileUtils.write(content, FileUtils.getPackageDirectory("test")+"BaseJunit.java");
		}
		
	}
}
