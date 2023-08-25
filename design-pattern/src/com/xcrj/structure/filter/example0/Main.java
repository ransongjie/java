package com.xcrj.structure.filter.example0;

import com.xcrj.structure.filter.example0.criteria.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Person, IsFemale, IsMale, And, Or
 */
public class Main {
    public static void main(String[] args) {
        //创建需要过滤的对象
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        //建立过滤标准，可组合多个标准
        Criteria criteriaMale = new IsMale();
        Criteria criteriaFemale = new IsFemale();
        Criteria criteriaSingle = new IsSingle();
        Criteria criteriaSingleAndMale = new And(criteriaSingle, criteriaMale);
        Criteria criteriaSingleOrFemale = new Or(criteriaSingle, criteriaFemale);

        //使用标准过滤对象
        System.out.println("Males: ");
        printPersons(criteriaMale.meetCriteria(persons));

        System.out.println("Females: ");
        printPersons(criteriaFemale.meetCriteria(persons));

        System.out.println("Single Males: ");
        printPersons(criteriaSingleAndMale.meetCriteria(persons));

        System.out.println("Single Or Females: ");
        printPersons(criteriaSingleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons) {
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    + ", Gender : " + person.getGender()
                    + ", Marital Status : " + person.getMaritalStatus()
                    + " ]");
        }
    }
}
