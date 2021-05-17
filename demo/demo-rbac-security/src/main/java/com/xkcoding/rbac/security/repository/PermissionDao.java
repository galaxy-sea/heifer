package com.xkcoding.rbac.security.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xkcoding.rbac.security.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限 DAO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 16:21
 */
public interface PermissionDao extends BaseMapper<Permission> {

    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     *
     * @return 权限列表
     */

    List<Permission> selectByRoleIdList(@Param("ids") List<Long> ids);
}
