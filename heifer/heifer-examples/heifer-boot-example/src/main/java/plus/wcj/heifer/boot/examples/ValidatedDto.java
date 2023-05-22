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


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/18
 */
public class ValidatedDto {
    @NotNull
    private String name;

    private Integer status;

    private Long age;

    private BigDecimal money;

    private String nullTest;

    private GradeEnum gradeEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getNullTest() {
        return nullTest;
    }

    public void setNullTest(String nullTest) {
        this.nullTest = nullTest;
    }

    public GradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(GradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    @Override
    public String toString() {
        return "ValidatedDto{" +
            "name='" + name + '\'' +
            ", status=" + status +
            ", age=" + age +
            ", money=" + money +
            ", nullTest='" + nullTest + '\'' +
            ", gradeEnum=" + gradeEnum +
            '}';
    }
}
