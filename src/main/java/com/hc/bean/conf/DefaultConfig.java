package com.hc.bean.conf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by congcong.han on 2019/3/23.
 */
public class DefaultConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfig.class);
    private static Properties properties = new Properties();

    {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            LOGGER.info("no config set,use default");
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
}
