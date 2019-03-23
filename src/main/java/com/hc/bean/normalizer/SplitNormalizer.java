package com.hc.bean.normalizer;

import com.google.common.collect.Lists;
import com.hc.bean.annotation.DataNormalizer;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;

/**
* @Description:     collection 内部数据的normalize
* @Author:         hancongcong
* @CreateDate:     18-5-30 下午7:55
* @Version:        1.0
*/
@DataNormalizer("split")
public class SplitNormalizer extends AbstractDataNormalizer<String,Collection>{
    private String splitter;

    public SplitNormalizer(String splitter) {
        this.splitter = splitter;
    }

    @Override
    public Collection normalize(String bean) throws Exception {
        if(bean == null){
            return Collections.EMPTY_LIST;
        }
        return Lists.newArrayList(StringUtils.split(bean,splitter));
    }
}
