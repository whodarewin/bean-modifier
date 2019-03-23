package com.hc.bean.conf;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * 所有的check信息
 */
public class ThreeLevelParam {
    //资源类型-校验类型-校验内容  校验内容 SpecialBeanCheckParam  SumCheckParam
    private Table<String,String,Object> checkParam  = HashBasedTable.create();

    public ThreeLevelParam(){}

    public ThreeLevelParam(Table<String, String, Object> param) {
        this.checkParam = param;
    }

    public Map<String,Object> getParamOfResource(String type){
        return checkParam.row(type);
    }

    public Table<String, String, Object> getCheckParam() {
        return checkParam;
    }

    public void setCheckParam(Table<String, String, Object> checkParam) {
        this.checkParam = checkParam;
    }

    public void put(String type,String validateType,Object value){
        this.checkParam.put(type,validateType,value);
    }
}
