package com.jf.cg;

import java.util.List;
import java.util.Map;

import com.jf.cg.generator.ControllerGenerator;
import com.jf.cg.generator.DaoGenerator;
import com.jf.cg.generator.JspGenerator;
import com.jf.cg.generator.JunitGenerator;
import com.jf.cg.generator.MapperGenerator;
import com.jf.cg.generator.ModelGenerator;
import com.jf.cg.generator.ProjectGenerator;
import com.jf.cg.generator.ServiceGenerator;
import com.jf.cg.util.DBUtils;
import com.jf.cg.util.FileUtils;
import com.jf.cg.util.PropertiesUtils;

public class Main {

	public static void main(String[] args) {
		FileUtils.createPackageDirectory();
		String primaryKey = null;
		List<String> tableList = null;
		try {
			tableList = PropertiesUtils.getTableList();
			if (tableList.size() == 0) {
				tableList = DBUtils.getAllTables();
			}
		} catch (Exception e) {
			System.err.println("connection exception, please check it.");
			return;
		}
		
		String project = PropertiesUtils.getProject();
		if (project != null && !"".equals(project)) {
			ProjectGenerator.generateProject(project,tableList);
			System.out.println(project + " framework has been generated.");
		}
		for (String tableName : tableList) {
			try {
				Map<String, String> pkMap = DBUtils.getPrimaryKey(tableName);
				primaryKey = pkMap.get("primaryKey");
			} catch (Exception e) {
				System.err.println(tableName + " doesn't exist or connection exception, please check it.");
				return;
			}
			if (primaryKey != null) {
				String layers = PropertiesUtils.getLayers();
				if(layers.contains("controller")){
					ControllerGenerator.generateController(tableName);
				}if(layers.contains("dao")){
					DaoGenerator.generateDao(tableName);
				}if(layers.contains("mapper")){
					MapperGenerator.generateMapper(tableName);
				}if(layers.contains("service")){
					ServiceGenerator.generateServiceAndImpl(tableName);
				}if(layers.contains("model")){
					ModelGenerator.generateModel(tableName);
				}if(layers.contains("jsp")){
					JspGenerator.generateJsp(tableName);
				}if(layers.contains("test")){
					JunitGenerator.generateJunit(tableName);
				}
				System.out.println(tableName + " has been generated.");
			} else {
				System.err.println(tableName + " has no pk, ignored.");
			}
		}
		System.out.println("All finished.");
	}
}
