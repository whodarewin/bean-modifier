package com.hc.bean.matcher;


import com.hc.bean.annotation.Matcher;

import java.util.Collection;

@Matcher("collection_match_one")
public class CollectionMatchOneMatcher extends AbstractMatcher<Collection,Collection> {

    @Override
    public boolean match(Collection v, Collection r) {
        if(v == null || r == null){
            return false;
        }
        for(Object o : v){
            if(r.contains(o)){
                return true;
            }
        }
        return false;
    }
}
