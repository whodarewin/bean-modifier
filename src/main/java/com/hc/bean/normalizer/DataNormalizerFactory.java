package com.hc.bean.normalizer;

import com.hc.bean.ClassScanner;
import com.hc.bean.annotation.DataNormalizer;
import com.hc.bean.conf.DefaultConfig;
import com.hc.bean.normalizer.param.BeanNormalizeParamModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataNormalizerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataNormalizerFactory.class);
    private static final String SCAN_PACKAGE = "normalizer.scan.package";

    private static Map<String,Class<IDataNormalizer>> key2DataNormalizer = new HashMap<>();

    static{
        loadCoreDataNormalizer();
    }
    //todo:可配包范围，现在扫一遍太慢
    private static void loadCoreDataNormalizer() {
        String basePackage = DefaultConfig.get(SCAN_PACKAGE);
        LOGGER.info("begin to scan all class to find normalizer,base package {}",basePackage);
        Set<Class<IDataNormalizer>> classSet = ClassScanner.findAnnotatedClasses(basePackage,DataNormalizer.class,IDataNormalizer.class);
        LOGGER.info("find DataNormalizer {}",classSet.size());
        for(Class clazz : classSet){
            String name = ((DataNormalizer)clazz.getAnnotation(DataNormalizer.class)).value();
            key2DataNormalizer.put(name, clazz);
            LOGGER.info("get DataNormalizer {} {}",name,clazz.getName());

        }
        LOGGER.info("loaded {} DataNormalizer", key2DataNormalizer.size());
    }


    public static IDataNormalizer createNormalizer(BeanNormalizeParamModel param){

        IDataNormalizer head = null;
        IDataNormalizer tail = null;
        String[] types = StringUtils.split(param.getType(),'.');
        int index = 0;
        List params = param.getParams();
        for(String t : types) {
            Class<IDataNormalizer> clazz = key2DataNormalizer.get(t);
            if(clazz == null){
                LOGGER.error("no IDataNormalizer type defined for type {}",t);
                throw new RuntimeException("no normalizer found "+ t);
            }
            Constructor[] constructors = clazz.getConstructors();

            if (constructors.length > 1) {
                LOGGER.error("can not instance class because it have more than one constructor {}", clazz.getName());
                throw new RuntimeException("instance class "+clazz.getName() +" error,param size > 1");
            }

            Constructor constructor = constructors[0];
            int paramLength = constructor.getParameterCount();
            Object[] conParams = new Object[paramLength];
            for (int i = 0; i < paramLength; i++) {
                conParams[i] = params.get(index++);
            }
            try {
                IDataNormalizer normalizer = (IDataNormalizer) constructor.newInstance(conParams);
                if (head == null) {
                    head = normalizer;
                    tail = head;
                } else {
                    tail.setNext(normalizer);
                    tail = normalizer;
                }
            } catch (Exception e) {
                LOGGER.error("create new instance of IDataNormalizer error", e);
            }
        }
        return head;
    }
}
