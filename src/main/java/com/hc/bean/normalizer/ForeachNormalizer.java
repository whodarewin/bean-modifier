package com.hc.bean.normalizer;

import com.hc.bean.annotation.DataNormalizer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@DataNormalizer("foreach")
public class ForeachNormalizer<FROM extends Collection,TO extends Collection> extends AbstractDataNormalizer<FROM,TO>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ForeachNormalizer.class);
    private static final String SET = "set";
    private static final String LIST = "list";
    private String returnType;
    private boolean addAll;

    public ForeachNormalizer(String returnType,boolean addAll) {
        this.returnType = returnType;
        this.addAll = addAll;
    }

    @Override
    public TO normalize(FROM beans) throws Exception {
        if(CollectionUtils.isEmpty(beans)){
            LOGGER.warn("beans is empty");
            return (TO) Collections.EMPTY_LIST;
        }
        Collection ret = null;
        if(StringUtils.isEmpty(returnType) || returnType.toLowerCase().equals(LIST)){
            ret = new ArrayList();
        }else if(returnType.toLowerCase().equals(SET)){
            ret = new HashSet();
        }else{
            throw new RuntimeException("return type not defined");
        }

        for(Object bean : beans){
            Object normalized = super.exec(bean);
            if(addAll && (normalized instanceof Collection)){
                ret.addAll((Collection) normalized);
                continue;
            }
            ret.add(normalized);
        }
        return (TO) ret;
    }
}
