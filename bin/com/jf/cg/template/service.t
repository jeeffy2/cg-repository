package ${packageName}.service;

import java.util.List;
import ${packageName}.model.${ClassName};

public interface ${ClassName}Service {

	public List<${ClassName}> get${ClassName}List();
	public ${ClassName} get${ClassName}ByPrimaryKey(${primaryKeyType} ${primaryKey});
	public void create${ClassName}(${ClassName} ${className});
	public void update${ClassName}(${ClassName} ${className});
	public void delete${ClassName}(${primaryKeyType} ${primaryKey});
}