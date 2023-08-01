package #(packageConfig.entityPackage);

#set(withLombok = entityConfig.isWithLombok())
#set(withSwagger = entityConfig.isWithSwagger())
#set(swaggerVersion = entityConfig.getSwaggerVersion())
#for(importClass : table.buildImports())
import #(importClass);
#end
#if(withSwagger && swaggerVersion.getName() == "FOX")
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
import io.swagger.v3.oas.annotations.media.Schema;
#end
#if(withLombok)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
#end
import com.mybatisflex.core.activerecord.Model;

/**
 * =====================================================================================
 *
 * @Created :   #(table.getGlobalConfig().getCustomConfig("DATE"))
 * @Compiler :  jdk 17
 * @Author :    #(javadocConfig.getAuthor())
 * @Copyright : #(javadocConfig.getAuthor())
 * @Decription : #(table.getComment()) 实体类。
 * @Since:  #(javadocConfig.getSince())
 * =====================================================================================
 */
#if(withLombok)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
#end
#if(withSwagger && swaggerVersion.getName() == "FOX")
@ApiModel("#(table.getComment())")
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
@Schema(description = "#(table.getComment())")
#end
#(table.buildTableAnnotation())
public class #(table.buildEntityClassName()) extends Model<#(table.buildEntityClassName())> #(table.buildImplements()) {

#for(column : table.columns)
    #set(comment = javadocConfig.formatColumnComment(column.comment))

    #if(isNotBlank(comment))
    /**
     * #(comment)
     */
    #end
    #set(annotations = column.buildAnnotations())
    #if(isNotBlank(annotations))
    #(annotations)
    #end
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiModelProperty("#(column.comment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Schema(description = "#(column.comment)")
    #end
    private #(column.propertySimpleType) #(column.property);
#end

#if(!withLombok)
    #for(column: table.columns)
    public #(column.propertySimpleType) #(column.getterMethod())() {
        return #(column.property);
    }

    public void #(column.setterMethod())(#(column.propertySimpleType) #(column.property)) {
        this.#(column.property) = #(column.property);
    }

    #end
#end
}
