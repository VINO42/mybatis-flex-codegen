package #(packageConfig.servicePackage);

import #(serviceConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());


/**
 * =====================================================================================
 *
 * @Created :   #(table.getGlobalConfig().getCustomConfig("DATE"))
 * @Compiler :  jdk 17
 * @Author :    #(javadocConfig.getAuthor())
 * @Copyright : #(javadocConfig.getAuthor())
 * @Decription : #(table.getComment()) 服务层。
 * @Since : #(javadocConfig.getSince())
 * =====================================================================================
 */
public interface #(table.buildServiceClassName()) extends #(serviceConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

}
