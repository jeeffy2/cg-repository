package com.jf.cg.generator;

import java.util.List;

import com.jf.cg.util.FileUtils;
import com.jf.cg.util.PropertiesUtils;


public class ProjectGenerator extends BaseGenerator{

	public static void generateProject(String project,List<String> tableList) {
		try {
			FileUtils.unZip(PropertiesUtils.getLocation());
			//generate constant
			ConstGenerator.generateConst();
			//generate config files in Web-Inf
			ConfigGenerator.generateConfig(tableList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
