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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import plus.wcj.heifer.common.mybatisplus.IService;
import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/21
 */
@SuppressWarnings("AlibabaAvoidCommentBehindStatement")
public class CodeGenerator {

    // 设置需要生成的表名
    private static final String[] tables = {"rbac_account"};
    // 设置父包模块名
    private static final String moduleName = "iam";


    // 新工程需要配置一次 start
    private static final String parent = "main";  // 设置父包名
    private static final String url = "jdbc:mysql://nuc8i7.wcj.plus:3306/heifer_boot?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String username = "root";
    private static final String password = "weichangjin";

    private static final String idType = Long.class.getSimpleName();

    // 新工程需要配置一次 end

    // 下面的别动
    private static final String author = System.getenv("Duser_name") != null ? System.getenv("Duser_name") : System.getenv("USER");
    private static final String javaDir = System.getProperty("user.dir") + "/src/main/java";
    private static final String xmlDir = System.getProperty("user.dir") + "/src/main/resources/mapper/" + moduleName;
    static final Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");

    private static final Map<String, Object> map = new LinkedHashMap<String, Object>() {{
        // put("RedisHash", true);
        // put("implements", new Class[]{});
        // put("idType", idType);
    }};

    public static void main(String[] args) {
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password)
                                         .dbQuery(new MySqlQuery() {
                                             @Override
                                             public String[] fieldCustom() {
                                                 return new String[]{"Null"};
                                             }
                                         })
                         ).globalConfig(builder -> {
                             builder.author(author) // 设置作者
                                    .enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir(javaDir); // 指定输出目录
                         }).packageConfig(builder -> {
                             builder.parent(parent).moduleName(moduleName) // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, xmlDir)); // 设置mapperXml生成路径
                         }).strategyConfig(builder -> {
                             builder.addInclude(tables) // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_") // 设置过滤表前缀
                             ;
                             // Entity 策略配置
                             builder.entityBuilder().enableLombok().naming(NamingStrategy.underline_to_camel).idType(IdType.ASSIGN_ID);
                             // Controller 策略配置
                             builder.controllerBuilder().enableRestStyle();
                             // Service 策略配置
                             builder.serviceBuilder().superServiceClass(IService.class).superServiceImplClass(ServiceImpl.class).formatServiceFileName("%sService");
                             // Mapper 策略配置
                             builder.mapperBuilder().enableBaseColumnList().formatMapperFileName("%sDao");

                         }).templateConfig(builder -> {
                             builder.controller("generator/templates/controller.java")
                                    .entity("generator/templates/entity.java")
                                    .mapper("generator/templates/mapper.java")
                                    .xml("generator/templates/mapper.xml")
                                    .service("generator/templates/service.java")
                                    .serviceImpl("generator/templates/serviceImpl.java");
                         }).injectionConfig(builder -> {
                             builder.beforeOutputFile((tableInfo, stringObjectMap) -> {
                                        tableInfo.setComment(pattern.matcher(tableInfo.getComment()).replaceAll(""));
                                        tableInfo.getFields().forEach(tableField -> tableField.setComment(pattern.matcher(tableField.getComment()).replaceAll("")));
                                    })
                                    .customMap(map)
                             ;
                         })
                         // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                         .templateEngine(new FreemarkerTemplateEngine()).execute();

    }
}
