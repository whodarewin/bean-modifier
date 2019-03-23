package com.hc.bean.conf;

import org.apache.commons.io.IOUtils;
import java.io.IOException;

/**
 * 从本机properties文件中读取配置
 */
public class PropertiesFileConfigrationSource implements IConfigrationSource {

    private String path;
    private String data;

    public PropertiesFileConfigrationSource(String path) throws IOException {
        this.path = path;
        //"/check.json"
        data = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(path));
    }

    @Override
    public void change(String data) {

    }

    @Override
    public void addListener(ConfListener listener) {
        listener.onChange(data);
    }
}
