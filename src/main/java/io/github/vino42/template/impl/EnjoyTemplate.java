package io.github.vino42.template.impl;

import com.jfinal.template.Engine;
import com.mybatisflex.core.util.DateUtil;
import com.mybatisflex.core.util.StringUtil;
import io.github.vino42.engine.CusSourceFactory;
import io.github.vino42.entity.Column;
import io.github.vino42.entity.TableInfo;
import io.github.vino42.template.ITemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Map;

import static com.mybatisflex.core.util.DateUtil.dateMillisecondPattern;
import static io.github.vino42.constants.GenConstant.UTF_8;
import static java.util.stream.Collectors.joining;

/**
 * =====================================================================================
 *
 * @Created :   2023/8/1 23:20
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription : 注意模板的位置要使用  X.class.getClassLoader().getResource("templates/controller.tpl").getPath()
 * <p>
 * =====================================================================================
 */
public class EnjoyTemplate implements ITemplate {
    private Logger logger = LoggerFactory.getLogger(EnjoyTemplate.class);

    private Engine engine;

    public EnjoyTemplate() {
        engine = Engine.createIfAbsent("mybatis-flex-generator", e -> {
            e.addSharedStaticMethod(StringUtil.class);
            e.setSourceFactory(new CusSourceFactory());
        });
    }


    public void writer(Map<String, Object> objectMap, String templatePath, File generateFile) throws Exception {
        String str = engine.getTemplate(templatePath).renderToString(objectMap);
        if (!generateFile.getParentFile().exists() && !generateFile.getParentFile().mkdirs()) {
            throw new IllegalStateException("Can not mkdirs by dir: " + generateFile.getParentFile());
        }
        try (FileOutputStream fos = new FileOutputStream(generateFile);
             OutputStreamWriter ow = new OutputStreamWriter(fos, UTF_8);
             BufferedWriter writer = new BufferedWriter(ow)) {
            writer.append(str);
        }
        logger.debug("模板:" + templatePath + ";  文件:" + generateFile);
    }

    @Override
    public void generate(Map<String, Object> params, String templateFilePath, File generateFile) throws Exception {
        params.put("DATE", DateUtil.toString(new Date(), dateMillisecondPattern));
        TableInfo table = (TableInfo) params.get("table");
        String columns = table.getColumns().stream().map(Column::getName).collect(joining(" , "));
        table.getGlobalConfig().setCustomConfig("baseColumns", columns);
        writer(params, templateFilePath, generateFile);
    }
}
