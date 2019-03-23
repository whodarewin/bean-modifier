package com.hc.bean.normalizer;


import com.hc.bean.annotation.DataNormalizer;

@DataNormalizer("toString")
public class ToStringNormalizer extends AbstractDataNormalizer {
    @Override
    public Object normalize(Object bean) throws Exception {
        if(bean == null){
            return exec(bean);
        }
        return exec(bean.toString());
    }
}
