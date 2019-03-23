package com.hc.bean.normalizer;

import com.hc.bean.ELUtil;
import com.hc.bean.annotation.DataNormalizer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@DataNormalizer("spel_normalizer")
public class SPELDataNormalizer extends AbstractDataNormalizer {
    private static final String BEAN_DEFINED_NAME = "bean";

    private String elExpress;

    public SPELDataNormalizer(String elExpress) {
        this.elExpress = ELUtil.formatSPEL(elExpress);
    }

    @Override
    public Object normalize(Object bean) throws Exception {
        SpelExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable(BEAN_DEFINED_NAME, bean);
        return parser.parseExpression(elExpress).getValue(context, Object.class);
    }
}
