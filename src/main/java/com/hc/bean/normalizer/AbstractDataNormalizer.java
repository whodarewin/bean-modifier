package com.hc.bean.normalizer;

import com.hc.bean.annotation.DataNormalizer;

public abstract class AbstractDataNormalizer<FROM,TO> implements IDataNormalizer<FROM,TO> {

    private IDataNormalizer next;

    @Override
    public String getName(){
        DataNormalizer matcher = this.getClass().getAnnotation(DataNormalizer.class);
        if(matcher != null){
            return matcher.value();
        }else {
            return null;
        }
    }

    public void setNext(IDataNormalizer next){
        this.next = next;
    }

    public Object exec(Object bean) throws Exception {
        if(next == null){
            return bean;
        }else{
            return next.normalize(bean);
        }
    }
}
