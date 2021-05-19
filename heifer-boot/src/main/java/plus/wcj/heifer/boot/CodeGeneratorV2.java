package plus.wcj.heifer.boot;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGeneratorV2 {

    /**
     * 要生成的表名
     */
    private static String[] tables = {"sec_permission",
            "sec_role",
            "sec_role_permission",
            "sec_user",
            "sec_user_role",};

    /**
     * 表的前缀
     */
    private static String[] tablePrefix = {"sec_"};
    /**
     * 数据库逻辑删除字段
     */
    private static String logicDeleteFieldName = "is_delete";


    private static String url = "jdbc:mysql://47.114.167.3:3306/spring-boot-demo?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static String schemaName = "public";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "weichangjin";

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
        mpg.setCfg(injectionConfig());
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
        // templateConfig.setService();
        // templateConfig.setServiceImpl();
        // templateConfig.setMapper();
        // templateConfig.setXml();
        templateConfig.setController("/generator/templates/controller.java");
        return templateConfig;
    }

    private static InjectionConfig injectionConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }

            @Override
            public void initTableMap(TableInfo tableInfo) {
                super.initTableMap(tableInfo);
                tableInfo.setServiceName(tableInfo.getServiceName().substring(1));
                for (TableField field : tableInfo.getFields()) {


                    if (StringUtils.isNotEmpty(field.getComment())) {

                        Matcher m = Pattern.compile("\\s*|\t|\r|\n").matcher(field.getComment());
                        field.setComment(m.replaceAll(""));
                    }
                }
            }
        };


        List<FileOutConfig> focList = new ArrayList<>();
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent(parent);
        pc.setModuleName(null);
        // pc.setEntity();
        // pc.setService();
        // pc.setServiceImpl();
        // pc.setMapper();
        // pc.setXml();
        // pc.setController();

        return pc;
    }

    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setSchemaName(schemaName);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        // dsc.setTypeConvert();
        return dsc;
    }

    /**
     * 全局配置
     *
     * @return
     */
    private static GlobalConfig globalConfig() {
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
        // gc.setEntityName();
        // gc.setMapperName();
        // gc.setXmlName();
        // gc.setServiceName();
        // gc.setServiceImplName();
        // gc.setControllerName();
        gc.setIdType(IdType.ASSIGN_ID);
        return gc;
    }
}
