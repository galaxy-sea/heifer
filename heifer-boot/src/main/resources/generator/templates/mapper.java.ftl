package ${package.Mapper};

import org.apache.ibatis.annotations.Param;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};

import java.util.List;

/**
* <p>
 * ${table.comment!} Mapper 接口
 * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
 interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
 public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

 <#list table.fields as field>
  <#if field.name?ends_with("_id")>
    List<${entity}> selectBy${field.propertyName?cap_first}(@Param("${field.propertyName}") ${field.propertyType} ${field.propertyName});

  </#if>
 </#list>
 }
</#if>
