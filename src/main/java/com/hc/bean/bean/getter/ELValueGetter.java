package com.hc.bean.bean.getter;

import com.hc.bean.ELProcessorFactory;
import com.hc.bean.ELUtil;

import javax.el.ELProcessor;
/**
* @Description:    使用EL表达式取值，更加灵活，推荐后续使用
* @Author:         hancongcong
* @CreateDate:     18-6-8 下午5:56
* @Version:        1.0
*/
public class ELValueGetter extends AbstractBeanValueGetter {
    private static final String NAME = "EL";
    private static final String BEAN_NAME = "bean";
    private String elExpress;

    public ELValueGetter(String elExpress) {
        this.elExpress = ELUtil.formatEL(elExpress);
    }

    @Override
    public Object get(Object bean) throws Exception {
        ELProcessor processor = ELProcessorFactory.create(null,null,null);
        processor.defineBean(BEAN_NAME,bean);
        return processor.eval(elExpress);
    }
}
