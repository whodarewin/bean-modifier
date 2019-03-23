package com.hc.bean.bean.getter;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionValueGetter extends AbstractBeanValueGetter<Collection>{
    private SimpleValueGetter<Collection> getter ;
    private String path;

    public CollectionValueGetter(String path) {
        this.path = path;
        this.getter = new SimpleValueGetter(path);
    }


    @Override
    public Collection get(Object o) throws Exception {
        Collection objs = getter.get(o);
        Collection ret = new ArrayList();
        for(Object object : objs){
            ret.add(super.execNextNode(object));
        }
        return ret;
    }
}
