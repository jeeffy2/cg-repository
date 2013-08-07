package com.jf.cg.generator;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.jf.cg.util.DBUtils;
import com.jf.cg.util.FileUtils;
import com.jf.cg.util.PropertiesUtils;
import com.jf.cg.util.StringUtils;


public class ConfigGenerator extends BaseGenerator{

	public static void generateConfig(List<String> tableList){
		generateMybatisConfig(tableList);
		generateSpringConfig();
		generateSpringTestConfig();
		generateSpringMVCConfig();
		generateJDBCConfig();
	}
	private static void generateMybatisConfig(List<String> tableList) {
		StringBuilder sb = new StringBuilder();
		String template = "config/mybatis-config.t";
		String content = FileUtils.getTemplate(template);
		String mapperFilePath = PropertiesUtils.getPackage().replaceAll("[.]", "/")+"/dao/mapper/";
		for(String tableName : tableList){
			Map<String, String> pkMap = DBUtils.getPrimaryKey(tableName);
			String primaryKey = pkMap.get("primaryKey");
			if(primaryKey!=null){
				sb.append("\t\t<mapper resource=\"").append(mapperFilePath).append(StringUtils.firstUpperAndNoPrefix(tableName))
				.append("DaoMapper.xml\"/>\n");
			}
		}
		content = content.replaceAll("[$][{]mapperResource}", sb.toString());
		generateFile(content,template);
		
	}
	private static void generateSpringConfig() {
		String template = "config/spring-config.t";
		String content = FileUtils.getTemplate(template);
		content = content.replaceAll("[$][{]package}", PropertiesUtils.getPackage());
		generateFile(content,template);
	}
	private static void generateSpringTestConfig() {
		String template = "config/spring-test-config.t";
		String content = FileUtils.getTemplate(template);
		content = content.replaceAll("[$][{]package}", PropertiesUtils.getPackage());
		generateFile(content,template);
	}
	private static void generateSpringMVCConfig() {
		String template = "config/springmvc-servlet.t";
		String content = FileUtils.getTemplate(template);
		content = content.replaceAll("[$][{]package}", PropertiesUtils.getPackage());
		generateFile(content,template);
	}
	private static void generateJDBCConfig(){
		File file = new File("jdbc.properties");
		String content = FileUtils.read(file);
		FileUtils.write(content, PropertiesUtils.getLocation()+"/WebContent/WEB-INF/jdbc.properties");
	}
	private static void generateFile(String content, String template){
		String name = template.substring(template.indexOf("/")+1,template.indexOf("."));
		FileUtils.write(content, PropertiesUtils.getLocation()+"/WebContent/WEB-INF/" + name + ".xml");
	}
}
