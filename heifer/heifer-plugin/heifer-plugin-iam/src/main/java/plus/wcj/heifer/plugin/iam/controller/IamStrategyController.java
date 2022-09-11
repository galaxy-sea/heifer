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
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.iam.entity.IamStrategy;
import plus.wcj.heifer.plugin.iam.service.IamStrategyService;

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
 * abac策略规则 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@ResponseBodyResult
@RestController
@RequestMapping("/iam/iamStrategy")
@RequiredArgsConstructor
public class IamStrategyController {

    private final IamStrategyService iamStrategyService;

    /** id查询 */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('iamStrategy:get')")
    public IamStrategy getById(@NotNull @PathVariable Long id) {
        return iamStrategyService.getById(id);
    }

    /** 分页查询 */
    @GetMapping
    @PreAuthorize("hasAuthority('iamStrategy')")
    public Page<IamStrategy> page(Page<IamStrategy> page, IamStrategy iamStrategy, Tenant tenant) {
        return iamStrategyService.page(page, iamStrategy);
    }

    /** 保存 */
    @PostMapping
    @PreAuthorize("hasAuthority('iamStrategy:post')")
    public boolean save(@Validated(value = PostValid.class) @RequestBody IamStrategy iamStrategy, Tenant tenant) {
        return iamStrategyService.save(iamStrategy);
    }

    /** 修改 */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('iamStrategy:put')")
    public boolean updateById(@NotNull @PathVariable Long id, @Validated(value = PutValid.class) @RequestBody IamStrategy iamStrategy) {
        iamStrategy.setId(id);
        return iamStrategyService.updateById(iamStrategy);
    }

    /** id删除 */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('iamStrategy:delete')")
    public boolean removeById(@NotNull @PathVariable Long id) {
        return iamStrategyService.removeById(id);
    }

}
