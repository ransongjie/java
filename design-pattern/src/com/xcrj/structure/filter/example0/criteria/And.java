package com.xcrj.structure.filter.example0.criteria;

import com.xcrj.structure.filter.example0.Person;

import java.util.List;

/**
 * and方式连接多个标准
 */
public class And implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public And(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    /**
     * 先交给第一个标准过滤，再将过滤结果交给第二个标准过滤
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}
