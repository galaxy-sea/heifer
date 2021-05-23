package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

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
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
 <#if table.name?matches(".*_rel$")>
interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
 <#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
 </#if>


}
</#if>
