package com.hc.bean.normalizer;

import java.io.Serializable;

/**
* @Description:    对数据的normalize工作
* @Author:         hancongcong
* @CreateDate:     18-5-30 下午7:28
* @Version:        1.0
*/
public interface IDataNormalizer<FROM,TO> extends Serializable {
    /**
     *
     * @param bean 操作的数据
     * @return
     */
    TO normalize(FROM bean) throws Exception;

    void setNext(IDataNormalizer next);

    String getName();
}
