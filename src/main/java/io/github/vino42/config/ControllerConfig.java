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
 * 生成 Controller 的配置。
 *
 * @author 王帅
 * @since 2023-05-15
 */
@SuppressWarnings("unused")
public class ControllerConfig {

    /**
     * Controller 类的前缀。
     */
    private String classPrefix = "";

    /**
     * Controller 类的后缀。
     */
    private String classSuffix = "Controller";

    /**
     * 自定义 Controller 的父类。
     */
    private Class<?> superClass;

    /**
     * 是否覆盖之前生成的文件。
     */
    private boolean overwriteEnable;

    /**
     * 生成 REST 风格的 Controller。
     */
    private boolean restStyle = true;

    public String buildSuperClassImport() {
        return superClass.getName();
    }

    public String buildSuperClassName() {
        return superClass.getSimpleName();
    }

    /**
     * 获取类前缀。
     */
    public String getClassPrefix() {
        return classPrefix;
    }

    /**
     * 设置类前缀。
     */
    public ControllerConfig setClassPrefix(String classPrefix) {
        this.classPrefix = classPrefix;
        return this;
    }

    /**
     * 获取类后缀。
     */
    public String getClassSuffix() {
        return classSuffix;
    }

    /**
     * 设置类后缀。
     */
    public ControllerConfig setClassSuffix(String classSuffix) {
        this.classSuffix = classSuffix;
        return this;
    }

    /**
     * 获取父类。
     */
    public Class<?> getSuperClass() {
        return superClass;
    }

    /**
     * 设置父类。
     */
    public ControllerConfig setSuperClass(Class<?> superClass) {
        this.superClass = superClass;
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
    public ControllerConfig setOverwriteEnable(boolean overwriteEnable) {
        this.overwriteEnable = overwriteEnable;
        return this;
    }

    /**
     * 是否 REST 风格。
     */
    public boolean isRestStyle() {
        return restStyle;
    }

    /**
     * 设置 REST 风格。 restController
     */
    public ControllerConfig setRestStyle(boolean restStyle) {
        this.restStyle = restStyle;
        return this;
    }

    public boolean  getRestStyle(){
        return  restStyle;
    }

}