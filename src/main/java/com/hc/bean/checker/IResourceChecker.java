package com.hc.bean.checker;

import java.io.Serializable;
import java.util.List;

/**
 * 对最终的数据进行check
 */
public interface IResourceChecker extends Serializable {
    List<CheckResult> check(List videoMetas);
}
