package com.hc.bean.matcher;

import com.hc.bean.ClassScanner;
import com.hc.bean.annotation.Matcher;
import com.hc.bean.conf.DefaultConfig;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MatcherFactory {
    private static final Logger LOGGER  = LoggerFactory.getLogger(MatcherFactory.class);
    private static final String MATCHER_CONF_PATH = "matcher.properties";
    private static final String SCAN_PACKAGE = "matcher.scan.package";
    private static Map<String,IMatcher> matchers = new HashMap<>();
    static{
        loadCoreMatcher();
        loadCofigMatcher();
    }

    private static void loadCofigMatcher() {
        try{
            List<String> datas = IOUtils.readLines(MatcherFactory.class.getClassLoader()
                    .getResourceAsStream(MATCHER_CONF_PATH));
            for(String clazzName : datas){
                Object o= Class.forName(clazzName).newInstance();
                if(o instanceof IMatcher){
                    IMatcher matcher = (IMatcher) o;
                    if(matchers.get(matcher.getName()) != null){
                        LOGGER.error("WARN!!! ALREADY HAVE MATCHER {},THE" +
                                " MATCHER IN matcher.properties WILL NOT LOAD TO PROGRAM!!!!!!");
                        continue;
                    }
                    matchers.put(matcher.getName(),matcher);
                }else{
                    LOGGER.error("class {} is not implement IMatcher");
                }
            }
        }catch(Exception e){
            LOGGER.error("exception happen while load matcher {}",e.getMessage());
        }
    }

    private static void loadCoreMatcher() {
        String basePackage = DefaultConfig.get(SCAN_PACKAGE);
        LOGGER.info("begin to scan all matcher ,base package {}",basePackage);
        Set<Class<IMatcher>> classSet = ClassScanner.findAnnotatedClasses(basePackage,Matcher.class,IMatcher.class);
        LOGGER.info("found matcher {}",classSet.size());
        for(Class clazz : classSet){
            try {
                String name = ((Matcher)clazz.getAnnotation(Matcher.class)).value();
                LOGGER.info("get matcher {} {}",name,clazz.getName());
                matchers.put(name, (IMatcher) clazz.newInstance());
                LOGGER.info("put matcher {} {}",name,clazz.getName());
            } catch (Exception e) {
                LOGGER.info("create class instance exception",e);
                LOGGER.info("create class instance {} error",clazz.getName());
            }
        }
        LOGGER.info("load {} matcher",matchers.size());
    }

    public static IMatcher getMatcher(String type){
        return matchers.get(type);
    }
}
