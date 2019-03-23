package com.hc.bean.matcher;

import java.io.Serializable;

/**
 * matcher 用于字段验证
 * matcher 鉴于量非常大，使用单例
 * TODO：有必要使用单例吗？再大一个对象能多大
 * @param <VALUE>
 * @param <RULE>
 */
public interface IMatcher<VALUE,RULE> extends Serializable {
    String getName();
    boolean match(VALUE v, RULE r);
}
