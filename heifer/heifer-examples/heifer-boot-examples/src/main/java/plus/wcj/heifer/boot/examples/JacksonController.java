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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@RestController
@RequestMapping("jackson")
public class JacksonController {

    @GetMapping()
    public ValidatedDto jackson() {
        ValidatedDto validatedDto = new ValidatedDto();
        validatedDto.setName("name");
        validatedDto.setStatus(2);
        validatedDto.setAge(3L);
        validatedDto.setMoney(new BigDecimal("10"));
        validatedDto.setGradeEnum(GradeEnum.PRIMARY);
        return validatedDto;
    }

    @PostMapping()
    public ValidatedDto jackson(@RequestBody ValidatedDto validatedDto) {
        return validatedDto;
    }

    @GetMapping("string")
    public String string() {
        return "hello";
    }
}
