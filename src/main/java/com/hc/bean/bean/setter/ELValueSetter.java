package com.hc.bean.bean.setter;

import com.hc.bean.ELProcessorFactory;
import com.hc.bean.ELUtil;

import javax.el.ELProcessor;

//TODO:也许会有性能问题
public class ELValueSetter implements IValueSetter {
    private static final String BEAN_DEFINE_NAME = "bean";
    private String express;

    public ELValueSetter(String express) {
        this.express = ELUtil.formatEL(express);
    }

    @Override
    public void set(Object bean, Object value) throws Exception {
        ELProcessor processor = ELProcessorFactory.create(null,null,null);
        processor.defineBean(BEAN_DEFINE_NAME,bean);
        processor.setValue(express,value);
    }
}
