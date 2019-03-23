package com.hc.bean.normalizer.param;


import com.hc.bean.checker.param.BeanCheckerParam;

import java.io.Serializable;
import java.util.List;

/**
* @Description:    一个bean进行标准化的所有参数
* @Author:         hancongcong
* @CreateDate:     18-5-29 下午4:54
* @Version:        1.0
*/
public class SpecialBeanNormalizerParam implements Serializable {
    private BeanCheckerParam condition;
    private List<BeanNormalizeParam> actions;

    public BeanCheckerParam getCondition() {
        return condition;
    }

    public void setCondition(BeanCheckerParam condition) {
        this.condition = condition;
    }

    public List<BeanNormalizeParam> getActions() {
        return actions;
    }

    public void setActions(List<BeanNormalizeParam> actions) {
        this.actions = actions;
    }
}
