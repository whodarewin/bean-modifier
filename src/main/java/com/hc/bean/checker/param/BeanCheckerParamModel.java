package com.hc.bean.checker.param;

public class BeanCheckerParamModel {
    //filed name : simple name,name:foreach:name,dynamic:
    private String path;
    //final match rule
    private Object value;
    //match type bigger lower container
    private String type;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
