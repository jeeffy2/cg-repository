package ${packageName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.model.${ClassName};
import ${packageName}.service.${ClassName}Service;

@Service
public class ${ClassName}ServiceImpl implements ${ClassName}Service {
    @Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public List<${ClassName}> get${ClassName}List(){
		return ${className}Dao.get${ClassName}List();
	}
	public ${ClassName} get${ClassName}ByPrimaryKey(${primaryKeyType} ${primaryKey}){
		return ${className}Dao.get${ClassName}ByPrimaryKey(${primaryKey});
	}
	public void create${ClassName}(${ClassName} ${className}){
		${className}Dao.create${ClassName}(${className});
	}
	public void update${ClassName}(${ClassName} ${className}){
		${className}Dao.update${ClassName}(${className});
	}
	public void delete${ClassName}(${primaryKeyType} ${primaryKey}){
		${className}Dao.delete${ClassName}(${primaryKey});
	}

}
