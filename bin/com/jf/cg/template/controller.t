package ${packageName}.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ${packageName}.constant.Const;
import ${packageName}.model.${ClassName};
import ${packageName}.service.${ClassName}Service;

@RequestMapping(value="/${className}")
@Controller
public class ${ClassName}Controller {
	
	private Logger log = Logger.getLogger(${ClassName}Controller.class);
	
	@Autowired
	private ${ClassName}Service ${className}Service;
	
	
	@RequestMapping
    public String list(HttpServletRequest request){
		try {
			List<${ClassName}> list = ${className}Service.get${ClassName}List();
			request.setAttribute("list", list);
		} catch (Exception e) {
			log.error(e);
		}
        return "${className}";
    }
	
	@RequestMapping(value="/get")
	@ResponseBody
    public Map<String,Object> get(@RequestParam("${primaryKey}") ${primaryKeyType} ${primaryKey}){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			${ClassName} ${className} = ${className}Service.get${ClassName}ByPrimaryKey(${primaryKey});

            map.put("${className}", ${className});
			map.put(Const.STATUS, Const.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			map.put(Const.STATUS, Const.FAILURE);
			map.put(Const.ERROR_MESSAGE, Const.GET_EXCEPTION);
		}
        return map;
    }
    
    @RequestMapping(value="/create")
	@ResponseBody
    public Map<String,Object> create(@ModelAttribute ${ClassName} ${className}){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			${className}Service.create${ClassName}(${className});
			map.put("${className}", ${className});
			map.put(Const.STATUS, Const.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			map.put(Const.STATUS, Const.FAILURE);
			map.put(Const.ERROR_MESSAGE, Const.CREATE_EXCEPTION);
		}
        return map;
    }
    @RequestMapping(value="/update")
	@ResponseBody
    public Map<String,Object> update(@ModelAttribute ${ClassName} ${className}){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			${ClassName} old${ClassName} = ${className}Service.get${ClassName}ByPrimaryKey(${className}.get${PrimaryKey}());
${updateAssignValue}
			${className}Service.update${ClassName}(old${ClassName});
			map.put("${className}", ${className});
			map.put(Const.STATUS, Const.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			map.put(Const.STATUS, Const.FAILURE);
			map.put(Const.ERROR_MESSAGE, Const.UPDATE_EXCEPTION);
		}
        return map;
    }
    @RequestMapping(value="/delete")
	@ResponseBody
    public Map<String,Object> delete(@RequestParam("deleteId") ${primaryKeyType} ${primaryKey}){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			${className}Service.delete${ClassName}(${primaryKey});
			map.put(Const.STATUS, Const.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			map.put(Const.STATUS, Const.FAILURE);
			map.put(Const.ERROR_MESSAGE, Const.DELETE_EXCEPTION);
		}
        return map;
    }
}