package com.hc.bean.normalizer.param;

import com.hc.bean.checker.param.BeanCheckerParamModel;

import java.util.List;

public class SpecialBeanNormalizerParamModel {
    private BeanCheckerParamModel condition;
    private List<BeanNormalizeParamModel> actions;

    public BeanCheckerParamModel getCondition() {
        return condition;
    }

    public void setCondition(BeanCheckerParamModel condition) {
        this.condition = condition;
    }

    public List<BeanNormalizeParamModel> getActions() {
        return actions;
    }

    public void setActions(List<BeanNormalizeParamModel> actions) {
        this.actions = actions;
    }
}
