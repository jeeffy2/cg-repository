<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="${packageName}.dao.${ClassName}Dao">
    <resultMap type="${packageName}.model.${ClassName}" id="${className}Map">
${resultMap}
    </resultMap>
    
    <select id="get${ClassName}List" resultMap="${className}Map">
    	${selectListSql}
    </select>
    <select id="get${ClassName}ByPrimaryKey" parameterType="${primaryKeyType}" resultMap="${className}Map">
    	${selectSql}
    </select>
    
    <insert id="create${ClassName}" parameterType="${packageName}.model.${ClassName}">
        ${insertPk}
		${insertSql}
    </insert>
    
    <update id="update${ClassName}" parameterType="${packageName}.model.${ClassName}">
		${updateSql}
    </update>
    
    <delete id="delete${ClassName}" parameterType="${primaryKeyType}">
    	${deleteSql}
    </delete>
</mapper>