package com.hc.bean.conf;

/**
 * 配置从什么地方获取
 */
public interface IConfigrationSource {

    void change(String values);

    void addListener(ConfListener listener);
}
