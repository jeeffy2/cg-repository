package ${packageName}.dao;

import java.util.List;

import ${packageName}.model.${ClassName};

public interface ${ClassName}Dao {

	public List<${ClassName}> get${ClassName}List();
	public ${ClassName} get${ClassName}ByPrimaryKey(${primaryKeyType} ${primaryKey});
	public void create${ClassName}(${ClassName} ${className});
	public void update${ClassName}(${ClassName} ${className});
	public void delete${ClassName}(${primaryKeyType} ${primaryKey});
}
