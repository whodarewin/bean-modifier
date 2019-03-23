package com.hc.bean.normalizer;


import com.hc.bean.ELProcessorFactory;
import com.hc.bean.ELUtil;
import com.hc.bean.annotation.DataNormalizer;

import javax.el.ELProcessor;

@DataNormalizer("el_normalizer")
public class ELDataNormalizer extends AbstractDataNormalizer {
    private static final String BEAN_DEFINED_NAME = "bean";

    private String elExpress;

    public ELDataNormalizer(String elExpress) {
        this.elExpress = ELUtil.formatEL(elExpress);
    }

    @Override
    public Object normalize(Object bean) throws Exception {
        ELProcessor elProcessor = ELProcessorFactory.create(null,null,null);
        elProcessor.defineBean(BEAN_DEFINED_NAME,bean);
        return elProcessor.eval(elExpress);
    }
}
