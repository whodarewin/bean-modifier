package com.hc.bean.bean.getter;

import java.util.Map;

public class MapValueGetter extends AbstractBeanValueGetter {
    private String key;

    public MapValueGetter(String key) {
        this.key = key;
    }

    @Override
    public Object get(Object bean) throws Exception {
        if(bean == null){
            throw new Exception("MapValueGetter:map is null,cannot exec");
        }
        if(bean instanceof Map){
            return execNextNode(((Map) bean).get(key));
        }else{
            throw new Exception("not a map");
        }
    }
}
