package com.xcrj.structure.filter.example0.criteria;

import com.xcrj.structure.filter.example0.Person;

import java.util.List;

/**
 * 标准中持有需要过滤的对象集合
 */
public interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
