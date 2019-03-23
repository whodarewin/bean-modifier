package com.hc.bean.normalizer;


import com.hc.bean.annotation.DataNormalizer;

@DataNormalizer("chineseNum2arab")
public class ChineseNum2ArabDataNormalizer extends AbstractDataNormalizer {
    @Override
    public Object normalize(Object bean) throws Exception {
        if(bean == null || bean.equals("")){
            return exec(bean);
        }
        if(bean instanceof String){

            return exec(NumberUtil.chineseToNumber((String) bean));
        }
        throw new RuntimeException("");
    }
}
