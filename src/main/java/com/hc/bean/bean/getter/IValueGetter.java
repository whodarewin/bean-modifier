package com.hc.bean.bean.getter;

public interface IValueGetter<RET> {
    RET get(Object bean) throws Exception;
    void setNext(IValueGetter next);
}
