package plus.wcj.heifer.boot;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.AES;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GeneratorCodeV2 {


    private static String key = "xxxxxx";


    /** 模块名 */
    private static String moduleName = "abc";

    /** 要生成的表名 */
    private static String[] tables = {
            // "rbac_dept",
            // "rbac_permission",
            // "rbac_role",
            // "rbac_role_data_power",
            // "rbac_role_permission_rel",
            // "rbac_user",
            // "rbac_user_data_power",
            // "rbac_user_permission_rel",
            // "rbac_user_role_rel",
            // "tenant_client",
            // "tenant_org",
            "test_hahah",
    };

    /** 表的前缀 */
    private static String[] tablePrefix = {
    };
    /** 数据库逻辑删除字段 */
    private static String logicDeleteFieldName = "is_delete";


    private static String url = "jiD4Yw3oNwCOQZO1bX0ksHZQoZrTH5j+CIqHSZNIWR6H2fb2/Al/Mb6EfAiQrMDKBil4CvkeBh7134KMM3RS5g==";
    private static String username = "9BaRvNjQ0aUgYyX++zKQ3Q==";
    private static String password = "AmNKrHnSb16mbMa/kDRgIQ==";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String schemaName = "public";

    private static String parent = "plus.wcj.heifer.boot";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        mpg.setGlobalConfig(globalConfig());
        // 数据源配置
        mpg.setDataSource(dataSourceConfig());
        // 包配置
        mpg.setPackageInfo(packageConfig());
        // 自定义配置
        mpg.setCfg(injectionConfig(mpg.getPackageInfo()));
        // 配置模板
        mpg.setTemplate(templateConfig());
        // 策略配置
        mpg.setStrategy(strategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static StrategyConfig strategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(tablePrefix);
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(tables);
        // strategy.setExclude();
        // strategy.setFieldPrefix();
        // strategy.setCapitalMode();
        // strategy.setSkipView(false);
        // strategy.setNameConvert();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        // strategy.setSuperMapperClass("你自己的父类实体,没有就不用设置!");
        // strategy.setSuperServiceClass("你自己的父类实体,没有就不用设置!");
        // strategy.setSuperServiceImplClass("你自己的父类实体,没有就不用设置!");
        // strategy.setSuperControllerClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntitySerialVersionUID(true);
        // strategy.setEntityColumnConstant();
        // strategy.setEntityBuilderModel();
        strategy.setEntityLombokModel(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        // strategy.setVersionFieldName();
        strategy.setLogicDeleteFieldName(logicDeleteFieldName);
        return strategy;

    }

    /**
     * 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
     *
     * @return
     */
    private static TemplateConfig templateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("/generator/templates/entity.java");
        // templateConfig.setEntityKt();
        // templateConfig.setService("/generator/templates/service.java");
        // templateConfig.setServiceImpl("/generator/templates/serviceImpl.java");
        // templateConfig.setMapper();
        templateConfig.setXml(null);
        templateConfig.setController("/generator/templates/controller.java");
        return templateConfig;
    }

    static final Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");

    private static InjectionConfig injectionConfig(PackageConfig packageInfo) {


        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }

            @Override
            public void initTableMap(TableInfo tableInfo) {
                super.initTableMap(tableInfo);

                // tableInfo.setServiceName(tableInfo.getServiceName().substring(1));
                tableInfo.setComment(pattern.matcher(tableInfo.getComment()).replaceAll(""));
                for (TableField field : tableInfo.getFields()) {
                    if (StringUtils.isNotEmpty(field.getComment())) {
                        field.setComment(pattern.matcher(field.getComment()).replaceAll(""));
                    }
                }
            }
        };

        String projectPath = System.getProperty("user.dir");

        // 如果模板引擎是 freemarker
        String templatePath = "templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";


        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {


            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                if (moduleName == null) {
                    return projectPath + "/src/main/resources/mapper/" + "" + tableInfo.getXmlName() + "Mapper" + StringPool.DOT_XML;
                }
                return projectPath + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getXmlName() + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent(parent);
        pc.setModuleName(null);
        pc.setEntity(pc.getEntity() + "." + moduleName);
        pc.setService(pc.getService() + "." + moduleName);
        pc.setServiceImpl(pc.getService() + ".impl");
        pc.setMapper("dao." + moduleName);
        pc.setXml("dao." + moduleName + ".mapper");
        pc.setController(pc.getController() + "." + moduleName);

        return pc;
    }

    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(AES.decrypt(url, key));
        dsc.setSchemaName(schemaName);
        dsc.setDriverName(driverName);
        dsc.setUsername(AES.decrypt(username, key));
        dsc.setPassword(AES.decrypt(password, key));
        // dsc.setTypeConvert();
        return dsc;
    }

    /**
     * 全局配置
     *
     * @return
     */
    private static synchronized GlobalConfig globalConfig() {
        String projectPath = System.getProperty("user.dir");

        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setEnableCache(false);
        gc.setAuthor(System.getenv().get("USER"));
        gc.setKotlin(false);
        gc.setSwagger2(true);
        // gc.setActiveRecord();
        gc.setBaseResultMap(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setBaseColumnList(true);
        gc.setEntityName("%sDo");
        gc.setMapperName("%sDao");
        // gc.setXmlName();
        gc.setServiceName("%s" + ConstVal.SERVICE);
        // gc.setServiceImplName();
        // gc.setControllerName();
        gc.setIdType(IdType.ASSIGN_ID);
        return gc;
    }
}
