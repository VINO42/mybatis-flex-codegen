/*
 *  Copyright (c) 2022-2023, Mybatis-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.github.vino42.generator.impl;


import io.github.vino42.config.GlobalConfig;
import io.github.vino42.config.MapperXmlConfig;
import io.github.vino42.config.PackageConfig;
import io.github.vino42.constants.TemplateConst;
import io.github.vino42.entity.TableInfo;
import io.github.vino42.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * MapperXml 生成器。
 *
 * @author 王帅
 * @since 2023-05-17
 */
public class MapperXmlGenerator implements IGenerator {

    private String templatePath;

    public MapperXmlGenerator() {
        this(TemplateConst.MAPPER_XML);
    }

    public MapperXmlGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(TableInfo table, GlobalConfig globalConfig) throws Exception {

        if (!globalConfig.isMapperXmlGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        MapperXmlConfig mapperXmlConfig = globalConfig.getMapperXmlConfig();

        File mapperXmlFile = new File(packageConfig.getMapperXmlPath() + "/" +
            table.buildMapperXmlFileName() + ".xml");


        if (mapperXmlFile.exists() && !mapperXmlConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(2);
        params.put("table", table);
        params.put("packageConfig", packageConfig);

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, mapperXmlFile);

        System.out.println("MapperXML ---> " + mapperXmlFile);
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
