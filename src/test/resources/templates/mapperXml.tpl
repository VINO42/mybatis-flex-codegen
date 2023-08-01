<?xml version="1.0" encoding="UTF-8"?>
#set(enableMapperCache = table.getGlobalConfig().getCustomConfig("enableMapperCache"))
#set(baseResultMap = table.getGlobalConfig().getCustomConfig("baseResultMap"))
#set(baseColumnList = table.getGlobalConfig().getCustomConfig("baseColumnList"))
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="#(packageConfig.mapperPackage).#(table.buildMapperClassName())">
    #if(enableMapperCache)
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
    #end

    #if(baseResultMap)
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="#(packageConfig.entityPackage).#(table.buildEntityClassName())">
        #for(field : table.columns)
            #set(isPrimaryKey = field.isPrimaryKey())
            #if(isPrimaryKey)
                <id column="#(field.name)"  property="#(field.property)"/>
            #end
        #end
        #for(commonFiled : table.columns)
                #set(isPrimaryKey = commonFiled.isPrimaryKey())
                #if(!isPrimaryKey)
                <result column="#(commonFiled.name)"    property="#(commonFiled.property)"/>
                #end
        #end
    </resultMap>
    #end

#if(baseColumnList)
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
       #set(columns=(table.getGlobalConfig().getCustomConfig("baseColumns")))
       #(columns)
    </sql>
#end
</mapper>
