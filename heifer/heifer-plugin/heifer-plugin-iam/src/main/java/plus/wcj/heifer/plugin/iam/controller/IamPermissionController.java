/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package plus.wcj.heifer.plugin.iam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
import plus.wcj.heifer.metadata.iam.Tenant;
import plus.wcj.heifer.plugin.iam.entity.IamPermission;
import plus.wcj.heifer.plugin.iam.service.IamPermissionService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 功能权限 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@ResponseBodyResult
@RestController
@RequestMapping("/iam/iamPermission")
@RequiredArgsConstructor
public class IamPermissionController {

    private final IamPermissionService iamPermissionService;

    /** id查询 */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('iamPermission:get')")
    public IamPermission getById(@NotNull @PathVariable Long id) {
        return iamPermissionService.getById(id);
    }

    /** 分页查询 */
    @GetMapping
    @PreAuthorize("hasAuthority('iamPermission')")
    public Page<IamPermission> page(Page<IamPermission> page, IamPermission iamPermission, Tenant tenant) {
        return iamPermissionService.page(page, iamPermission);
    }

    /** 保存 */
    @PostMapping
    @PreAuthorize("hasAuthority('iamPermission:post')")
    public boolean save(@Validated(value = PostValid.class) @RequestBody IamPermission iamPermission, Tenant tenant) {
        return iamPermissionService.save(iamPermission);
    }

    /** 修改 */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('iamPermission:put')")
    public boolean updateById(@NotNull @PathVariable Long id, @Validated(value = PutValid.class) @RequestBody IamPermission iamPermission) {
        iamPermission.setId(id);
        return iamPermissionService.updateById(iamPermission);
    }

    /** id删除 */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('iamPermission:delete')")
    public boolean removeById(@NotNull @PathVariable Long id) {
        return iamPermissionService.removeById(id);
    }

}
