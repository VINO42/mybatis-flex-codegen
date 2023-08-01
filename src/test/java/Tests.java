import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import io.github.vino42.Generator;
import io.github.vino42.config.ColumnConfig;
import io.github.vino42.config.EntityConfig;
import io.github.vino42.config.GlobalConfig;
import io.github.vino42.config.TableDefConfig;
import org.sqlite.date.DateFormatUtils;

import java.io.File;
import java.util.Date;
import java.util.HashSet;

import static io.github.vino42.constants.GenConstant.DOT;

/**
 * =====================================================================================
 *
 * @Created :   2023/8/1 22:35
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public class Tests {
    public static void main(String[] args) {
        String json = "{\"rootFolder\":\"G:/Codes/LLL/mybatis-flex-codegen\",\"pack\":\"io.github.vino42\",\"moduleName\":\"mymodule123\",\"prefix\":\"\",\"cover\":true,\"author\":\"vino\",\"isLombok\":true,\"isSwagger\":true,\"isRestController\":true,\"isResultMap\":true,\"isFill\":true,\"templatePath\":\"/flextemplates\",\"entityName\":\"entity\",\"mapperName\":\"mapper\",\"controllerName\":\"controller\",\"serviceName\":\"service\",\"serviceImplName\":\"service.impl\",\"idtype\":1,\"isEnableCache\":true,\"isBaseColumnList\":true,\"isAuthor\":false,\"isEntity\":true,\"isMapper\":true,\"isController\":true,\"isService\":true,\"isServiceImpl\":true}";

        Gson gson = new Gson();
        GenConfig genConfig = gson.fromJson(json, GenConfig.class);
        String tableName = "application";

        generatorCode(tableName, genConfig, null, null);
    }

    private static void generatorCode(String tableName, GenConfig genConfig, Object o, Object o1) {
        // 数据源配置

        HikariDataSource dataSource = new HikariDataSource();
//        Messages.showInfoMessage("dbUrl：" + MysqlUtil.getInstance().getDbUrl(), "Mybatis Plus");
//        Messages.showInfoMessage("dbUrl：" + MysqlUtil.getInstance().getUsername(), "Mybatis Plus");
//        Messages.showInfoMessage("dbUrl：" + MysqlUtil.getInstance().getPassword(), "Mybatis Plus");

//        dataSource.setJdbcUrl(MysqlUtil.getInstance().getDbUrl());
//        dataSource.setUsername(MysqlUtil.getInstance().getUsername());
//        dataSource.setPassword(MysqlUtil.getInstance().getPassword());

        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/lll?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("nopasswd");
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setTemplateEngine(new EnjoyTemplate());

        //设置表前缀和只生成哪些表
        if (genConfig.isRestController()) {
            globalConfig.setCustomConfig("restControllerStyle", genConfig.isRestController());
        }
        //包设置
        String projectPath = genConfig.getRootFolder();
        globalConfig.getPackageConfig().setSourceDir(projectPath + File.separator + genConfig.getModuleName() + "/src/main/java");
        String pack = genConfig.getPack();
        String templatePath = "/templates";

        globalConfig.setCustomConfig("genController", genConfig.isController());
        globalConfig.setCustomConfig("genResultMap ", genConfig.isResultMap());
        globalConfig.setCustomConfig("genBaseColumn", genConfig.isBaseColumnList());

        globalConfig.setCustomConfig("DATE", DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
        globalConfig.setCustomConfig("TIME", DateFormatUtils.format(new Date(), "HH:mm:ss"));
        globalConfig.setCustomConfig("entitySerialVersionUID", true);
        globalConfig.setCustomConfig("enableMapperCache", genConfig.isEnableCache());
        globalConfig.setCustomConfig("baseResultMap", genConfig.isResultMap());
        globalConfig.setCustomConfig("baseColumnList", genConfig.isBaseColumnList());

        //文档设置
        globalConfig.getJavadocConfig().setAuthor(genConfig.getAuthor());
        //控制器生成配置
        if (genConfig.isController()) {
            //生成controller的配置
            globalConfig.setControllerGenerateEnable(true);
            globalConfig.setControllerOverwriteEnable(genConfig.isCover());
            globalConfig.getJavadocConfig().setControllerPackage(pack + DOT + genConfig.getControllerName());
            globalConfig.getPackageConfig().setControllerPackage(pack + DOT + genConfig.getControllerName());
            globalConfig.getTemplateConfig().setController(templatePath + "/controller.tpl");
            globalConfig.enableController().setRestStyle(true);
            globalConfig.setControllerRestStyle(true);
        }
        System.out.println("controoler");
        //service生成配置
        if (genConfig.isService()) {
            globalConfig.setServiceGenerateEnable(true);
            globalConfig.setServiceOverwriteEnable(genConfig.isCover());
            globalConfig.getJavadocConfig().setServicePackage(pack + DOT + genConfig.getServiceName());
            globalConfig.getPackageConfig().setServicePackage(pack + DOT + genConfig.getServiceName());
            globalConfig.getTemplateConfig().setService(templatePath + "/service.tpl");
        }
        System.out.println("service生成配置");

        //service生成配置
        if (genConfig.isServiceImpl()) {
            globalConfig.setServiceImplGenerateEnable(true);
            globalConfig.setServiceImplOverwriteEnable(genConfig.isCover());
            globalConfig.getJavadocConfig().setServiceImplPackage(pack + DOT + genConfig.getServiceImplName());
            globalConfig.getPackageConfig().setServiceImplPackage(pack + DOT + genConfig.getServiceImplName());
            globalConfig.getTemplateConfig().setServiceImpl(templatePath + "/serviceImpl.tpl");
            globalConfig.enableServiceImpl().setClassSuffix("Impl");
        }
        System.out.println("serviceImpl生成配置");

        //mapper生成配置
        if (genConfig.isMapper()) {
            globalConfig.setMapperGenerateEnable(true);
            globalConfig.setMapperOverwriteEnable(genConfig.isCover());
            globalConfig.setMapperXmlOverwriteEnable(genConfig.isCover());

            globalConfig.getJavadocConfig().setMapperPackage(pack + DOT + genConfig.getMapperName());
            globalConfig.getPackageConfig().setMapperPackage(pack + DOT + genConfig.getMapperName());
            globalConfig.getTemplateConfig().setMapper(templatePath + "/mapper.tpl");

            globalConfig.setMapperXmlGenerateEnable(true);
            globalConfig.setMapperXmlOverwriteEnable(genConfig.isCover());
            globalConfig.getPackageConfig().setMapperXmlPath(projectPath + File.separator + genConfig.getModuleName() + "/src/main/resources/mapper");
            globalConfig.getTemplateConfig().setMapperXml(templatePath + "/mapperXml.tpl");
        }
        System.out.println("mapper生成配置");

        //entity生成配置
        if (genConfig.isEntity()) {
            int idtype = genConfig.getIdtype();

            globalConfig.setEntityGenerateEnable(true);
            globalConfig.setEntityOverwriteEnable(genConfig.isCover());
            globalConfig.getJavadocConfig().setEntityPackage(pack + DOT + genConfig.getEntityName());
            globalConfig.getPackageConfig().setEntityPackage(pack + DOT + genConfig.getEntityName());
            globalConfig.getTemplateConfig().setEntity(templatePath + "/entity.tpl");
            globalConfig.setEntityClassSuffix("Entity");

            globalConfig.setTableDefGenerateEnable(true);
            globalConfig.setTableDefPropertiesNameStyle(TableDefConfig.NameStyle.UPPER_CAMEL_CASE);
            globalConfig.setTableDefOverwriteEnable(genConfig.isCover());
            globalConfig.getPackageConfig().setTableDefPackage(pack + DOT + genConfig.getEntityName() + ".def");
            globalConfig.getJavadocConfig().setTableDefPackage(pack + DOT + genConfig.getEntityName() + ".def");
            globalConfig.setTableDefPropertiesNameStyle(TableDefConfig.NameStyle.UPPER_CASE);
            globalConfig.getTemplateConfig().setTableDef(templatePath + "/tableDef.tpl");

        }
        System.out.println("entity生成配置");

        globalConfig.getJavadocConfig().setTableDefPackage(genConfig.getPack());
        //swagger配置
        if (genConfig.isSwagger()) {
            globalConfig.setEntityWithSwagger(genConfig.isSwagger());
            globalConfig.getEntityConfig().setSwaggerVersion(EntityConfig.SwaggerVersion.DOC);
        }
        if (genConfig.isFill()) {
            ColumnConfig columnConfig = new ColumnConfig();
            columnConfig.setOnUpdateValue("update_time");
            columnConfig.setOnInsertValue("create_time");
            globalConfig.getStrategyConfig().setColumnConfig(columnConfig);
        }
        //lombok配置
        if (genConfig.isLombok()) {
            globalConfig.enableEntity().setWithLombok(true);
        }

        globalConfig.getPackageConfig().setBasePackage(genConfig.getPack());
        globalConfig.getPackageConfig().setTableDefPackage(pack + DOT + genConfig.getEntityName());
        //策略配置
        //数据库表前缀，多个前缀用英文逗号（,） 隔开
        globalConfig.getStrategyConfig().setTablePrefix(genConfig.getPrefix());
        //是否生成视图映射
        globalConfig.getStrategyConfig().setGenerateForView(false);
        //逻辑删除的默认字段名称
        globalConfig.getStrategyConfig().setLogicDeleteColumn(null);
        //乐观锁的字段名称
        globalConfig.getStrategyConfig().setVersionColumn(null);

        HashSet<String> objects = new HashSet<>();
        objects.add(tableName);
        globalConfig.getStrategyConfig().setGenerateTables(objects);


        Generator generator = new Generator(dataSource, globalConfig);
//        Messages.showInfoMessage("开始生成！", "Mybatis Plus");
        System.out.println("开始生成");

        generator.generate();
    }
}
