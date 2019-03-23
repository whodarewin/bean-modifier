package com.hc.bean.checker.param;

import java.util.List;

public class SpecialBeanCheckParamModel {
    private BeanCheckerParamModel condition;
    private List<BeanCheckerParamModel> actions;

    public BeanCheckerParamModel getCondition() {
        return condition;
    }

    public void setCondition(BeanCheckerParamModel condition) {
        this.condition = condition;
    }

    public List<BeanCheckerParamModel> getActions() {
        return actions;
    }

    public void setActions(List<BeanCheckerParamModel> actions) {
        this.actions = actions;
    }
}
