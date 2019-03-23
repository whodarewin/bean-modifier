package com.hc.bean.bean.setter;

import org.apache.commons.lang3.StringUtils;

//TODO:做成可配置并加上EL表达式的
public class ValueSetterFactory {
    public static IValueSetter create(String path){
        IValueSetter setter = null;
        if(!StringUtils.isBlank(path)){
            setter = new SimpleValueSetter(path);
        }else{
            setter = NoneValueSetter.INSTANCE;
        }
        return setter;
    }
}
