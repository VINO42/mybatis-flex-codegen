package io.github.vino42.engine;

import com.jfinal.template.source.ISource;

import java.io.*;
import java.net.URL;

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
public class CusStringSource implements ISource {
    protected String finalFileName;
    protected String fileName;
    protected String encoding;

    protected boolean isInJar;
    protected long lastModified;
    protected URL url;

    protected String buildFinalFileName(String baseTemplatePath, String fileName) {
        String finalFileName;
        if (baseTemplatePath != null) {
            char firstChar = fileName.charAt(0);
            if (firstChar == '/' || firstChar == '\\') {
                finalFileName = baseTemplatePath + fileName;
            } else {
                finalFileName = baseTemplatePath + "/" + fileName;
            }
        } else {
            finalFileName = fileName;
        }

        if (finalFileName.charAt(0) == '/') {
            finalFileName = finalFileName.substring(1);
        }


        return finalFileName;
    }

    public CusStringSource(String baseTemplatePath, String fileName, String encoding) {
        this.finalFileName = buildFinalFileName(baseTemplatePath, fileName);
        this.fileName = fileName;
        this.encoding = encoding;
    }

    @Override
    public boolean isModified() {
        return lastModified != getLastModified();
    }

    @Override
    public String getCacheKey() {
        return fileName;
    }

    @Override
    public StringBuilder getContent() {

        InputStream inputStream = CusStringSource.class.getClassLoader().getResourceAsStream(finalFileName);
        try (InputStreamReader isr = new InputStreamReader(inputStream, encoding)) {
            StringBuilder ret = new StringBuilder();
            char[] buf = new char[1024];
            for (int num; (num = isr.read(buf, 0, buf.length)) != -1; ) {
                ret.append(buf, 0, num);
            }
            //这里的打印是给插件用
            System.out.println(ret.toString());
            System.out.println("生成成功！");
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getEncoding() {
        return "utf-8";
    }

    protected long getLastModified() {
        return new File(url.getFile()).lastModified();
    }

    protected void processIsInJarAndlastModified() {
        if ("file".equalsIgnoreCase(url.getProtocol())) {
            isInJar = false;
            lastModified = new File(url.getFile()).lastModified();
        } else {
            isInJar = true;
            lastModified = -1;
        }
    }
}
