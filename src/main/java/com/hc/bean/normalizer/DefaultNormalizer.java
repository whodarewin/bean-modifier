package com.hc.bean.normalizer;

import com.hc.bean.bean.getter.IValueGetter;
import com.hc.bean.bean.setter.IValueSetter;
import com.hc.bean.checker.BeanChecker;
import com.hc.bean.normalizer.param.BeanNormalizeParam;
import com.hc.bean.normalizer.param.SpecialBeanNormalizerParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * normalize 入口
 */
public class DefaultNormalizer implements INormalizer{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNormalizer.class);
    private List<SpecialBeanNormalizerParam> normalizeParams;

    private BeanChecker checker = new BeanChecker();

    public DefaultNormalizer(List<SpecialBeanNormalizerParam> normalizeParams) {
        this.normalizeParams = normalizeParams;
    }

    public List normalize(List beans) throws Exception {

        if(CollectionUtils.isEmpty(normalizeParams)){
            return beans;
        }else{

            for(Object bean : beans){
                for(SpecialBeanNormalizerParam param : normalizeParams){
                    //1 是否match
                    if(checker.checkField(bean,param.getCondition())){
                        //2 do action
                        action(bean,param.getActions());
                    }
                }

            }
        }
        return beans;
    }

    private void action(Object bean, List<BeanNormalizeParam> actions) throws Exception {
        for(BeanNormalizeParam param : actions){
            try {
                IValueGetter getter = param.getGetter();
                IValueSetter setter = param.getSetter();
                IDataNormalizer normalizer = param.getNormalizer();
                Object o = normalizer.normalize(getter.get(bean));
                setter.set(bean, o);
            }catch (Exception e){
                LOGGER.error("normalize error",e);
            }
        }
    }

}
