package com.hc.bean.bean.getter;

import com.hc.bean.ELUtil;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SPELValueGetter extends AbstractBeanValueGetter {
    private String expression;
    private static final String BEAN_DEFINE_NAME = "bean";

    public SPELValueGetter(String expression) {
        this.expression = ELUtil.formatSPEL(expression);
    }

    @Override
    public Object get(Object bean) throws Exception {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable(BEAN_DEFINE_NAME,bean);
        Object result = parser.parseExpression(expression).getValue(context, Object.class);
        return execNextNode(result);
    }
}
