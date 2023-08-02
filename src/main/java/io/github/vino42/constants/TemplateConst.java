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

package io.github.vino42.constants;

/**
 * 代码生成模板常量池。
 *
 * @author 王帅
 * @since 2023-05-16
 */
public final class TemplateConst {

    private TemplateConst() {
    }

    public static final String ENTITY = "/templates/entity.ftl";
    public static final String MAPPER = "/templates/mapper.ftl";
    public static final String SERVICE = "/templates/service.ftl";
    public static final String SERVICE_IMPL = "/templates/serviceImpl.ftl";
    public static final String CONTROLLER = "/templates/controller.ftl";
    public static final String TABLE_DEF = "/templates/tableDef.ftl";
    public static final String MAPPER_XML = "/templates/mapperXml.ftl";
    public static final String PACKAGE_INFO = "/templates/package-info.ftl";

}