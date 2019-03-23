package com.hc.bean;

import org.apache.commons.collections.CollectionUtils;

import javax.el.ELProcessor;
import javax.el.ImportHandler;
import java.util.List;

public class ELProcessorFactory {

    public static ELProcessor create(List<String> importClass,
                                     List<String> importPackage, List<String> importStaticClass){

        ELProcessor processor = new ELProcessor();
        if(CollectionUtils.isNotEmpty(importClass)) {
            for (String clazz : importClass) {
                processor.getELManager().importClass(clazz);
            }
        }

        if(CollectionUtils.isNotEmpty(importPackage)) {
            for (String pak : importPackage) {
                processor.getELManager().importPackage(pak);
            }
        }

        if(CollectionUtils.isNotEmpty(importStaticClass)) {
            for (String staticCls : importStaticClass) {
                processor.getELManager().importStatic(staticCls);
            }
        }

        return processor;
    }
}
