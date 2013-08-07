package com.jf.cg.generator;

import com.jf.cg.util.FileUtils;
import com.jf.cg.util.PropertiesUtils;


public class ConstGenerator extends BaseGenerator{

	public static void generateConst() {
		String template = "const.t";
		String content = FileUtils.getTemplate(template);
		content = content.replaceAll("[$][{]package}", PropertiesUtils.getPackage());
		FileUtils.write(content, FileUtils.getPackageDirectory("constant")+"Const.java");
	}

}
