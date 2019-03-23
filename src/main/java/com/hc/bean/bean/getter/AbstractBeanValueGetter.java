package com.hc.bean.bean.getter;

public abstract class AbstractBeanValueGetter<RET> implements IValueGetter<RET> {
    private IValueGetter next;

    public void setNext(IValueGetter next){
        this.next = next;
    }
    public Object execNextNode(Object o) throws Exception {
        if(next == null){
            return o;
        }
        return next.get(o);
    }
}
