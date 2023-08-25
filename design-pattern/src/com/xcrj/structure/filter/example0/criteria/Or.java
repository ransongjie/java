package com.xcrj.structure.filter.example0.criteria;

import com.xcrj.structure.filter.example0.Person;

import java.util.List;

/**
 *  or方式连接多个标准
 */
public class Or implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public Or(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    /**
     * 第一二两个标准可以同时过滤，最后取并集即可
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
        List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);

        for (Person person : otherCriteriaItems) {
            if (!firstCriteriaItems.contains(person)) {
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}
