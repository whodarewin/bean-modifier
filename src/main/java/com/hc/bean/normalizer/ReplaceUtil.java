package com.hc.bean.normalizer;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public class ReplaceUtil {
    /**
     * 简单替换
     * @param bean
     * @param from
     * @param to
     * @return
     */
    public static Object simpleReplace(Object bean, Object from, Object to){
        if(bean instanceof String){
            return StringUtils.replace((String)bean,(String)from,(String)to);
        }else if(bean instanceof Collection){
            return ((Collection) bean).stream().map((value)-> {
                if(from.equals(value)){
                    return to;
                }else{
                    return from;
                }
            }).collect(Collectors.toList());
        }else{
            throw new RuntimeException("replace not support " + bean.getClass().getName());
        }
    }
}
