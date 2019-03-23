package com.hc.bean.matcher;


import com.hc.bean.ELProcessorFactory;
import com.hc.bean.ELUtil;
import com.hc.bean.annotation.Matcher;

import javax.el.ELProcessor;

@Matcher("EL_MATCHER")
public class ELMatcher extends AbstractMatcher<Object,String> {
    private static final String BEAN_DEFINE_NAME = "bean";

    @Override
    public boolean match(Object v, String r) {
        String expression = ELUtil.formatEL(r);
        ELProcessor processor = ELProcessorFactory.create(null,null,null);
        processor.defineBean(BEAN_DEFINE_NAME,v);
        return (boolean) processor.eval(expression);
    }
}
