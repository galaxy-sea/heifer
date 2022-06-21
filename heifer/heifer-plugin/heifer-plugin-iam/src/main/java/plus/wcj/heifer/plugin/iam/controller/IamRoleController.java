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
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.iam.entity.IamRole;
import plus.wcj.heifer.plugin.iam.service.IamRoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/iam-role")
@RequiredArgsConstructor
public class IamRoleController {
    private final IamRoleService iamRoleService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public IamRole getById(@NotNull Long id) {
        return iamRoleService.getById(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<IamRole> page(Page<IamRole> page, IamRole iamRole, Tenant tenant) {
        return iamRoleService.page(page, iamRole);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) IamRole iamRole, Tenant tenant) {
        return iamRoleService.save(iamRole);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) IamRole iamRole) {
        return iamRoleService.update(iamRole);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return iamRoleService.removeById(id);
    }

    // TODO: 2022-04-23 weichangjin (魏昌进) 补充其他接口
}
