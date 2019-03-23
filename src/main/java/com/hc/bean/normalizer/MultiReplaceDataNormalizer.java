package com.hc.bean.normalizer;

import com.google.common.base.Preconditions;
import com.hc.bean.annotation.DataNormalizer;

import java.util.Map;

@DataNormalizer("multi_replace")
public class MultiReplaceDataNormalizer extends AbstractDataNormalizer {
    private Map<Object,Object> f2t;

    public MultiReplaceDataNormalizer(Map f2t) {
        Preconditions.checkNotNull(f2t);
        this.f2t = f2t;
    }

    @Override
    public Object normalize(Object bean) throws Exception {
        if(bean == null){
            return exec(bean);
        }
        for(Map.Entry entity:f2t.entrySet()){
            Object key = entity.getKey();
            Object value = entity.getValue();
            bean = ReplaceUtil.simpleReplace(bean,key,value);
        }
        return exec(bean);
    }
}
