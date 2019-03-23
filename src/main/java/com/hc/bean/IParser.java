package com.hc.bean;

import java.io.IOException;
import java.io.Serializable;

/**
* @Description:对表达式生成相应的操作bean的内部数据结构
* @Author:         hancongcong
* @CreateDate:     18-5-28 下午7:29
* @Version:        1.0
*/
public interface IParser<T> extends Serializable {
    T parse(String value) throws IOException;
}
