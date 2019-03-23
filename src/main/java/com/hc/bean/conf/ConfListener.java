package com.hc.bean.conf;

/**
 * 配置变更监听器
 * @param <T>
 */
public interface ConfListener<T> {
    void onChange(String data);
    T get();
    int getVersion();
}
