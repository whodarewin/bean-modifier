package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

import java.util.Collection;

@Matcher("collection_equals")
public class CollectionEqualsMatcher extends AbstractMatcher<Collection,Collection> {

    @Override
    public boolean match(Collection v, Collection r) {

        if(v == null && r == null){
            return true;
        }else if(v == null || r == null){
            return false;
        }
        return v.size() == r.size()
                && v.containsAll(r)
                && r.containsAll(v);
    }
}
