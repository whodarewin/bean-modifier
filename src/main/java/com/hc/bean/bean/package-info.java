package com.hc.bean.bean;
/**
 *  after : is an action,
 *  list_1 means get value at index 1
 *  map_key means get value where key is ${key}
 *  foreach means get all value where gives by the getter behind
 *  also you can define your special action by implements IValueGetter
 * express:
 * 1 property.property.property
 * 2 property:list_1.property.property
 * 3 property:foreach.property.property
 * 4 property:map_key.property.property
 * 5 property:list_1:map_key.property
 * 6 property:map_key:list_1.property
 * 7 property:foreach:map_key.property
 * 8 etc...
 *
 * getter and setter use the same express.
 */