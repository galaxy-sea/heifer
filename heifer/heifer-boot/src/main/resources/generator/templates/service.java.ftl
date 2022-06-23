package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}, Long> {
    // 自动生成外键查询  请勿修改

 <#list table.fields as field>
  <#if field.name?ends_with("_id")>
    List<${entity}> listBy${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName});

  </#if>
 </#list>
    // 自动生成外键查询  请勿修改
}
</#if>
