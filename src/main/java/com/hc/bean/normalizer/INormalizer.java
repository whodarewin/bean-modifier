package com.hc.bean.normalizer;

import java.io.Serializable;
import java.util.List;

public interface INormalizer extends Serializable {
    List normalize(List beans) throws Exception ;
}
