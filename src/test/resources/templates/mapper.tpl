package #(packageConfig.mapperPackage);

import org.apache.ibatis.annotations.Mapper;
import #(mapperConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());

/**
 * =====================================================================================
 *
 * @Created :   #(table.getGlobalConfig().getCustomConfig("DATE"))
 * @Compiler :  jdk 17
 * @Author :     #(javadocConfig.getAuthor())
 * @Copyright :  #(javadocConfig.getAuthor())
 * @Decription : #(table.getComment()) 映射层。
 * =====================================================================================
 */
@Mapper
public interface #(table.buildMapperClassName()) extends #(mapperConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

}
