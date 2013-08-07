package ${packageName}.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.model.${ClassName};


public class ${ClassName}DaoTest extends BaseJunit{
    
	
	@Autowired
	${ClassName}Dao ${className}Dao;
	
	@Test
	public void testGet${ClassName}List(){
    	List<${ClassName}> list = ${className}Dao.get${ClassName}List();
		Assert.assertTrue(list.size()>0);
	}
	
    @Test
	public void testCreate${ClassName}(){
    	${ClassName} ${className} = new ${ClassName}();
		${className}Dao.create${ClassName}(${className});
		testId = ${className}.get${PrimaryKey}();
	}
    
    @Test
    public void testGet${ClassName}ByPrimaryKey(){
    	${ClassName} ${className} = ${className}Dao.get${ClassName}ByPrimaryKey(testId);
    	Assert.assertTrue(${className}!=null);
    }

    @Test
	public void testUpdate${ClassName}(){
    	${ClassName} ${className} = ${className}Dao.get${ClassName}ByPrimaryKey(testId);
		${className}Dao.update${ClassName}(${className});
	}
    
    @Test
	public void testDelete${ClassName}(){
		${className}Dao.delete${ClassName}(testId);
	}
    
}