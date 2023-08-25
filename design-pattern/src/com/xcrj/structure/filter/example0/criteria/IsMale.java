package com.xcrj.structure.filter.example0.criteria;

import com.xcrj.structure.filter.example0.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 男性标准过滤器
 */
public class IsMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
