#set(primaryKey = table.getPrimaryKey())
#set(entityClassName = table.buildEntityClassName())
package #(packageConfig.serviceImplPackage);

import #(serviceImplConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());
import #(packageConfig.mapperPackage).#(table.buildMapperClassName());
import #(packageConfig.servicePackage).#(table.buildServiceClassName());
import org.springframework.stereotype.Service;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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
@Service
public class #(table.buildServiceImplClassName()) extends #(serviceImplConfig.buildSuperClassName())<#(table.buildMapperClassName()), #(table.buildEntityClassName())> implements #(table.buildServiceClassName()) {

    @Override
    public boolean remove(QueryWrapper query) {
        return super.remove(query);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public boolean update(#(entityClassName) entity, QueryWrapper query) {
        return super.update(entity, query);
    }

    @Override
    public boolean updateById(#(entityClassName) entity, boolean ignoreNulls) {
        return super.updateById(entity, ignoreNulls);
    }

    @Override
    public boolean updateBatch(Collection<#(entityClassName)> entities, int batchSize) {
        return super.updateBatch(entities, batchSize);
    }

    @Override
    public #(entityClassName) getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public #(entityClassName) getOne(QueryWrapper query) {
        return super.getOne(query);
    }

    @Override
    public <R> R getOneAs(QueryWrapper query, Class<R> asType) {
        return super.getOneAs(query, asType);
    }

    @Override
    public List<#(entityClassName)> list(QueryWrapper query) {
        return super.list(query);
    }

    @Override
    public <R> List<R> listAs(QueryWrapper query, Class<R> asType) {
        return super.listAs(query, asType);
    }

    /**
     * @deprecated 无法通过注解进行缓存操作。
     */
    @Override
    @Deprecated
    public List<#(entityClassName)> listByIds(Collection<? extends Serializable> ids) {
        return super.listByIds(ids);
    }

    @Override
    public long count(QueryWrapper query) {
        return super.count(query);
    }

    @Override
    public <R> Page<R> pageAs(Page<R> page, QueryWrapper query, Class<R> asType) {
        return super.pageAs(page, query, asType);
    }

}
