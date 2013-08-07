package com.jf.cg.generator;

import com.jf.cg.util.FileUtils;
import com.jf.cg.util.StringUtils;

public class DaoGenerator extends BaseGenerator{

	public static void generateDao(String tableName) {
		String template = "dao.t";
		String content = generate(template,tableName);
		FileUtils.write(content, FileUtils.getPackageDirectory("dao")+StringUtils.firstUpperAndNoPrefix(tableName)+"Dao.java");
	}
}
