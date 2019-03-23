package com.hc.bean.matcher;

import com.hc.bean.ELUtil;
import com.hc.bean.annotation.Matcher;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Matcher("SPEL_MATCHER")
public class SPELMatcher extends AbstractMatcher<Object,String> {
    private static final String BEAN_DEFINE_NAME = "bean";

    @Override
    public boolean match(Object v, String r) {
        String expression = ELUtil.formatSPEL(r);
        SpelExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable(BEAN_DEFINE_NAME, v);
        return parser.parseExpression(expression).getValue(context, Boolean.class);
    }
}
