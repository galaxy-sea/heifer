package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}, ${cfg.idType}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}, ${cfg.idType}> implements ${table.serviceName} {

 <#list table.fields as field>
  <#if field.name?ends_with("_id")>
    @Override
    public List<${entity}> listBy${field.propertyName?cap_first}(${field.propertyType} ${field.propertyName}){
        return super.getBaseMapper().selectBy${field.propertyName?cap_first}(${field.propertyName});
   }

  </#if>
 </#list>
}
</#if>
