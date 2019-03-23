package com.hc.bean.normalizer;


import com.hc.bean.annotation.DataNormalizer;

@DataNormalizer("arab2chineseNum")
public class Arab2ChineseNumDataNormalizer extends AbstractDataNormalizer {
    @Override
    public Object normalize(Object bean) throws Exception {
        if (bean == null) {
            return exec(bean);
        }
        if (bean instanceof Integer) {
            Integer value = (Integer) bean;
            return exec(NumberUtil.numberToChinese(value));
        }
        throw new RuntimeException("value is not an integer " + bean);
    }
}
