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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.common.mybatisplus.validation.OrderByValid;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
import plus.wcj.heifer.metadata.iam.User;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/12
 */
@RestController
@RequestMapping("page")
@Tag(name = "data")
public class PageAnnController {

    @GetMapping("page")
    @ResponseBodyResult
    public Page<User> page(Page<User> page) {
        return page;
    }

    @GetMapping("ann")
    @ResponseBodyResult
    public Page<User> ann(@OrderByValid(field = "ann") Page<User> page) {
        return page;
    }


    @GetMapping("security")
    @ResponseBodyResult
//    @PreAuthorize("hasAuthority('admin123123')")
    public Page<User> security(@OrderByValid(field = "ann") Page<User> page) {
        return page;
    }

    @GetMapping("all")
    @ResponseBodyResult
//    @PreAuthorize("hasAuthority('admin123123')")
//    @PostFilter("hasAuthority('admin123123')")
//    @PostAuthorize("hasAuthority('admin123123')")
//    @PreFilter("hasAuthority('admin123123')")
    public Page<User> all(Page<User> page) {
        return page;
    }

}
