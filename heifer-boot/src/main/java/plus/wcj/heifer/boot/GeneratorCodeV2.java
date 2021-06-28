package plus.wcj.heifer.boot;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.AES;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.baomidou.mybatisplus.generator.config.IKeyWordsHandler;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.H2Query;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressWarnings("all")
public class GeneratorCodeV2 {


    private static String key = "xxxxxx";


    /** 模块名 */
    private static String moduleName = "rbac";

    /** 要生成的表名 */
    private static String[] tables = {
            "rbac_dept",
            "rbac_org",
            "rbac_permission",
            "rbac_role",
            "rbac_role_data_power",
            "rbac_role_permission_rel",
            "rbac_user",
            "rbac_user_data_power",
            "rbac_user_permission_rel",
            "rbac_user_role_rel",
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


    // public static void main(String[] args) {
    //     System.out.println("mpw:" + AES.encrypt("jdbc:mysql://xxxxx :3306/heifer_boot?serverTimezone=UTC", "xxxxxxxxxxxxxxxx"));
    //     System.out.println("mpw:" + AES.encrypt("xxxxxx", "xxxxxxxxxxxxxxxx"));
    //     System.out.println("mpw:" + AES.encrypt("xxxxxx", "xxxxxxxxxxxxxxxx"));
    // }


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
        mpg.setCfg(injectionConfig(mpg.getPackageInfo(), mpg.getDataSource()));
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
        strategy.setSuperServiceClass("plus.wcj.heifer.boot.extension.service.IService");
        strategy.setSuperServiceImplClass("plus.wcj.heifer.boot.extension.service.impl.ServiceImpl");
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

    private static InjectionConfig injectionConfig(PackageConfig packageInfo, DataSourceConfig dataSourceConfig) {


        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                if (super.getMap() == null) {
                    super.setMap(new HashMap<>());
                }
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

                Map<String, Object> map = super.getMap();
                Map<String, Boolean> columnsNotNull = new HashMap<String, Boolean>();
                map.put(tableInfo.getEntityName(), columnsNotNull);

                // TODO: 2021/6/1 changjin wei(魏昌进)

                DbType dbType = dataSourceConfig.getDbType();
                IDbQuery dbQuery = dataSourceConfig.getDbQuery();
                String tableName = tableInfo.getName();
                Connection connection = dataSourceConfig.getConn();
                try {
                    String tableFieldsSql = dbQuery.tableFieldsSql();
                    Set<String> h2PkColumns = new HashSet<>();
                    if (DbType.POSTGRE_SQL == dbType) {
                        tableFieldsSql = String.format(tableFieldsSql, tableName);
                    } else if (DbType.KINGBASE_ES == dbType) {
                        tableFieldsSql = String.format(tableFieldsSql, dataSourceConfig.getSchemaName(), tableName);
                    } else if (DbType.DB2 == dbType) {
                        tableFieldsSql = String.format(tableFieldsSql, dataSourceConfig.getSchemaName(), tableName);
                    } else if (DbType.ORACLE == dbType) {
                        tableName = tableName.toUpperCase();
                        tableFieldsSql = String.format(tableFieldsSql.replace("#schema", dataSourceConfig.getSchemaName()), tableName);
                    } else if (DbType.DM == dbType) {
                        tableName = tableName.toUpperCase();
                        tableFieldsSql = String.format(tableFieldsSql, tableName);
                    } else if (DbType.H2 == dbType) {
                        try (PreparedStatement pkQueryStmt = connection.prepareStatement(String.format(H2Query.PK_QUERY_SQL, tableName));
                             ResultSet pkResults = pkQueryStmt.executeQuery()) {
                            while (pkResults.next()) {
                                String primaryKey = pkResults.getString(dbQuery.fieldKey());
                                if (Boolean.parseBoolean(primaryKey)) {
                                    h2PkColumns.add(pkResults.getString(dbQuery.fieldName()));
                                }
                            }
                        }
                        tableFieldsSql = String.format(tableFieldsSql, tableName);
                    } else {
                        tableFieldsSql = String.format(tableFieldsSql, tableName);
                    }
                    try (
                            PreparedStatement preparedStatement = connection.prepareStatement(tableFieldsSql);
                            ResultSet results = preparedStatement.executeQuery()) {
                        while (results.next()) {
                            String columnName = results.getString(dbQuery.fieldName());

                            String newColumnName = columnName;
                            IKeyWordsHandler keyWordsHandler = dataSourceConfig.getKeyWordsHandler();
                            if (keyWordsHandler != null && keyWordsHandler.isKeyWords(columnName)) {
                                System.err.printf("当前表[%s]存在字段[%s]为数据库关键字或保留字!%n", tableName, columnName);
                                newColumnName = keyWordsHandler.formatColumn(columnName);
                            }
                            Boolean isNotNull = BooleanUtils.NO.equalsIgnoreCase(results.getString("Null"));
                            columnsNotNull.put(newColumnName, isNotNull);
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("SQL Exception：" + e.getMessage());
                }
            }

            @Override
            public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
                objectMap.put("fieldsNotNull", super.getMap());
                return super.prepareObjectMap(objectMap);
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
                    return projectPath + "/src/main/resources/mapper/" + "" + tableInfo.getXmlName() + "Mapper" + ConstVal.XML_SUFFIX;
                }
                return projectPath + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getXmlName() + ConstVal.XML_SUFFIX;
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
        dsc.setTypeConvert(new TypeConvert());
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

    static class TypeConvert extends MySqlTypeConvert {
        @Override
        public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
            if ("int(11) unsigned".equals(fieldType) || "int unsigned".equals(fieldType)) {
                return DbColumnType.LONG;
            }
            return super.processTypeConvert(config, fieldType);
        }
    }


}


