package com.hc.bean.bean.getter;

public enum SelfValueGetter implements IValueGetter {
    INSTANCE;
    @Override
    public Object get(Object o) {
        return o;
    }

    @Override
    public void setNext(IValueGetter next) {
        throw new RuntimeException("self getter, get inner properties not supported");
    }
}
