package com.hc.bean.normalizer;


import com.hc.bean.annotation.DataNormalizer;

@DataNormalizer("replace")
public class ReplaceDataNormalizer extends AbstractDataNormalizer {
    private Object from;
    private Object to;

    public ReplaceDataNormalizer(Object from, Object to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Object normalize(Object bean) throws Exception {
        Object value = ReplaceUtil.simpleReplace(bean,from,to);
        return exec(value);
    }
}
