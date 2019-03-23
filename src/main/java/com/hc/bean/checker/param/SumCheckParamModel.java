package com.hc.bean.checker.param;

import java.io.Serializable;

public class SumCheckParamModel implements Serializable {
    private BeanCheckerParamModel condition;
    private long count;

    public BeanCheckerParamModel getCondition() {
        return condition;
    }

    public void setCondition(BeanCheckerParamModel condition) {
        this.condition = condition;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SumCheckParamModel{" +
                "condition=" + condition +
                ", count=" + count +
                '}';
    }
}
