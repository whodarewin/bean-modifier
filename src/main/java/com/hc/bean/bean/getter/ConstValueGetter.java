package com.hc.bean.bean.getter;


public class ConstValueGetter implements IValueGetter {

    private Object constValue;

    public ConstValueGetter(Object constValue) {
        this.constValue = constValue;
    }

    @Override
    public Object get(Object bean) throws Exception {
        return constValue;
    }

    @Override
    public void setNext(IValueGetter next) {
        throw new RuntimeException("not support");
    }
}
