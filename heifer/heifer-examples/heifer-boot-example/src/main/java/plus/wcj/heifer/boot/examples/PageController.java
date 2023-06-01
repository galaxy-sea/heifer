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

package plus.wcj.heifer.boot.examples;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.common.mybatisplus.validation.OrderByValid;
import plus.wck.heifer.api.User;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/10
 */
@RestController
@RequestMapping("page")
@Validated
public class PageController {

    @GetMapping
    public IPage pageDTO(IPage pageDTO, HttpServletRequest request, @RequestParam(value = "test", required = false) List<Long> test) {
        return pageDTO;
    }


    @GetMapping("valid")
    public IPage<User> pageDTO(@OrderByValid Page<User> pageDTO) {
        return pageDTO;
    }
}
