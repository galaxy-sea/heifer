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

package main;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/21
 */
@SuppressWarnings("AlibabaAvoidCommentBehindStatement")
public class CodeGenerator {

    // 设置父包模块名
    public static final String moduleName = "业务模块名称";
    // 设置父包名
    public static final String parent = "main";


    private static String author = System.getenv("Duser_name") != null ? System.getenv("Duser_name") : System.getenv("USER");

    public static void main(String[] args) {
        FastAutoGenerator.create("url", "username", "password")
                         .globalConfig(builder -> {
                             builder.author(author) // 设置作者
                                    .enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("D://"); // 指定输出目录
                         })
                         .packageConfig(builder -> {
                             builder.parent("com.baomidou.mybatisplus.samples.generator")
                                    .moduleName(moduleName) // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                         })
                         .strategyConfig(builder -> {
                             builder.addInclude("t_simple") // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                         })
                         .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                         .execute();
    }
}
