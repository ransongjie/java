package com.xcrj.structure.filter.example0.criteria;

import com.xcrj.structure.filter.example0.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 女性标准过滤器
 */
public class IsFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("FEMALE")) {
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
