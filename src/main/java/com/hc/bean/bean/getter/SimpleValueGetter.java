package com.hc.bean.bean.getter;

import org.apache.commons.beanutils.PropertyUtils;

public class SimpleValueGetter<RET> extends AbstractBeanValueGetter<RET> {

    private String path;

    public SimpleValueGetter(String path) {
        this.path = path;
    }

    @Override
    public RET get(Object o) throws Exception {
        Object obj =  PropertyUtils.getProperty(o,path);
        return (RET) super.execNextNode(obj);
    }
}
