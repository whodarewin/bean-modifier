package com.hc.bean.checker.param;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class SpecialBeanCheckParam implements Serializable {
    private BeanCheckerParam condition;
    private List<BeanCheckerParam> actions;

    public SpecialBeanCheckParam(BeanCheckerParam condition, List<BeanCheckerParam> actions) {
        this.condition = condition;
        this.actions = actions;
    }

    public BeanCheckerParam getCondition() {
        return condition;
    }

    public void setCondition(BeanCheckerParam condition) {
        this.condition = condition;
    }

    public List<BeanCheckerParam> getActions() {
        return actions;
    }

    public void setActions(List<BeanCheckerParam> actions) {
        this.actions = actions;
    }
}
