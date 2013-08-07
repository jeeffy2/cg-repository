package com.jf.cg.generator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.jf.cg.util.DBUtils;
import com.jf.cg.util.FileUtils;
import com.jf.cg.util.PropertiesUtils;
import com.jf.cg.util.StringUtils;


public class ModelGenerator extends BaseGenerator{

	public static void generateModel(String tableName) {
		Map<String, String> colMap = DBUtils.getColumnNameTypeMap(tableName);
		String ClassName = StringUtils.firstUpperAndNoPrefix(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("package "+PropertiesUtils.getPackage()+".model;\n\n");
		
		Set<String> keySet = colMap.keySet();
		Set<String> set = new HashSet<String>();
		for(String key : keySet){
			String value = colMap.get(key);
			if("BigDecimal".equals(value)&&!set.contains("BigDecimal")){
				sb.append("import java.math.BigDecimal;\n");
				set.add("BigDecimal");
			}else if("Date".equals(value)&&!set.contains("Date")){
				sb.append("import java.util.Date;\n");
				set.add("Date");
			}else if("Timestamp".equals(value)&&!set.contains("Timestamp")){
				sb.append("import java.sql.Timestamp;\n");
				set.add("Timestamp");
			}
		}
		
		sb.append("\npublic class "+ClassName+"{\n");
		sb.append(generateFields(colMap));
		sb.append(generateGetAndSetMethods(colMap));
		sb.append("}");
		FileUtils.write(sb.toString(), FileUtils.getPackageDirectory("model")+StringUtils.firstUpperAndNoPrefix(tableName)+".java");
	}
	private static String generateFields(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder();
		for(String key : keySet){
			String value = map.get(key);
			sb.append("\tprivate ").append(value+" ").append(StringUtils.format(key)+";\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	private static String generateGetAndSetMethods(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		StringBuilder sb = new StringBuilder();
		for(String key : keySet){
			String field = StringUtils.format(key);
			String fieldType = map.get(key);
			//generate get method
			sb.append("\tpublic ").append(fieldType+" ").append("get"+StringUtils.firstUpperNoFormat(field)+"() {\n\t\t")
			  .append("return "+field+";\n\t}\n");
			//generate set method
			sb.append("\tpublic ").append("void ").append("set"+StringUtils.firstUpperNoFormat(field)+"("+fieldType+" "+field+") {\n\t\t")
			  .append("this."+field+" = "+field+";\n\t}\n");
		}
		return sb.toString();
	}
}
