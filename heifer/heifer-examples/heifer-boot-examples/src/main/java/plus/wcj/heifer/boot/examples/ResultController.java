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

import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.bean.Result;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/17
 */
@RestController
@RequestMapping
@ResultResponseBody
public class ResultController {

    @GetMapping("hello")
    public Map<String, String> hello() {
        return new LinkedHashMap<String, String>() {{
            put("hello", "hello");
            put("world", "world");
        }};
    }

    @GetMapping("result")
    public Result<Map<String, String>> result() {
        return Result.success(new LinkedHashMap<String, String>() {{
            put("hello", "hello");
            put("world", "world");
        }});
    }

    @GetMapping("error401")
    public Map<String, String> error401() {
        throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
    }


    @GetMapping(value = "string", produces = MediaType.APPLICATION_JSON_VALUE)
    public String string() {
        return "hello, world";
    }

    @GetMapping(value = "validator")
    public String validator(@Validated ValidatedDto validatedDto) {
        return "hello, world";
    }
}
