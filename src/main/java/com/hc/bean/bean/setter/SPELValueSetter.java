package com.hc.bean.bean.setter;

import com.hc.bean.ELUtil;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SPELValueSetter implements IValueSetter {
    private static final String BEAN_DEFINED_NAME = "bean";
    private String express;

    public SPELValueSetter(String express) {
        this.express = ELUtil.formatSPEL(express);
    }

    @Override
    public void set(Object bean, Object value) throws Exception {
        SpelExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable(BEAN_DEFINED_NAME, bean);
        parser.parseExpression(express).setValue(context,value);
    }
}
