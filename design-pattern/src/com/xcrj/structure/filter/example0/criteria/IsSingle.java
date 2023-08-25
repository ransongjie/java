package com.xcrj.structure.filter.example0.criteria;

import com.xcrj.structure.filter.example0.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 单身标准过滤器
 */
public class IsSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
