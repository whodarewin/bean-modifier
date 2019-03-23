package com.hc.bean.normalizer;
/**
 * sample:
 * 1 videoName.name replace - with +
 * {
 *    "from": "-",
 *    "to":"+",
 *    "type": "replace",
 *    "path": "videoName.name"
 * }
 *
 * 2 videoName:List.name replace - with +
 * {
 *     "from":"-",
 *     "to":"+",
 *     "type":"replace",
 *     "path":"videoName:foreach.name",
 * }
 *
 *  NORMALIZER
 *  1 simple replace
 *  2 replace every property of a collection
 *  3 replace every key_value of a map
 *  4 remove a property of a collection
 *  5 add a property of a collection
 **/