package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if mapperAnnotation>
import org.apache.ibatis.annotations.Mapper;
</#if>

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if mapperAnnotation>
@Mapper
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
     // 自动生成外键查询  请勿修改
 <#list table.fields as field>
  <#if field.name?ends_with("_id")>
    default List<${entity}> selectBy${field.propertyName?cap_first}(@Param("${field.propertyName}") ${field.propertyType} ${field.propertyName}){
        return this.selectList(new LambdaQueryWrapper<${entity}>().eq(RbacRole::get${field.propertyName?cap_first}, ${field.propertyName}));
    }

  </#if>
 </#list>
    // 自动生成外键查询  请勿修改
}
</#if>
