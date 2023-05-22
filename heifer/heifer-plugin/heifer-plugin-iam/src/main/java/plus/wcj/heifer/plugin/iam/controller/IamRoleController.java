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

import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
import plus.wcj.heifer.metadata.iam.User;
import plus.wcj.heifer.plugin.iam.entity.IamRole;
import plus.wcj.heifer.plugin.iam.service.IamRoleService;

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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@ResponseBodyResult
@RestController
@RequestMapping("/iam/iamRole")
public class IamRoleController {

    private final IamRoleService iamRoleService;

    public IamRoleController(IamRoleService iamRoleService) {
        this.iamRoleService = iamRoleService;
    }

    /** id查询 */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('iamRole:get')")
    public IamRole getById(@NotNull @PathVariable Long id) {
        return iamRoleService.getById(id);
    }

    /** 分页查询 */
    @GetMapping
    @PreAuthorize("hasAuthority('iamRole')")
    public Page<IamRole> page(Page<IamRole> page, IamRole iamRole, User user) {
        return iamRoleService.page(page, iamRole);
    }

    /** 保存 */
    @PostMapping
    @PreAuthorize("hasAuthority('iamRole:post')")
    public boolean save(@Validated(value = PostValid.class) @RequestBody IamRole iamRole, User user) {
        return iamRoleService.save(iamRole);
    }

    /** 修改 */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('iamRole:put')")
    public boolean updateById(@NotNull @PathVariable Long id, @Validated(value = PutValid.class) @RequestBody IamRole iamRole) {
        iamRole.setId(id);
        return iamRoleService.updateById(iamRole);
    }

    /** id删除 */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('iamRole:delete')")
    public boolean removeById(@NotNull @PathVariable Long id) {
        return iamRoleService.removeById(id);
    }

}
