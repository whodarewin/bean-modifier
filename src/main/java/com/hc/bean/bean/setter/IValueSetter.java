package com.hc.bean.bean.setter;

/**
* @Description: 对bean进行set操作
* @Author:         hancongcong
* @CreateDate:     18-5-28 下午5:22
* @Version:        1.0
*/
public interface IValueSetter {
    void set(Object bean, Object value) throws Exception;
}
