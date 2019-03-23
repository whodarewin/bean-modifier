package com.hc.bean.normalizer.param;

import java.util.List;

/**
* @Description:
* @Author:         hancongcong
* @CreateDate:     18-5-28 下午5:59
* @Version:        1.0
*/
public class BeanNormalizeParamModel {
    private List params;
    private String type;
    private String fromPath;
    private String toPath;
    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromPath() {
        return fromPath;
    }

    public void setFromPath(String fromPath) {
        this.fromPath = fromPath;
    }

    public String getToPath() {
        return toPath;
    }

    public void setToPath(String toPath) {
        this.toPath = toPath;
    }

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
