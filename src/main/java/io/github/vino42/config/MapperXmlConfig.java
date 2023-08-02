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

package io.github.vino42.config;

/**
 * 生成 MapperXml 的配置。
 *
 * @author 王帅
 * @since 2023-05-17
 */
@SuppressWarnings("unused")
public class MapperXmlConfig {

    /**
     * MapperXml 文件的前缀。
     */
    private String filePrefix = "";

    /**
     * MapperXml 文件的后缀。
     */
    private String fileSuffix = "Mapper";

    /**
     * 是否覆盖之前生成的文件。
     */
    private boolean overwriteEnable;
    /**
     * 是否生成baseResultMap。
     */
    private boolean baseResultMap;
    /**
     * 是否生成columnList
     */
    private boolean baseColumnList;
    /**
     * 是否开启二级缓存
     */
    private boolean enableCache;

    /**
     * 获取文件前缀。
     */
    public String getFilePrefix() {
        return filePrefix;
    }

    /**
     * 设置文件前缀。
     */
    public MapperXmlConfig setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
        return this;
    }

    /**
     * 获取文件后缀。
     */
    public String getFileSuffix() {
        return fileSuffix;
    }

    /**
     * 设置文件后缀。
     */
    public MapperXmlConfig setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }

    /**
     * 是否覆盖原有文件。
     */
    public boolean isOverwriteEnable() {
        return overwriteEnable;
    }

    /**
     * 设置是否覆盖原有文件。
     */
    public MapperXmlConfig setOverwriteEnable(boolean overwriteEnable) {
        this.overwriteEnable = overwriteEnable;
        return this;
    }

    public boolean isBaseResultMap() {
        return baseResultMap;
    }

    public boolean isBaseColumnList() {
        return baseColumnList;
    }

    public MapperXmlConfig setBaseResultMap(boolean baseResultMap) {
        this.baseResultMap = baseResultMap;
        return this;
    }

    public MapperXmlConfig setBaseColumnList(boolean baseColumnList) {
        this.baseColumnList = baseColumnList;
        return this;
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public MapperXmlConfig setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
        return this;
    }
}