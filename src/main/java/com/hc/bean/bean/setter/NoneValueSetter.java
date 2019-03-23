package com.hc.bean.bean.setter;

public enum NoneValueSetter implements IValueSetter {
    INSTANCE;
    @Override
    public void set(Object bean, Object value) throws Exception {
        //do nothing
    }
}
