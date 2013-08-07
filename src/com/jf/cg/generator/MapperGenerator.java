package com.jf.cg.generator;

import java.util.List;
import java.util.Map;

import com.jf.cg.util.DBUtils;
import com.jf.cg.util.FileUtils;
import com.jf.cg.util.StringUtils;

public class MapperGenerator extends BaseGenerator{

	public static void generateMapper(String tableName) {
		String template = "mapper.t";
		List<String> list = DBUtils.getColumnNameList(tableName);
		Map<String,String> map = DBUtils.getPrimaryKey(tableName);
		String primaryKey = map.get("primaryKey");
		String primaryKeyType = map.get("primaryKeyType");
		String content = generate(template,tableName);

		content = generateResultMap(content,list);
		content = generateSelectListSql(content, tableName);
		content = generateSelectSql(content, tableName, primaryKey);
		content = generateInsertSql(content, tableName, list);
		content = generateUpdateSql(content, tableName, primaryKey,list);
		content = generateDeleteSql(content, tableName, primaryKey);
		content = generateInsertPk(content, tableName, primaryKey, primaryKeyType);
		FileUtils.write(content, FileUtils.getPackageDirectory("dao/mapper")+StringUtils.firstUpperAndNoPrefix(tableName)+"DaoMapper.xml");
	}
	private static String generateResultMap(String content,List<String> columnList){
		StringBuilder sb = new StringBuilder();
		for(String col : columnList){
			String field = StringUtils.format(col);
			sb.append("\t\t<result property=\"").append(field).append("\" column=\"").append(col).append("\"/>\n");
		}
		String newContent = content.replaceAll("[$][{]resultMap}", sb.toString());
		return newContent;
	}
	private static String generateSelectListSql(String content, String tableName){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName);
		String newContent = content.replaceAll("[$][{]selectListSql}", sb.toString());
		return newContent;
	}
	private static String generateSelectSql(String content, String tableName, String primaryKey){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE ")
		  .append(primaryKey).append(" =#{").append(StringUtils.format(primaryKey)).append("}");
		String newContent = content.replaceAll("[$][{]selectSql}", sb.toString());
		return newContent;
	}
	private static String generateInsertSql(String content, String tableName, List<String> columnList){
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(tableName).append("(\n");
		for(int i=0;i<columnList.size();i++){
			String col = columnList.get(i);
			if(i==(columnList.size()-1)){
				sb.append("\t\t\t").append(col).append("\n");
			}else{
				sb.append("\t\t\t").append(col).append(",\n");
			}
		}
		sb.append("\t\t)VALUES(\n");
		for(int i=0;i<columnList.size();i++){
			String col = columnList.get(i);
			String field = StringUtils.format(col);
			if(i==(columnList.size()-1)){
				sb.append("\t\t\t#{").append(field).append("}\n");
			}else{
				String dbType = DBUtils.getDatabaseType();
				if("createTime".equals(field)||"updateTime".equals(field)){
					if("mysql".equals(dbType)){
						sb.append("\t\t\tnow(),\n");
					}else if("oracle".equals(dbType)){
						sb.append("\t\t\tSYSDATE,\n");
					}
				}else{
					sb.append("\t\t\t#{").append(field).append("},\n");
				}
			}
		}
		sb.append("\t\t)");
		String newContent = content.replaceAll("[$][{]insertSql}", sb.toString());
		return newContent;
	}
	private static String generateUpdateSql(String content, String tableName, String primaryKey, List<String> columnList){
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(tableName).append(" SET\n");
		for(int i=0;i<columnList.size();i++){
			String col = columnList.get(i);
			String field = StringUtils.format(col);
			if(!primaryKey.equals(col)&&!col.contains("create_time")&&!col.contains("create_by")){
				if(i==(columnList.size()-1)){
					sb.append("\t\t\t").append(col).append(" = #{").append(field).append("}\n");
				}else{
					String dbType = DBUtils.getDatabaseType();
					if("createTime".equals(field)||"updateTime".equals(field)){
						if("mysql".equals(dbType)){
							sb.append("\t\t\t").append(col).append(" = now(),\n");
						}else if("oracle".equals(dbType)){
							sb.append("\t\t\t").append(col).append(" = SYSDATE,\n");
						}
					}else{
						sb.append("\t\t\t").append(col).append(" = #{").append(field).append("},\n");
					}
				}
			}
			
		}
		sb.append("\t\tWHERE ").append(primaryKey).append(" = #{").append(StringUtils.format(primaryKey)).append("}\n");
		String newContent = content.replaceAll("[$][{]updateSql}", sb.toString());
		return newContent;
	}
	private static String generateDeleteSql(String content, String tableName, String primaryKey){
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM  ").append(tableName).append(" WHERE ")
		  .append(primaryKey).append(" =#{").append(StringUtils.format(primaryKey)).append("}");
		String newContent = content.replaceAll("[$][{]deleteSql}", sb.toString());
		return newContent;
	}
	private static String generateInsertPk(String content, String tableName, String primaryKey, String primaryKeyType){
		StringBuilder sb = new StringBuilder();
		String dbType = DBUtils.getDatabaseType();
		if("mysql".equals(dbType)){
			sb.append("<selectKey resultType=\"").append(primaryKeyType).append("\"  order=\"AFTER\" keyProperty=\"").append(StringUtils.format(primaryKey)).append("\" >\n");
			sb.append("\t\t\tSELECT LAST_INSERT_ID()\n");
			sb.append("\t\t</selectKey>");
		}else if("oracle".equals(dbType)){
			sb.append("<selectKey resultType=\"").append(primaryKeyType).append("\"  order=\"BEFORE\" keyProperty=\"").append(StringUtils.format(primaryKey)).append("\" >\n");
			sb.append("\t\t\tSELECT S_").append(tableName).append(".NEXTVAL FROM DUAL\n");
			sb.append("\t\t</selectKey>");
		}
		String newContent = content.replaceAll("[$][{]insertPk}", sb.toString());
		return newContent;
	}
}
