package com.hc.bean.checker.param;

import java.io.Serializable;

public class SumCheckParam implements Serializable {
    private BeanCheckerParam condition;
    private long count;

    public BeanCheckerParam getCondition() {
        return condition;
    }

    public void setCondition(BeanCheckerParam condition) {
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
        return "SumCheckParam{" +
                "condition=" + condition +
                ", count=" + count +
                '}';
    }
}
