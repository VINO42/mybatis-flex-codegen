package io.github.vino42.engine;

import com.jfinal.template.source.ISource;
import com.jfinal.template.source.ISourceFactory;

/**
 * =====================================================================================
 *
 * @Created :   2023/8/2 3:09
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email : 38912428@qq.com
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public class CusSourceFactory implements ISourceFactory {
    @Override
    public ISource getSource(String baseTemplatePath, String fileName, String encoding) {
        return new CusStringSource(baseTemplatePath, fileName, encoding);
    }
}
