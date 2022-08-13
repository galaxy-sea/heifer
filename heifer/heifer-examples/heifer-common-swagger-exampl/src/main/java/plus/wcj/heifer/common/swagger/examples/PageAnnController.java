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

package plus.wcj.heifer.common.swagger.examples;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.common.mybatisplus.validation.OrderByValid;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;

import io.swagger.annotations.ApiOperation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/12
 */
@RestController
@RequestMapping("page")
@ApiOperation("data")
public class PageAnnController {

    @GetMapping("page")
    @ResultResponseBody
    public Page<Tenant> page(Page<Tenant> page) {
        return page;
    }

    @GetMapping("ann")
    @ResultResponseBody
    public Page<Tenant> ann(@OrderByValid(field = "ann") Page<Tenant> page) {
        return page;
    }


    @GetMapping("security")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('admin123123')")
    public Page<Tenant> security(@OrderByValid(field = "ann") Page<Tenant> page) {
        return page;
    }

    @GetMapping("all")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('admin123123')")
    public Page<Tenant> all(Page<Tenant> page) {
        return page;
    }

}
