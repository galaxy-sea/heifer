package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.RequestMapping;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
import plus.wcj.heifer.metadata.tenant.Tenant;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import org.springframework.security.access.prepost.PreAuthorize;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if swagger>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@ResponseBodyResult
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@RequiredArgsConstructor
<#if swagger>
@Api(tags="${table.comment!}")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    private final ${table.serviceName} ${table.serviceName?uncap_first};

    /** id查询 */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('${table.entityPath}:get')")
    <#if swagger>
    @ApiOperation("id查询")
    </#if>
    public ${entity} getById(@NotNull @PathVariable Long id) {
        return ${table.serviceName?uncap_first}.getById(id);
    }

    /** 分页查询 */
    @GetMapping
    @PreAuthorize("hasAuthority('${table.entityPath}')")
    <#if swagger>
    @ApiOperation("分页查询")
    </#if>
    public Page<${entity}> page(Page<${entity}> page, ${entity} ${entity?uncap_first}, Tenant tenant) {
        return ${table.serviceName?uncap_first}.page(page, ${entity?uncap_first});
    }

    /** 保存 */
    @PostMapping
    @PreAuthorize("hasAuthority('${table.entityPath}:post')")
    <#if swagger>
    @ApiOperation("保存")
    </#if>
    public boolean save(@Validated(value = PostValid.class) @RequestBody ${entity} ${entity?uncap_first}, Tenant tenant) {
        return ${table.serviceName?uncap_first}.save(${entity?uncap_first});
    }

    /** 修改 */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('${table.entityPath}:put')")
    <#if swagger>
    @ApiOperation("修改")
    </#if>
    public boolean updateById(@NotNull @PathVariable Long id, @Validated(value = PutValid.class) @RequestBody ${entity} ${entity?uncap_first}) {
        ${entity?uncap_first}.setId(id);
        return ${table.serviceName?uncap_first}.updateById(${entity?uncap_first});
    }

    /** id删除 */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('${table.entityPath}:delete')")
    <#if swagger>
    @ApiOperation("id删除")
    </#if>
    public boolean removeById(@NotNull @PathVariable Long id) {
        return ${table.serviceName?uncap_first}.removeById(id);
    }

}
</#if>
