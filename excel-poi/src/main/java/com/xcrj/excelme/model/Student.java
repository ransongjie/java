package com.xcrj.excelme.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Student {
    //ID
    private Long id;
    //年龄
    private Integer age;
    //姓名
    private String name;
    //学分
    private Float score;
    //零花钱
    private Double pinMoney;
    //农历生日
    private Date chineseBirthDay;
    //公历生日
    private LocalDateTime birthDay;
    //性别
    private Boolean gender;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Double getPinMoney() {
        return pinMoney;
    }

    public void setPinMoney(Double pinMoney) {
        this.pinMoney = pinMoney;
    }

    public Date getChineseBirthDay() {
        return chineseBirthDay;
    }

    public void setChineseBirthDay(Date chineseBirthDay) {
        this.chineseBirthDay = chineseBirthDay;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}