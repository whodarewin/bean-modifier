package com.hc.bean.normalizer;

import com.hc.bean.annotation.DataNormalizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @Description:    只有整个string全部match了，才返回to，否则返回from。
* @Author:         hancongcong
* @CreateDate:     18-6-5 下午3:33
* @Version:        1.0
*/
@DataNormalizer("equal_replace")
public class EqualReplaceDataNormalizer extends AbstractDataNormalizer{
    private static final Logger LOGGER = LoggerFactory.getLogger(EqualReplaceDataNormalizer.class);
    private String from;
    private String to;

    public EqualReplaceDataNormalizer(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Object normalize(Object bean) throws Exception {
        if(bean instanceof String){
            if(bean.equals(from)){
                return to;
            }else{
                return bean;
            }
        }
        LOGGER.warn("not a string {}",bean.getClass().getName());
        return bean;
    }
}
