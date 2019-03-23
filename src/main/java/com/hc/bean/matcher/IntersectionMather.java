package com.hc.bean.matcher;

import com.hc.bean.annotation.Matcher;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

@Matcher("intersection")
public class IntersectionMather extends AbstractMatcher<Collection,Collection> {

    @Override
    public boolean match(Collection v, Collection r) {
        if(CollectionUtils.isEmpty(v) || CollectionUtils.isEmpty(r)){
            return false;
        }
        return CollectionUtils.isNotEmpty(CollectionUtils.intersection(v,r));
    }
}
