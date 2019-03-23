package com.hc.bean.normalizer;


import com.hc.bean.annotation.DataNormalizer;

@DataNormalizer("append")
public class AppendDataNormalizer extends AbstractDataNormalizer {
    private String appendStr;

    public AppendDataNormalizer(String appendStr) {
        this.appendStr = appendStr;
    }

    @Override
    public Object normalize(Object bean) throws Exception {
        if(bean instanceof String){
            return exec(((String)bean).concat(appendStr));
        }
        throw new RuntimeException("not a string in AppenderDataNormalizer");
    }
}
