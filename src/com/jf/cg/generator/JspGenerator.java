package com.jf.cg.generator;

import java.util.List;
import java.util.Map;

import com.jf.cg.util.DBUtils;
import com.jf.cg.util.FileUtils;
import com.jf.cg.util.PropertiesUtils;
import com.jf.cg.util.StringUtils;


public class JspGenerator extends BaseGenerator{

	public static void generateJsp(String tableName) {
		String template = "jsp.t";
		DBUtils.getAllTables();
		Map<String,String> map = DBUtils.getPrimaryKey(tableName);
		List<String> colList = DBUtils.getFormatedColumnNameList(tableName);
		String primaryKey = map.get("primaryKey");
		String content = generate(template,tableName);
		content = generateForm(content,primaryKey,colList);
		content = generateHead(content,primaryKey,colList);
		content = generateBody(content,primaryKey,colList);
		String project = PropertiesUtils.getProject();
		String jspDir = null;
		if(project!=null && !"".equals(project)){
			jspDir = PropertiesUtils.getLocation()+"/WebContent/WEB-INF/jsp/";
		}else{
			jspDir = PropertiesUtils.getLocation()+"/jsp/";
		}
		FileUtils.write(content, jspDir + StringUtils.formatAndNoPrefix(tableName)+".jsp");
	}
	private static String generateForm(String content, String primaryKey, List<String> colList){
		StringBuilder sb = new StringBuilder();
		for(String str: colList){
			if(!StringUtils.format(primaryKey).equals(str)&&!"createTime".equals(str)&&!"createBy".equals(str)&&!"updateTime".equals(str)&&!"updateBy".equals(str)){
				sb.append("\t\t\t\t<div class=\"control-group\">\n");
				sb.append("\t\t\t\t\t<label class=\"control-label\" for=\"").append(str).append("\">").append(str).append("</label>\n");
				sb.append("\t\t\t\t\t<div class=\"controls\">\n");
				sb.append("\t\t\t\t\t\t<input type=\"text\" id=\"").append(str).append("\" name=\"").append(str).append("\"/>\n");
				sb.append("\t\t\t\t\t</div>\n");
				sb.append("\t\t\t\t</div>\n");
			}
		}
		String newContent = content.replaceAll("[$][{]cgForm}", sb.toString());
		return newContent;
	}
	private static String generateHead(String content, String primaryKey, List<String> colList){
		StringBuilder sb = new StringBuilder();
		for(String str: colList){
			if(!StringUtils.format(primaryKey).equals(str)&&!"createTime".equals(str)&&!"createBy".equals(str)&&!"updateTime".equals(str)&&!"updateBy".equals(str)){
				sb.append("\t\t\t\t\t<th>").append(str).append("</th>\n");
			}
		}
		String newContent = content.replaceAll("[$][{]cgHead}", sb.toString());
		return newContent;
	}
	private static String generateBody(String content, String primaryKey, List<String> colList){
		StringBuilder sb = new StringBuilder();
		for(String str: colList){
			if(!StringUtils.format(primaryKey).equals(str)&&!"createTime".equals(str)&&!"createBy".equals(str)&&!"updateTime".equals(str)&&!"updateBy".equals(str)){
				sb.append("\t\t\t\t\t\t<td class=\"").append(str).append("\">\\${item.").append(str).append("}</td>\n");
			}
		}
		String newContent = content.replaceAll("[$][{]cgBody}", sb.toString());
		return newContent;
	}
}
