package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}, ${cfg.idType}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}, ${cfg.idType}> {

 <#list table.fields as field>
  <#if field.name?ends_with("_id")>
    List<${entity}> listBy${field.propertyName?cap_first}(@Param("${field.propertyName}") ${field.propertyType} ${field.propertyName});

  </#if>
 </#list>
}
</#if>
